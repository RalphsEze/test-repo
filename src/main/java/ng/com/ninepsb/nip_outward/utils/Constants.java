package ng.com.ninepsb.nip_outward.utils;

public class Constants {

    // "999" is code to signify settlement data has been processed and that record has been stated to be reversed.
    public static final String[] ReversibleResponse = {
            "999", "03", "05", "06", "07", "08", "12", "13", "14", "15", "16", "17", "18",
            "26", "25", "30", "34", "35", "51", "57", "58", "61", "63", "65", "68",
            "92", "94", "96", "21", "91", "09", "97", "81", "82", "80"
    };

    public static final String[] NonRequeryableResponse = {
            "00", "03", "05", "06", "07", "08", "12", "13", "14", "15", "16", "17", "18",
            "26", "30", "34", "35", "51", "57", "58", "61", "63", "65", "68",
            "92", "94", "96", "21", "91", "09", "97", "81", "82", "80"
    };

    public static final String PSB9WalletCode = "999";
    public static final String PSB9BankNIBSSScheme = "120001";
    public static final String PSB9BankCode = "802";
    public static final String NibssRequest = "NibssRequest";
    public static final String NibssResponse = "NibssResponse";
    public static final String Yes = "Y";
    public static final String No = "N";
    public static final String BlockAccount = "ACCOUNTNUMBER";
    public static final String UnblockAccount = "U";
    public static final String ActiveNIPClientStatus = "ACTIVE";
    public static final String InActiveNIPClientStatus = "INACTIVE";
    public static final String DebitAccountTypeCASA = "CASA";
    public static final String DebitAccountTypeList = "LIST";
    public static final String RegularAccount = "REGULAR";
    public static final String VirtualAccount = "VIRTUAL";
    public static final String PSB9VirtualAccount = "9PSBVIRTUAL";
    public static final String CEVAWalletAccount = "CEVA WALLET";
    public static final String InvalidAccount = "INVALID ACCOUNT";
    public static final String OtherBank = "OTHER BANK";
    public static final String CEVAInvalidWalletResponse = "999";
    public static final String ErrorRetrievingAccountOrWalletDetails = "0000";
    public static final String ZONESWITCH = "ZONESWITCH";
    public static final String REMITA = "REMITA";
    public static final String NIBSS = "NIBSS";
    public static final String LOCAL = "LOCAL";
    public static final String IntraTransferType = "INTRA";
    public static final String InterTransferType = "INTER";
    public static final String BothTransferType = "BOTH";
    public static final String TransferTypeNotPermitted = "NOTPERMITTED";
    public static final String UnknownRoute = "UNKNOWNROUTE";
    public static final String DynamicVirtualAccount = "DYNAMIC";
    public static final String StaticVirtualAccount = "STATIC";
    public static final String VirtualAmountTypeExact = "EXACT";
    public static final String VirtualAmountTypeAny = "ANY";
    public static final String VirtualAmountTypeLowerOrExact = "LOWEROREXACT";
    public static final String VirtualAmountTypeHigherOrExact = "HIGHEROREXACT";
    public static final String Percentage = "Percentage";
    public static final String Flat = "Flat";
    public static final String None = "NONE";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }
}
