package com.example.eventaid;

public class DamagedCarModels {
    private String damagedCarImg, mtvDCCarName, mtvDCBrand, mtvTechnicianName, mtvTechnicianPhoneNumber,
            mSentRepairDate, mRepairDays, mtvDCCarPlate, mtvDCCarTransmission, mtvDCCarDriven, mtvDCCarBody, mtvDCPricePerDay;

    //constructor
    public DamagedCarModels() {
    }

    public DamagedCarModels(String damagedCarImg, String mtvDCCarName, String mtvDCBrand, String mtvTechnicianName, String mtvTechnicianPhoneNumber,
                            String sentRepairDate, String repairDays, String mtvDCCarPlate, String mtvDCCarTransmission, String mtvDCCarDriven, String mtvDCCarBody, String mtvDCPricePerDay) {
        this.damagedCarImg = damagedCarImg;
        this.mtvDCCarName = mtvDCCarName;
        this.mtvDCBrand = mtvDCBrand;
        this.mtvTechnicianName = mtvTechnicianName;
        this.mtvTechnicianPhoneNumber = mtvTechnicianPhoneNumber;
        this.mSentRepairDate = sentRepairDate;
        this.mRepairDays = repairDays;
        this.mtvDCCarPlate = mtvDCCarPlate;
        this.mtvDCCarTransmission = mtvDCCarTransmission;
        this.mtvDCCarDriven = mtvDCCarDriven;
        this.mtvDCCarBody = mtvDCCarBody;
        this.mtvDCPricePerDay = mtvDCPricePerDay;
    }

    //getter and setter
    public String getDamagedCarImg() {
        return damagedCarImg;
    }

    public void setDamagedCarImg(String damagedCarImg) {
        this.damagedCarImg = damagedCarImg;
    }

    public String getMtvDCCarName() {
        return mtvDCCarName;
    }

    public void setMtvDCCarName(String mtvDCCarName) {
        this.mtvDCCarName = mtvDCCarName;
    }

    public String getMtvDCBrand() {
        return mtvDCBrand;
    }

    public void setMtvDCBrand(String mtvDCBrand) {
        this.mtvDCBrand = mtvDCBrand;
    }

    public String getMtvTechnicianName() {
        return mtvTechnicianName;
    }

    public void setMtvTechnicianName(String mtvTechnicianName) {
        this.mtvTechnicianName = mtvTechnicianName;
    }

    public String getMtvTechnicianPhoneNumber() {
        return mtvTechnicianPhoneNumber;
    }

    public void setMtvTechnicianPhoneNumber(String mtvTechnicianPhoneNumber) {
        this.mtvTechnicianPhoneNumber = mtvTechnicianPhoneNumber;
    }

    public String getSentRepairDate() {
        return mSentRepairDate;
    }

    public void setSentRepairDate(String sentRepairDate) {
        mSentRepairDate = sentRepairDate;
    }

    public String getRepairDays() {
        return mRepairDays;
    }

    public void setRepairDays(String repairDays) {
        mRepairDays = repairDays;
    }

    public String getMtvDCCarPlate() {
        return mtvDCCarPlate;
    }

    public void setMtvDCCarPlate(String mtvDCCarPlate) {
        this.mtvDCCarPlate = mtvDCCarPlate;
    }

    public String getMtvDCCarTransmission() {
        return mtvDCCarTransmission;
    }

    public void setMtvDCCarTransmission(String mtvDCCarTransmission) {
        this.mtvDCCarTransmission = mtvDCCarTransmission;
    }

    public String getMtvDCCarDriven() {
        return mtvDCCarDriven;
    }

    public void setMtvDCCarDriven(String mtvDCCarDriven) {
        this.mtvDCCarDriven = mtvDCCarDriven;
    }

    public String getMtvDCCarBody() {
        return mtvDCCarBody;
    }

    public void setMtvDCCarBody(String mtvDCCarBody) {
        this.mtvDCCarBody = mtvDCCarBody;
    }

    public String getMtvDCPricePerDay() {
        return mtvDCPricePerDay;
    }

    public void setMtvDCPricePerDay(String mtvDCPricePerDay) {
        this.mtvDCPricePerDay = mtvDCPricePerDay;
    }
}