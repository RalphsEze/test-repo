package ng.com.ninepsb.nip_outward.handlers;

import ng.com.ninepsb.logger_lib.service.CustomLogger;
import ng.com.ninepsb.nibss_nip_lib.enums.NibssKycCode;
import ng.com.ninepsb.nibss_nip_lib.enums.NibssResponseCode;
import ng.com.ninepsb.nibss_nip_lib.model.requests.NESingleRequest;
import ng.com.ninepsb.nibss_nip_lib.model.response.NESingleResponse;
import ng.com.ninepsb.nibss_nip_lib.service.NipTransactionProcessor;
import ng.com.ninepsb.nip_outward.clients.NIPClient;
import ng.com.ninepsb.nip_outward.config.AppConfig;
import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.request.NipOutwardRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.enums.OpsProcessor;
import ng.com.ninepsb.nip_outward.exception.BlockedChannelException;
import ng.com.ninepsb.nip_outward.exception.InvalidParamsException;
import ng.com.ninepsb.nip_outward.exception.NibssRequestException;
import ng.com.ninepsb.nip_outward.exception.ServiceUnavailableException;
import ng.com.ninepsb.nip_outward.model.BankDetail;
import ng.com.ninepsb.nip_outward.model.ClientProfile;
import ng.com.ninepsb.nip_outward.model.NipNameEnquiry;
import ng.com.ninepsb.nip_outward.service.ClientProfileService;
import ng.com.ninepsb.nip_outward.service.NipNameEnquiryService;
import ng.com.ninepsb.nip_outward.utils.BankUtils;
import ng.com.ninepsb.nip_outward.utils.Constants;
import ng.com.ninepsb.nip_outward.utils.SessionUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static ng.com.ninepsb.nip_outward.enums.HandlerName.NAME_ENQUIRY;
import static ng.com.ninepsb.nip_outward.enums.OpsProcessor.LOCAL;
import static ng.com.ninepsb.nip_outward.enums.OpsProcessor.NIBSS;
import static ng.com.ninepsb.nip_outward.utils.Constants.*;

@Component
public class NameEnquiryHandler extends BaseHandler {

    private final CustomLogger logger;
    private final AppConfig appConfig;
    private final ClientProfileService clientProfileService;
    private final BankUtils bankUtils;
    private final NipNameEnquiryService nipNameEnquiryService;
    private final NipTransactionProcessor nipTransactionProcessor;
    private final NIPClient nipClient;

    public NameEnquiryHandler(CustomLogger logger,
                              AppConfig appConfig,
                              ClientProfileService clientProfileService,
                              BankUtils bankUtils,
                              NipNameEnquiryService nipNameEnquiryService,
                              NipTransactionProcessor nipTransactionProcessor,
                              NIPClient nipClient) {
        this.logger = logger;
        this.appConfig = appConfig;
        this.clientProfileService = clientProfileService;
        this.bankUtils = bankUtils;
        this.nipNameEnquiryService = nipNameEnquiryService;
        this.nipTransactionProcessor = nipTransactionProcessor;
        this.nipClient = nipClient;
        init(NAME_ENQUIRY);
    }

    @Override
    public AccountEnquiryResponse handle(NipOutwardRequest nipOutwardRequest, HttpServletRequest httpRequest) {
        var request = (AccountEnquiryRequest) nipOutwardRequest;
        String clientId = "CLIENT"; // This will be retrieved from authentication service

        ClientProfile clientProfile = clientProfileService.getClientProfile(clientId);
        logger.logRequest(request.toString(), clientProfile.toString());

        if (appConfig.isTest()) {
            return getTestResponse(request);
        }

        BankDetail bankDetail = bankUtils.getBankDetail(request.bankCode());
        OpsProcessor processor = resolveProcessor(bankDetail.getBankCode());
        LocalDateTime leastTime = LocalDateTime.now().minusMinutes(appConfig.getNameEnquiryValidityTime());

        verifyAccountNotBlocked(clientId, request.accountNumber(), request.bankCode());

        NipNameEnquiry recent = nipNameEnquiryService.getRecentEnquiry(request, leastTime, clientId, processor);
        if (recent != null) {
            return getCachedResponse();
        }

        String sessionId = SessionUtils.generateSessionId();
        NipNameEnquiry nameEnquiry = nipNameEnquiryService.initiate(request, sessionId, clientProfile, httpRequest.getRemoteAddr(), processor);

        LocalDateTime start = now();
        AccountEnquiryResponse response = switch (processor) {
            case LOCAL -> perform9psbNameEnquiry(request, sessionId, clientProfile, bankDetail, nameEnquiry);
            case NIBSS -> performNibssNameEnquiry(request, sessionId, clientProfile, bankDetail, nameEnquiry);
        };

        nipNameEnquiryService.update(response, nameEnquiry, start, now());
        return response;
    }

