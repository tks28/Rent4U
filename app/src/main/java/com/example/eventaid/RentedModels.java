package com.example.eventaid;

public class RentedModels {
    private String rentedImg, mtvCarName, mtvBrand, mtvRenterName, mtvRenterPhoneNumber, mRentDate, mRentDays, mtvCarPlate, mtvCarTransmission, mtvCarDriven, mtvCarBody, mtvPricePerDay;

    //constructor
    public RentedModels() {

    }

    public RentedModels(String rentedImg, String mtvCarName, String mtvBrand, String mtvRenterName, String mtvRenterPhoneNumber,
                        String rentDate, String rentDays, String mtvCarPlate, String mtvCarTransmission, String mtvCarDriven, String mtvCarBody, String mtvPricePerDay) {
        this.rentedImg = rentedImg;
        this.mtvCarName = mtvCarName;
        this.mtvBrand = mtvBrand;
        this.mtvRenterName = mtvRenterName;
        this.mtvRenterPhoneNumber = mtvRenterPhoneNumber;
        this.mRentDate = rentDate;
        this.mRentDays = rentDays;
        this.mtvCarPlate = mtvCarPlate;
        this.mtvCarTransmission = mtvCarTransmission;
        this.mtvCarDriven = mtvCarDriven;
        this.mtvCarBody = mtvCarBody;
        this.mtvPricePerDay = mtvPricePerDay;
    }

    //getter and setter
    public String getRentedImg() {
        return rentedImg;
    }

    public void setRentedImg(String rentedImg) {
        this.rentedImg = rentedImg;
    }

    public String getMtvCarName() {
        return mtvCarName;
    }

    public void setMtvCarName(String mtvCarName) {
        this.mtvCarName = mtvCarName;
    }

    public String getMtvBrand() {
        return mtvBrand;
    }

    public void setMtvBrand(String mtvBrand) {
        this.mtvBrand = mtvBrand;
    }

    public String getMtvRenterName() {
        return mtvRenterName;
    }

    public void setMtvRenterName(String mtvRenterName) {
        this.mtvRenterName = mtvRenterName;
    }

    public String getMtvRenterPhoneNumber() {
        return mtvRenterPhoneNumber;
    }

    public void setMtvRenterPhoneNumber(String mtvRenterPhoneNumber) {
        this.mtvRenterPhoneNumber = mtvRenterPhoneNumber;
    }

    public String getRentDate() {
        return mRentDate;
    }

    public void setRentDate(String rentDate) {
        mRentDate = rentDate;
    }

    public String getRentDays() {
        return mRentDays;
    }

    public void setRentDays(String rentDays) {
        mRentDays = rentDays;
    }

    public String getMtvCarPlate() {
        return mtvCarPlate;
    }

    public void setMtvCarPlate(String mtvCarPlate) {
        this.mtvCarPlate = mtvCarPlate;
    }

    public String getMtvCarTransmission() {
        return mtvCarTransmission;
    }

    public void setMtvCarTransmission(String mtvCarTransmission) {
        this.mtvCarTransmission = mtvCarTransmission;
    }

    public String getMtvCarDriven() {
        return mtvCarDriven;
    }

    public void setMtvCarDriven(String mtvCarDriven) {
        this.mtvCarDriven = mtvCarDriven;
    }

    public String getMtvCarBody() {
        return mtvCarBody;
    }

    public void setMtvCarBody(String mtvCarBody) {
        this.mtvCarBody = mtvCarBody;
    }

    public String getMtvPricePerDay() {
        return mtvPricePerDay;
    }

    public void setMtvPricePerDay(String mtvPricePerDay) {
        this.mtvPricePerDay = mtvPricePerDay;
    }
}

