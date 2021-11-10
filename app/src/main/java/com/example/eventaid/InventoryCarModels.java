package com.example.eventaid;

public class InventoryCarModels {
    private String inventoryCarName, carImg, mtvInventoryCarBrand, mtvInventoryModel, mtvInventoryYear,
            mtvInventoryCarPlate, mtvInventoryCarTransmission, mtvInventoryCarDriven, mtvInventoryCarBody, mtvInventoryPricePerDay, status;

    //constructor
    public InventoryCarModels() {

    }

    public InventoryCarModels(String carImg, String mtvInventoryCarBrand, String mtvInventoryModel, String mtvInventoryYear,
                              String mtvInventoryCarPlate, String mtvInventoryCarTransmission, String mtvInventoryCarDriven, String mtvInventoryCarBody, String mtvInventoryPricePerDay, String mStatus) {
        this.carImg = carImg;
        this.mtvInventoryCarBrand = mtvInventoryCarBrand;
        this.mtvInventoryModel = mtvInventoryModel;
        this.mtvInventoryYear = mtvInventoryYear;
        this.inventoryCarName = getMtvInventoryCarName();
        this.mtvInventoryCarPlate = mtvInventoryCarPlate;
        this.mtvInventoryCarTransmission = mtvInventoryCarTransmission;
        this.mtvInventoryCarDriven = mtvInventoryCarDriven;
        this.mtvInventoryCarBody = mtvInventoryCarBody;
        this.mtvInventoryPricePerDay = mtvInventoryPricePerDay;
        this.status = mStatus;
    }

    //getter and setter
    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public String getMtvInventoryCarBrand() {
        return mtvInventoryCarBrand;
    }

    public void setMtvInventoryCarBrand(String mtvInventoryCarBrand) {
        this.mtvInventoryCarBrand = mtvInventoryCarBrand;
    }

    public String getMtvInventoryModel() {
        return mtvInventoryModel;
    }

    public void setMtvInventoryModel(String mtvInventoryModel) {
        this.mtvInventoryModel = mtvInventoryModel;
    }

    public String getMtvInventoryYear() {
        return mtvInventoryYear;
    }

    public void setMtvInventoryYear(String mtvInventoryYear) {
        this.mtvInventoryYear = mtvInventoryYear;
    }

    public String getMtvInventoryCarName(){
        String carName = getMtvInventoryCarBrand() + " " + getMtvInventoryModel() + " " + getMtvInventoryYear();
        return carName;
    }

    public String getMtvInventoryCarPlate() {
        return mtvInventoryCarPlate;
    }

    public void setMtvInventoryCarPlate(String mtvInventoryCarPlate) {
        this.mtvInventoryCarPlate = mtvInventoryCarPlate;
    }

    public String getMtvInventoryCarTransmission() {
        return mtvInventoryCarTransmission;
    }

    public void setMtvInventoryCarTransmission(String mtvInventoryCarTransmission) {
        this.mtvInventoryCarTransmission = mtvInventoryCarTransmission;
    }

    public String getMtvInventoryCarDriven() {
        return mtvInventoryCarDriven;
    }

    public void setMtvInventoryCarDriven(String mtvInventoryCarDriven) {
        this.mtvInventoryCarDriven = mtvInventoryCarDriven;
    }

    public String getMtvInventoryCarBody() {
        return mtvInventoryCarBody;
    }

    public void setMtvInventoryCarBody(String mtvInventoryCarBody) {
        this.mtvInventoryCarBody = mtvInventoryCarBody;
    }

    public String getMtvInventoryPricePerDay() {
        return mtvInventoryPricePerDay;
    }

    public void setMtvInventoryPricePerDay(String mtvInventoryPricePerDay) {
        this.mtvInventoryPricePerDay = mtvInventoryPricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
