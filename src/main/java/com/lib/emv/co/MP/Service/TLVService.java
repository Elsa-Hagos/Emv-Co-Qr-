package com.lib.emv.co.MP.Service;

import com.lib.emv.co.MP.Model.AdditionalDataField;
import com.lib.emv.co.MP.Model.MerchantAccountInformationFieldCodes;
import com.lib.emv.co.MP.Model.MerchantPresentedModeCodes;

@org.springframework.stereotype.Service
public class TLVService {

     String generateEMVCoQRString(String pointOfInitiation,
            String merchantGUID, String mspBIC ,String accountNumber, String merchantCategoryCode, String Currency ,
             String transactionAmount, String tipOrConvenienceIndicator, String fixedConvenienceFee, String fixedConvenienceFeePercentage, String countryCode, String dbaName, String city,
              String storeLabel,    String terminalNumber,
            String transactionPurpose, String additionalConsumerDataRequest, String merchantTaxID

    ) {
        StringBuilder qrString = new StringBuilder();


        appendTLV(qrString, MerchantPresentedModeCodes.PAYLOAD_FORMAT_INDICATOR, "01");
        appendTLV(qrString, MerchantPresentedModeCodes.POINT_OF_INITIATION_METHOD, pointOfInitiation);

        StringBuilder tag28 = new StringBuilder();
        appendTLV(tag28, MerchantAccountInformationFieldCodes.GLOBALLY_UNIQUE_IDENTIFIER, merchantGUID);
        appendTLV(tag28, MerchantAccountInformationFieldCodes.MSP,  mspBIC );
        appendTLV(tag28, MerchantAccountInformationFieldCodes.DESTINATION_ACCOUNT_NUMBER, accountNumber);
        appendTLV(qrString, "28", tag28.toString());

        appendTLV(qrString, MerchantPresentedModeCodes.MERCHANT_CATEGORY_CODE, merchantCategoryCode);
        appendTLV(qrString, MerchantPresentedModeCodes.TRANSACTION_CURRENCY, Currency);
        appendTLV(qrString, MerchantPresentedModeCodes.TRANSACTION_AMOUNT, transactionAmount);
        appendTLV(qrString, MerchantPresentedModeCodes.TIP_OR_CONVENIENCE_INDICATOR , tipOrConvenienceIndicator);
        appendTLV(qrString, MerchantPresentedModeCodes.VALUE_OF_CONVENIENCE_FEE_FIXED , fixedConvenienceFee);
        appendTLV(qrString,MerchantPresentedModeCodes.VALUE_OF_CONVENIENCE_FEE_PERCENTAGE , fixedConvenienceFeePercentage);
        appendTLV(qrString, MerchantPresentedModeCodes.COUNTRY_CODE, countryCode);
        appendTLV(qrString, MerchantPresentedModeCodes.MERCHANT_NAME_DBA, dbaName);
        appendTLV(qrString, MerchantPresentedModeCodes.MERCHANT_CITY, city);


        StringBuilder tag62 = new StringBuilder();
        appendTLV(tag62, AdditionalDataField.STORE_LABEL , storeLabel);
        appendTLV(tag62, AdditionalDataField.TERMINAL_NUMBER , terminalNumber);
        appendTLV(tag62, AdditionalDataField.TRANSACTION_PURPOSE , transactionPurpose);
        appendTLV(tag62, AdditionalDataField.ADDITIONAL_CONSUMER_DATA_REQUEST , additionalConsumerDataRequest);
        appendTLV(tag62, AdditionalDataField.MERCHANT_TAX_ID , merchantTaxID);
        appendTLV(qrString, "62", tag62.toString());

       // appendTLV(qrString, MerchantPresentedModeCodes.CONTEXT_OF_TRANSACTION,transactionContext );
        qrString.append("6304");
        String crc = calculateCRC(qrString.toString().getBytes());
        qrString.append(crc);

        return qrString.toString();

    }
    private void appendTLV(StringBuilder builder, String tag, String value) {
        if (value != null) {
            int length = value.length();
            builder.append(tag);
            builder.append(String.format("%02d", length));
            builder.append(value);
          }
    }

    public static String calculateCRC(byte[] data) {
        final int POLYNOMIAL = 0x1021;
        int crc = 0xFFFF;
        for (byte b : data) {
            crc ^= (b << 8);
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ POLYNOMIAL;
                } else {
                    crc <<= 1;
                }
                crc &= 0xFFFF;
            }
        }
       return String.format("%04X", crc);

    }



}




