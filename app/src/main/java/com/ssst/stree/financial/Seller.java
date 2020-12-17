package com.ssst.stree.financial;

public class Seller {
    private final String email;
    private final String businessName;
    private final String businessContact;
    private final String businessInformation;
    private final String businessUpi;
    private final String businessAcc;
    private final String businessIFSC;
    private final String bankName;
    private final String bankBranch;
    private final String bankAccHolderName;
    private final String bankAddress;

    public Seller(String email,String businessName, String businessContact, String businessInformation, String businessUpi, String businessAcc, String businessIFSC, String bankName, String bankBranch, String bankAccHolderName, String bankAddress) {
        this.email = email;
        this.businessName = businessName;
        this.businessContact = businessContact;
        this.businessInformation = businessInformation;
        this.businessUpi = businessUpi;
        this.businessAcc = businessAcc;
        this.businessIFSC = businessIFSC;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.bankAccHolderName = bankAccHolderName;
        this.bankAddress = bankAddress;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessContact() {
        return businessContact;
    }

    public String getBusinessInformation() {
        return businessInformation;
    }

    public String getBusinessUpi() {
        return businessUpi;
    }

    public String getBusinessAcc() {
        return businessAcc;
    }

    public String getBusinessIFSC() {
        return businessIFSC;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public String getBankAccHolderName() {
        return bankAccHolderName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public String getEmail() {
        return email;
    }
}
