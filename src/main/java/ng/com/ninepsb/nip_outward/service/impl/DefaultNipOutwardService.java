package ng.com.ninepsb.nip_outward.service.impl;

import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.service.NipOutwardService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
class DefaultNipOutwardService implements NipOutwardService {

    @Override
    public AccountEnquiryResponse performAccountEnquiry(AccountEnquiryRequest accountEnquiryRequest, HttpServletRequest request) {
        return (AccountEnquiryResponse) handle(accountEnquiryRequest, request);
    }
}
