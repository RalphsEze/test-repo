package ng.com.ninepsb.nip_outward.controller;

import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.dto.response.ApiResponse;
import ng.com.ninepsb.nip_outward.service.NipOutwardService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static ng.com.ninepsb.nip_outward.dto.response.ApiResponse.STATUS_CODE_SUCCESS;

@RestController
@RequestMapping("api/v2/nip-outward")
public class NipOutwardController {

    private final NipOutwardService nipOutwardService;

    public NipOutwardController(NipOutwardService nipOutwardService) {
        this.nipOutwardService = nipOutwardService;
    }

    @PostMapping("account/enquiry")
    public ApiResponse<AccountEnquiryResponse> performAccountEnquiry(
            @Valid @RequestBody AccountEnquiryRequest accountEnquiryRequest,
            HttpServletRequest httpServletRequest) {
        var response = nipOutwardService.performAccountEnquiry(accountEnquiryRequest, httpServletRequest);
        return new ApiResponse<>(STATUS_CODE_SUCCESS, "Account enquiry successful", response);
    }

    @GetMapping("test")
    public ApiResponse<String> testCallToNibss(HttpServletRequest httpServletRequest) {
        nipOutwardService.testCallToNibss(httpServletRequest);
        return new ApiResponse<>(STATUS_CODE_SUCCESS, "Account enquiry successful", "response");
    }
}
