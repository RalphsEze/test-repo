package ng.com.ninepsb.nip_outward.dto;

import ng.com.ninepsb.nip_outward.enums.AccountCategory;

public record AccountType(String accountNumber, AccountCategory category) {
}
