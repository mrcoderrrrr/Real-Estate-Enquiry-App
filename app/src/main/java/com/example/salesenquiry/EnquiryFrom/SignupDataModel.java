package com.example.salesenquiry.EnquiryFrom;

import androidx.room.PrimaryKey;

public class SignupDataModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    String FULLNAME;
    String USERNAME;
    String PASSWORD;

    //Constructor

    public SignupDataModel() {
        this.id = id;
        this.FULLNAME = FULLNAME;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    //getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFULLNAME() {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
