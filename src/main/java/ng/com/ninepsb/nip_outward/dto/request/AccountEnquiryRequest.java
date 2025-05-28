package ng.com.ninepsb.nip_outward.dto.request;


import ng.com.ninepsb.nip_outward.enums.HandlerName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

import static ng.com.ninepsb.nip_outward.enums.HandlerName.NAME_ENQUIRY;

public record AccountEnquiryRequest(
        @Length(min = 10, max = 10, message = "accountNumber must be 10 digits")
        @Pattern(regexp = "\\d+$", message = "accountNumber must be digits only")
        String accountNumber,

        @Length(min = 6, max = 6, message = "bankCode must be 6 digits")
        @Pattern(regexp = "\\d+$", message = "bankCode must be digits only")
        String bankCode
        ) implements NipOutwardRequest{

        @Override
        public HandlerName getHandlerName() {
                return NAME_ENQUIRY;
        }
}
