package com.lib.emv.co.MP.Model;


public final class MerchantPresentedModeCodes {

    public static final String PAYLOAD_FORMAT_INDICATOR = "00";
    public static final String POINT_OF_INITIATION_METHOD = "01";
    public static final String MERCHANT_ACCOUNT_INF0RMATION = "28";
    public static final String MERCHANT_CATEGORY_CODE = "52";
    public static final String TRANSACTION_CURRENCY = "53";
    public static final String TRANSACTION_AMOUNT = "54";
    public static final String TIP_OR_CONVENIENCE_INDICATOR = "55";
    public static final String VALUE_OF_CONVENIENCE_FEE_FIXED = "56";
    public static final String VALUE_OF_CONVENIENCE_FEE_PERCENTAGE = "57";
    public static final String COUNTRY_CODE = "58";
    public static final String MERCHANT_NAME_DBA = "59"; // (M) Merchant Name
    public static final String MERCHANT_CITY = "60";
    public static final String POSTAL_CODE = "61";
    public static final String ADDITIONAL_DATA_FIELD_TEMPLATE = "62";
    public static final String CRC = "63"; // (M) CRC
    public static final String MERCHANT_INFORMATION_LANGUAGE_TEMPLATE = "64";
    public static final String CONTEXT_OF_TRANSACTION = "80";

    private MerchantPresentedModeCodes() {
        super();
    }

}