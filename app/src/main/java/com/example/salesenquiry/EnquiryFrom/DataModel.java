package com.example.salesenquiry.EnquiryFrom;

import androidx.room.PrimaryKey;

public class DataModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    //Personal Details
    String FNAME;
    String LNAME;
    String LOCALITY;
    String CITY;
    int PINCODE;
    String TIME_TO_CALL;
    String PHONE;
    String ALTPHONE;
    String EMAIL;
    //Personal Details
    String GENDER;
    String STATUS;
    String OCCUPATION;
    String COMPANY_NAME;
    String DESIGNATION;
    String WORK_NATURE;
    String BUSINESS_LOCATION;
    //Need And Requirements
    String CONFIG_ONE;
    String CONFIG_TWO;
    String CONFIG_THREE;
    String CONFIG_OTHER;
    String SPECIFY;
    String BUDGET;
    String LOAN;
    String BANKNAME;
    String PURCHASE;
    String RESIDENTAL;
    //About Project
    String SOURCE_ADV;
    String NEWSPAPER_ADV;
    String NEWSPAPER_INSERT;
    String HORDING;
    String ADVERTISEMENT;
    String TELECALLING;
    String BROKER_FNAME;
    String BROKER_LNAME;


    //Constructor
    public DataModel() {
        this.id = id;
        this.FNAME = FNAME;
        this.LNAME = LNAME;
        this.LOCALITY = LOCALITY;
        this.CITY = CITY;
        this.PINCODE = PINCODE;
        this.TIME_TO_CALL = TIME_TO_CALL;
        this.PHONE = PHONE;
        this.ALTPHONE = ALTPHONE;
        this.EMAIL = EMAIL;
        this.GENDER = GENDER;
        this.STATUS = STATUS;
        this.OCCUPATION = OCCUPATION;
        this.COMPANY_NAME = COMPANY_NAME;
        this.DESIGNATION = DESIGNATION;
        this.WORK_NATURE = WORK_NATURE;
        this.BUSINESS_LOCATION = BUSINESS_LOCATION;
        this.CONFIG_ONE = CONFIG_ONE;
        this.CONFIG_TWO = CONFIG_TWO;
        this.CONFIG_THREE = CONFIG_THREE;
        this.CONFIG_OTHER = CONFIG_OTHER;
        this.SPECIFY = SPECIFY;
        this.BUDGET = BUDGET;
        this.LOAN = LOAN;
        this.BANKNAME = BANKNAME;
        this.PURCHASE = PURCHASE;
        this.RESIDENTAL = RESIDENTAL;
        this.NEWSPAPER_ADV = NEWSPAPER_ADV;
        this.NEWSPAPER_INSERT = NEWSPAPER_INSERT;
        this.HORDING = HORDING;
        this.ADVERTISEMENT = ADVERTISEMENT;
        this.TELECALLING = TELECALLING;
        this.BROKER_FNAME = BROKER_FNAME;
        this.BROKER_LNAME = BROKER_LNAME;
        this.SOURCE_ADV =SOURCE_ADV;
    }


    //getter settter


    public String getSOURCE_ADV() {
        return SOURCE_ADV;
    }

    public void setSOURCE_ADV(String SOURCE_ADV) {
        this.SOURCE_ADV = SOURCE_ADV;
    }

    public String getBROKER_FNAME() {
        return BROKER_FNAME;
    }

    public void setBROKER_FNAME(String BROKER_FNAME) {
        this.BROKER_FNAME = BROKER_FNAME;
    }

    public String getBROKER_LNAME() {
        return BROKER_LNAME;
    }

    public void setBROKER_LNAME(String BROKER_LNAME) {
        this.BROKER_LNAME = BROKER_LNAME;
    }

    public String getCONFIG_OTHER() {
        return CONFIG_OTHER;
    }

    public void setCONFIG_OTHER(String CONFIG_OTHER) {
        this.CONFIG_OTHER = CONFIG_OTHER;
    }

    public String getCONFIG_ONE() {
        return CONFIG_ONE;
    }

    public void setCONFIG_ONE(String CONFIG_ONE) {
        this.CONFIG_ONE = CONFIG_ONE;
    }

    public String getCONFIG_TWO() {
        return CONFIG_TWO;
    }

    public void setCONFIG_TWO(String CONFIG_TWO) {
        this.CONFIG_TWO = CONFIG_TWO;
    }

    public String getCONFIG_THREE() {
        return CONFIG_THREE;
    }

    public void setCONFIG_THREE(String CONFIG_THREE) {
        this.CONFIG_THREE = CONFIG_THREE;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getLNAME() {
        return LNAME;
    }

    public void setLNAME(String LNAME) {
        this.LNAME = LNAME;
    }

    public String getLOCALITY() {
        return LOCALITY;
    }

    public void setLOCALITY(String LOCALITY) {
        this.LOCALITY = LOCALITY;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public int getPINCODE() {
        return PINCODE;
    }

    public void setPINCODE(int PINCODE) {
        this.PINCODE = PINCODE;
    }

    public String getTIME_TO_CALL() {
        return TIME_TO_CALL;
    }

    public void setTIME_TO_CALL(String TIME_TO_CALL) {
        this.TIME_TO_CALL = TIME_TO_CALL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getALTPHONE() {
        return ALTPHONE;
    }

    public void setALTPHONE(String ALTPHONE) {
        this.ALTPHONE = ALTPHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getOCCUPATION() {
        return OCCUPATION;
    }

    public void setOCCUPATION(String OCCUPATION) {
        this.OCCUPATION = OCCUPATION;
    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public String getWORK_NATURE() {
        return WORK_NATURE;
    }

    public void setWORK_NATURE(String WORK_NATURE) {
        this.WORK_NATURE = WORK_NATURE;
    }

    public String getBUSINESS_LOCATION() {
        return BUSINESS_LOCATION;
    }

    public void setBUSINESS_LOCATION(String BUSINESS_LOCATION) {
        this.BUSINESS_LOCATION = BUSINESS_LOCATION;
    }

    public String getSPECIFY() {
        return SPECIFY;
    }

    public void setSPECIFY(String SPECIFY) {
        this.SPECIFY = SPECIFY;
    }

    public String getBUDGET() {
        return BUDGET;
    }

    public void setBUDGET(String BUDGET) {
        this.BUDGET = BUDGET;
    }

    public String getLOAN() {
        return LOAN;
    }

    public void setLOAN(String LOAN) {
        this.LOAN = LOAN;
    }

    public String getBANKNAME() {
        return BANKNAME;
    }

    public void setBANKNAME(String BANKNAME) {
        this.BANKNAME = BANKNAME;
    }

    public String getPURCHASE() {
        return PURCHASE;
    }

    public void setPURCHASE(String PURCHASE) {
        this.PURCHASE = PURCHASE;
    }

    public String getRESIDENTAL() {
        return RESIDENTAL;
    }

    public void setRESIDENTAL(String RESIDENTAL) {
        this.RESIDENTAL = RESIDENTAL;
    }

    public String getNEWSPAPER_ADV() {
        return NEWSPAPER_ADV;
    }

    public void setNEWSPAPER_ADV(String NEWSPAPER_ADV) {
        this.NEWSPAPER_ADV = NEWSPAPER_ADV;
    }

    public String getNEWSPAPER_INSERT() {
        return NEWSPAPER_INSERT;
    }

    public void setNEWSPAPER_INSERT(String NEWSPAPER_INSERT) {
        this.NEWSPAPER_INSERT = NEWSPAPER_INSERT;
    }

    public String getHORDING() {
        return HORDING;
    }

    public void setHORDING(String HORDING) {
        this.HORDING = HORDING;
    }

    public String getADVERTISEMENT() {
        return ADVERTISEMENT;
    }

    public void setADVERTISEMENT(String ADVERTISEMENT) {
        this.ADVERTISEMENT = ADVERTISEMENT;
    }

    public String getTELECALLING() {
        return TELECALLING;
    }

    public void setTELECALLING(String TELECALLING) {
        this.TELECALLING = TELECALLING;
    }


}
