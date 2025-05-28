package ng.com.ninepsb.nip_outward.utils;

import ng.com.ninepsb.nip_outward.config.AppConfig;
import ng.com.ninepsb.nip_outward.model.BankDetail;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountUtils {

    private final AppConfig appConfig;

    public AccountUtils(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public boolean isMobile(String accountNumber) {
        List<String> mobilePrefixes = appConfig.getMobilePrefixes();
        int upperIndex = accountNumber.length() == 10 ? 3 : 4;
        String prefix = accountNumber.substring(0, upperIndex);
        return mobilePrefixes.contains(prefix);
    }

    public boolean isCEVAWalletAccount(String accountNumber) {
        List<String> cevaPrefixes = appConfig.getMobilePrefixes();
        String prefix = accountNumber.substring(0, 3);
        return cevaPrefixes.contains(prefix);
    }

    public boolean isVirtual(String accountNumber) {
        List<String> virtualPrefixes = getVirtualPrefixes();
        return false;
    }

    @Cacheable(cacheNames = Constants.CACHE_KEY_VIRTUAL_PREFIX, key = Constants.CACHE_KEY_VIRTUAL_PREFIX)
    public List<String> getVirtualPrefixes() {

        return null;
    }

    public boolean is9PsbVirtual(String accountNumber) {
        return false;
    }

    public BankDetail isValidNUBAN(String accountNumber, BankDetail bankDetail) {
        try {
            if (accountNumber == null || bankDetail == null || bankDetail.getBankCode() == null) {
                return null;
            }

            String bankCode = bankDetail.getBankCode();

            if (accountNumber.length() != 10 || bankCode.length() != 3 ||
                    !accountNumber.matches("\\d{10}") || !bankCode.matches("\\d{3}")) {
                return null;
            }

            int[] factors = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};
            String combinedAccountNum = bankCode + accountNumber;
            int sum = 0;

            for (int i = 0; i < factors.length; i++) {
                sum += factors[i] * Character.getNumericValue(combinedAccountNum.charAt(i));
            }

            int checkDigit = 10 - (sum % 10);
            int lastDigit = Character.getNumericValue(accountNumber.charAt(9));

            if (checkDigit == 10) checkDigit = 0;

            return lastDigit == checkDigit ? bankDetail : null;
        } catch (Exception e) {
            return null;
        }
    }
}
