package ng.com.ninepsb.nip_outward.service.impl;

import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.enums.OpsProcessor;
import ng.com.ninepsb.nip_outward.model.ClientProfile;
import ng.com.ninepsb.nip_outward.model.NipNameEnquiry;
import ng.com.ninepsb.nip_outward.service.NipNameEnquiryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class DefaultNipNameEnquiryService implements NipNameEnquiryService {

    @Override
    public NipNameEnquiry getRecentEnquiry(AccountEnquiryRequest request, LocalDateTime leastTime, Long clientId, OpsProcessor processor) {
        return null;
    }

    @Override
    public NipNameEnquiry initiate(AccountEnquiryRequest request, String sessionId, ClientProfile clientProfile, String callerIp, OpsProcessor processor) {
        return null;
    }

    @Override
    public void update(AccountEnquiryResponse nameEnquiryResponse, NipNameEnquiry nameEnquiry, LocalDateTime startTime, LocalDateTime endTime) {

    }
}
