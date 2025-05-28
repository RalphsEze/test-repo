package ng.com.ninepsb.nip_outward.service.impl;

import ng.com.ninepsb.logger_lib.service.CustomLogger;
import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.service.NipOutwardService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
class DefaultNipOutwardService implements NipOutwardService {

    private final CustomLogger logger;

    DefaultNipOutwardService(CustomLogger logger) {
        this.logger = logger;
    }

    @Override
    public AccountEnquiryResponse performAccountEnquiry(AccountEnquiryRequest accountEnquiryRequest, HttpServletRequest request) {
        return (AccountEnquiryResponse) handle(accountEnquiryRequest, request);
    }

    @Override
    public void testCallToNibss(HttpServletRequest httpServletRequest) {
        AccountEnquiryRequest request = new AccountEnquiryRequest("0051762787", "999998");
        AccountEnquiryResponse response = (AccountEnquiryResponse) handle(request, httpServletRequest);
        logger.logResponse(response.toString());
    }
}
