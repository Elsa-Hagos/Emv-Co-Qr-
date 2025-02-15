package com.lib.emv.co.MP.Service;
import  com.lib.emv.co.MP.Dto.QrCodeRequest;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@org.springframework.stereotype.Service
public class DataProcessing {
    private TLVService tLVService;

        public DataProcessing(TLVService tLVService ) {
            this.tLVService = tLVService;
        }
        public String dataValidation(QrCodeRequest qrCodeRequest) {
            String pointOfInitiation = qrCodeRequest.getPointOfInitiationMethod();
            String accountNumber = qrCodeRequest.getMerchantAccountNumber();
            if (accountNumber == null || accountNumber.isEmpty()) {
                return "Error: Account number is required.";
            }


//
             String merchantGUID = qrCodeRequest.getGuid();
             String Guid = gUID(merchantGUID);

            String mspBIC = "LIBSETTA";
            String merchantCategoryCode = qrCodeRequest.getMerchantCategoryCode();
            if (merchantCategoryCode == null || merchantCategoryCode.isEmpty()) {
                return "Error: Merchant Category Code is required.";
            }
            if (!merchantCategoryCode.matches("\\d{4}")) {
                return "Error: Merchant Category Code must be a 4-digit number.";
            }

            String currency = "230";
            String transactionAmount = qrCodeRequest.getTransactionAmount();
            String amountValidation = validateAmount(transactionAmount);
            if (amountValidation != null) {
                return amountValidation;
            }

            String tipOrConvenienceIndicator = qrCodeRequest.getTipOrConvenienceIndicator();
            String fixedConvenienceFee = qrCodeRequest.getFixedConvenienceFee();
            String fixedConvenienceFeePercentage = qrCodeRequest.getFixedConvenienceFeePercentage();

            String tipValidationResponse = validateTipOrConvenienceData(tipOrConvenienceIndicator, fixedConvenienceFee, fixedConvenienceFeePercentage);
            if (tipValidationResponse != null) {
                return tipValidationResponse;
            }

            String countryCode = "ET";

            String dbaName = qrCodeRequest.getMerchantName_DBA();
            if (dbaName == null || dbaName.isEmpty()) {
                return "Error:  Name (DBA) is required.";
            }

            String city = qrCodeRequest.getCity();
            if (city == null || city.isEmpty()) {
                return "Error:  City is required.";
            }
            String storeLabel = qrCodeRequest.getStoreLabel();
            String terminalNumber = qrCodeRequest.getTerminalNumber();
            String transactionPurpose = qrCodeRequest.getTransactionPurpose();
            if (transactionPurpose == null || transactionPurpose.isEmpty()) {
                return "Error: Transaction Purpose is required.";
            }
            String additionalConsumerDataRequest = qrCodeRequest.getAdditionalConsumerDataRequest();
            String merchantTaxID = qrCodeRequest.getMerchantTaxID();

            String qrString = tLVService.generateEMVCoQRString(pointOfInitiation,Guid , mspBIC, accountNumber,
                    merchantCategoryCode, currency, transactionAmount, tipOrConvenienceIndicator, fixedConvenienceFee, fixedConvenienceFeePercentage, countryCode, dbaName, city,
                    storeLabel, terminalNumber, transactionPurpose,
                    additionalConsumerDataRequest, merchantTaxID);
            return qrString;
        }
    private String validateTipOrConvenienceData(String tipOrConvenienceIndicator, String fixedConvenienceFee, String fixedConvenienceFeePercentage) {

        if (tipOrConvenienceIndicator != null && !tipOrConvenienceIndicator.matches("01|02|03")) {
            return "Invalid tipOrConvenienceIndicator. Allowed values are null, 01, 02, 03.";
        }

        // Rule 4: If tipOrConvenienceIndicator is null or empty, both fees must be null
        if (tipOrConvenienceIndicator == null || tipOrConvenienceIndicator.isEmpty()) {
            if ((fixedConvenienceFee != null && !fixedConvenienceFee.isEmpty()) ||
                    (fixedConvenienceFeePercentage != null && !fixedConvenienceFeePercentage.isEmpty())) {
                return "When tipOrConvenienceIndicator is null or empty, both fixedConvenienceFee and fixedConvenienceFeePercentage must be null or empty.";
            }
            return null;
        }

        // Rule 2: Validate fixedConvenienceFee as a double if provided
        if (fixedConvenienceFee != null && !fixedConvenienceFee.isEmpty()) {
            if (!isValidDouble(fixedConvenienceFee)) {
                return "fixedConvenienceFee must be a valid double value.";
            }
        }

        // Rule 3: Validate fixedConvenienceFeePercentage between 1 and 100 if provided
        if (fixedConvenienceFeePercentage != null && !fixedConvenienceFeePercentage.isEmpty()) {
            if (!isValidPercentage(fixedConvenienceFeePercentage)) {
                return "fixedConvenienceFeePercentage must be a valid percentage between 1 and 100.";
            }
        }

        // Rule 5: For tipOrConvenienceIndicator "02"
        if ("02".equals(tipOrConvenienceIndicator)) {
            if (fixedConvenienceFee == null || fixedConvenienceFee.isEmpty()) {
                return "For tipOrConvenienceIndicator 02, fixedConvenienceFee must be provided.";
            }
            if (fixedConvenienceFeePercentage != null && !fixedConvenienceFeePercentage.isEmpty()) {
                return "For tipOrConvenienceIndicator 02, fixedConvenienceFeePercentage must be null or empty.";
            }
        }

        // Rule 6: For tipOrConvenienceIndicator "03"
        if ("03".equals(tipOrConvenienceIndicator)) {
            if (fixedConvenienceFeePercentage == null || fixedConvenienceFeePercentage.isEmpty()) {
                return "For tipOrConvenienceIndicator 03, fixedConvenienceFeePercentage must be provided.";
            }
            if (fixedConvenienceFee != null && !fixedConvenienceFee.isEmpty()) {
                return "For tipOrConvenienceIndicator 03, fixedConvenienceFee must be null or empty.";
            }
        }

        // Rule 7: For tipOrConvenienceIndicator "01"
        if ("01".equals(tipOrConvenienceIndicator)) {
            if (fixedConvenienceFee != null && !fixedConvenienceFee.isEmpty()) {
                return "For tipOrConvenienceIndicator 01, fixedConvenienceFee must be null or empty.";
            }
            if (fixedConvenienceFeePercentage != null && !fixedConvenienceFeePercentage.isEmpty()) {
                return "For tipOrConvenienceIndicator 01, fixedConvenienceFeePercentage must be null or empty.";
            }
        }

        return null;
    }

    private String validateAmount(String amount) {
            if (amount != null && !amount.isEmpty()) {
                try {
                    Double.parseDouble(amount);
                } catch (NumberFormatException e) {
                    return "Invalid transaction amount.";
                }
            }
            return null;
        }

     private boolean isValidDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
     }
    private boolean isValidPercentage(String value) {
        try {
            double percentage = Double.parseDouble(value);
            return percentage >= 1 && percentage <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String gUID(String merchantGUID) {
        String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if (merchantGUID.matches(uuidRegex)) {
            UUID MGUID = UUID.fromString(merchantGUID);
            return MGUID.toString().replace("-", "");
        } else {
            UUID customerIDuuid = UUID.nameUUIDFromBytes(merchantGUID.getBytes(StandardCharsets.UTF_8));
            return customerIDuuid.toString().replace("-", "");
        }
    }

}






