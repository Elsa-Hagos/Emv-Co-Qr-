package com.lib.emv.co.MP.Dto;

import lombok.*;

@Data

public class QrCodeRequest {

    private String pointOfInitiationMethod;
    private String transactionAmount;
    private String merchantAccountNumber;
    private String TipOrConvenienceIndicator;
    private String FixedConvenienceFee;
    private String FixedConvenienceFeePercentage;
    private String merchantCategoryCode;
    private String MerchantName_DBA;
    private String City;
    private  String StoreLabel ;
    private  String TerminalNumber;
    private String TransactionPurpose ;
    private String AdditionalConsumerDataRequest;
    private String MerchantTaxID;
    private String ContextOfTransaction;
    private String TransactionTypeCode;
    private String Guid;

//   private String merchantName;
    // private String MerchantChannel;
    // private String LoyaltyNumber ;
    //   private  String ReferenceLabel;
    // private  String CustomerLabel
    //   private String Currency ;
    //   private String Country;
    // private String BillNumber;
    // private  String MobileNumber ;
    //    private String GUID;
    //   private String BIC;


    public String getPointOfInitiationMethod() {
        return pointOfInitiationMethod;
    }

    public void setPointOfInitiationMethod(String pointOfInitiationMethod) {
        this.pointOfInitiationMethod = pointOfInitiationMethod;
    }
//
//    public String getMerchantName() {
//        return merchantName;
//    }
//
//    public void setMerchantName(String merchantName) {
//        this.merchantName = merchantName;
//    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

//    public String getGUID() {
//        return GUID;
//    }
//
//    public void setGUID(String GUID) {
//        this.GUID = GUID;
//    }
//
//    public String getBIC() {
//        return BIC;
//    }
//
//    public void setBIC(String BIC) {
//        this.BIC = BIC;
//    }

    public String getMerchantAccountNumber() {
        return merchantAccountNumber;
    }

    public void setMerchantAccountNumber(String merchantAccountNumber) {
        this.merchantAccountNumber = merchantAccountNumber;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }


    public String getContextOfTransaction() {
        return ContextOfTransaction;
    }

    public void setContextOfTransaction(String contextOfTransaction) {
        ContextOfTransaction = contextOfTransaction;
    }

    public String getTransactionTypeCode() {
        return TransactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        TransactionTypeCode = transactionTypeCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

//    public String getCurrency() {
//        return Currency;
//    }
//
//    public void setCurrency(String currency) {
//        Currency = currency;
//    }
//
//    public String getCountry() {
//        return Country;
//    }
//
//    public void setCountry(String country) {
//        Country = country;
//    }

    public String getTipOrConvenienceIndicator() {
        return TipOrConvenienceIndicator;
    }

    public void setTipOrConvenienceIndicator(String tipOrConvenienceIndicator) {
        TipOrConvenienceIndicator = tipOrConvenienceIndicator;
    }

    public String getFixedConvenienceFee() {
        return FixedConvenienceFee;
    }

    public void setFixedConvenienceFee(String fixedConvenienceFee) {
        FixedConvenienceFee = fixedConvenienceFee;
    }

    public String getFixedConvenienceFeePercentage() {
        return FixedConvenienceFeePercentage;
    }

    public void setFixedConvenienceFeePercentage(String fixedConvenienceFeePercentage) {
        FixedConvenienceFeePercentage = fixedConvenienceFeePercentage;
    }

    public String getMerchantName_DBA() {
        return MerchantName_DBA;
    }

    public void setMerchantName_DBA(String merchantName_DBA) {
        MerchantName_DBA = merchantName_DBA;
    }

//    public String getBillNumber() {
//        return BillNumber;
//    }
//
//    public void setBillNumber(String billNumber) {
//        BillNumber = billNumber;
//    }
//
//    public String getMobileNumber() {
//        return MobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        MobileNumber = mobileNumber;
//    }

    public String getStoreLabel() {
        return StoreLabel;
    }

    public void setStoreLabel(String storeLabel) {
        StoreLabel = storeLabel;
    }

//    public String getLoyaltyNumber() {
//        return LoyaltyNumber;
//    }
//
//    public void setLoyaltyNumber(String loyaltyNumber) {
//        LoyaltyNumber = loyaltyNumber;
//    }
//
//    public String getReferenceLabel() {
//        return ReferenceLabel;
//    }
//
//    public void setReferenceLabel(String referenceLabel) {
//        ReferenceLabel = referenceLabel;
//    }
//
//    public String getCustomerLabel() {
//        return CustomerLabel;
//    }
//
//    public void setCustomerLabel(String customerLabel) {
//        CustomerLabel = customerLabel;
//    }

    public String getTerminalNumber() {
        return TerminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        TerminalNumber = terminalNumber;
    }

    public String getTransactionPurpose() {
        return TransactionPurpose;
    }

    public void setTransactionPurpose(String transactionPurpose) {
        TransactionPurpose = transactionPurpose;
    }

    public String getAdditionalConsumerDataRequest() {
        return AdditionalConsumerDataRequest;
    }

    public void setAdditionalConsumerDataRequest(String additionalConsumerDataRequest) {
        AdditionalConsumerDataRequest = additionalConsumerDataRequest;
    }

    public String getMerchantTaxID() {
        return MerchantTaxID;
    }

    public void setMerchantTaxID(String merchantTaxID) {
        MerchantTaxID = merchantTaxID;
    }

//    public String getMerchantChannel() {
//        return MerchantChannel;
//    }
//
//    public void setMerchantChannel(String merchantChannel) {
//        MerchantChannel = merchantChannel;
//    }
}

