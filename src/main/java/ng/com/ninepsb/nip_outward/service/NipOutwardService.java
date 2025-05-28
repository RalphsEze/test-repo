package ng.com.ninepsb.nip_outward.service;

import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.request.NipOutwardRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.dto.response.NipOutwardResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static ng.com.ninepsb.nip_outward.handlers.HandlerManager.getHandler;

public interface NipOutwardService {
    AccountEnquiryResponse performAccountEnquiry(@Valid AccountEnquiryRequest accountEnquiryRequest, HttpServletRequest httpServletRequest);

    default NipOutwardResponse handle(NipOutwardRequest nipOutwardRequest, HttpServletRequest httpServletRequest) {
        var handler = getHandler(nipOutwardRequest.getHandlerName());
        return handler.handle(nipOutwardRequest, httpServletRequest);
    }

    void testCallToNibss(HttpServletRequest httpServletRequest);
}