    private AccountEnquiryResponse performNibssNameEnquiry(AccountEnquiryRequest request, String sessionId, ClientProfile clientProfile, BankDetail bankDetail, NipNameEnquiry nameEnquiry) {

        NESingleRequest neRequest = buildNERequest(request, sessionId, bankDetail, clientProfile);
        String encryptedRequest = nipTransactionProcessor.prepareOutgoingTransaction(neRequest);

        logger.logNibssData(NibssRequest, request.toString(), encryptedRequest);

        String encryptedResponse = nipClient.performCallToNibss(encryptedRequest);
        NESingleResponse response = (NESingleResponse) nipTransactionProcessor.prepareIncomingTransaction(encryptedResponse);

        logger.logNibssData(NibssResponse, response.toString());

        NibssResponseCode code = NibssResponseCode.fromCode(response.getResponseCode());

        if (NibssResponseCode.SUCCESSFUL.equals(code)) {
            return new AccountEnquiryResponse(response.getAccountNumber(), response.getDestinationInstitutionCode(), nameEnquiry.getBankName(), response.getAccountName(), String.valueOf(response.getKycLevel()));
        }

        if (code != null) {
            throw new NibssRequestException(code.getDescription());
        }
        throw new ServiceUnavailableException();
    }

    private static NESingleRequest buildNERequest(AccountEnquiryRequest request, String sessionId, BankDetail bankDetail, ClientProfile clientProfile) {
        var neRequest = new NESingleRequest();
        neRequest.setAccountNumber(request.accountNumber());
        neRequest.setChannelCode(clientProfile.getChannel().getCode());
        neRequest.setSessionId(sessionId);
        neRequest.setDestinationInstitutionCode(bankDetail.getBankCode());
        return neRequest;
    }

    private AccountEnquiryResponse perform9psbNameEnquiry(AccountEnquiryRequest request,
                                                          String sessionId,
                                                          ClientProfile clientProfile,
                                                          BankDetail bankDetail,
                                                          NipNameEnquiry nameEnquiry) {
        if (request.accountNumber().length() > 10 && !appConfig.isTest()) {
            throw new InvalidParamsException("Invalid account number");
        }



        // Future: Add local logic here
        throw new UnsupportedOperationException("Local name enquiry not yet implemented");
    }

    private void verifyAccountNotBlocked(String clientId, String accountNumber, String bankCode) {
        List<String> blockedChannels = appConfig.getBlockedChannels();
        if (blockedChannels.contains(clientId) &&
                (bankCode.equalsIgnoreCase(PSB9BankCode) || bankCode.equalsIgnoreCase(PSB9BankNIBSSScheme))
                && "456".contains(accountNumber.substring(0, 1))
        ) {
            throw new BlockedChannelException("Channel blocked for client: " + clientId);
        }
    }

    private OpsProcessor resolveProcessor(String bankCode) {
        return PSB9BankCode.equalsIgnoreCase(bankCode) || PSB9BankNIBSSScheme.equalsIgnoreCase(bankCode)
                ? LOCAL : NIBSS;
    }

    private AccountEnquiryResponse getTestResponse(AccountEnquiryRequest request) {
        return new AccountEnquiryResponse("", bankUtils.getTestBankCode(request.bankCode()), "", "", NibssKycCode.KYC_LEVEL_1.name());
    }

    private AccountEnquiryResponse getCachedResponse() {
        return new AccountEnquiryResponse("", "", "", "", NibssKycCode.KYC_LEVEL_1.name());
    }

    private LocalDateTime now() {
        return LocalDateTime.now(Clock.systemDefaultZone());
    }
}
