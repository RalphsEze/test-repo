package ng.com.ninepsb.nip_outward.dto.response;

public record AccountEnquiryResponse(
        String accountNumber,
        String bankCode,
        String bankName,
        String accountName,
        String kyc
) implements NipOutwardResponse {
}
