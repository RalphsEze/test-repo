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
}
