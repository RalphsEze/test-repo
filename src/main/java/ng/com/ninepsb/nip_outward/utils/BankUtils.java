package ng.com.ninepsb.nip_outward.utils;

import ng.com.ninepsb.nip_outward.model.BankDetail;
import org.springframework.stereotype.Component;

@Component
public class BankUtils {
    public BankDetail getBankDetail(String bankCode) {

        // check if bank code is valid

        // Get long bank code

        // Get bank code

        BankDetail bankDetail = new BankDetail();
        bankDetail.setBankCode(bankCode);
        return bankDetail;
    }

    public String getTestBankCode(String providedBankCode) {
        return switch (providedBankCode) {
            case "000016" -> "999011";
            case "000015" -> "999057";
            case "000013" -> "999058";
            case "000012" -> "999221";
            case "000008" -> "999076";
            case "000001" -> "999232";
            default -> providedBankCode;
        };
    }

}
