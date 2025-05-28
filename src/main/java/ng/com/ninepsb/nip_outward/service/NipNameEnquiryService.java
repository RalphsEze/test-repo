package ng.com.ninepsb.nip_outward.service;

import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.enums.OpsProcessor;
import ng.com.ninepsb.nip_outward.model.ClientProfile;
import ng.com.ninepsb.nip_outward.model.NipNameEnquiry;

import java.time.LocalDateTime;

public interface NipNameEnquiryService {
    NipNameEnquiry getRecentEnquiry(AccountEnquiryRequest request, LocalDateTime leastTime, Long clientId, OpsProcessor processor);

    NipNameEnquiry initiate(AccountEnquiryRequest request, String sessionId, ClientProfile clientProfile, String callerIp, OpsProcessor processor);

    void update(AccountEnquiryResponse nameEnquiryResponse, NipNameEnquiry nameEnquiry, LocalDateTime startTime, LocalDateTime endTime);
}
