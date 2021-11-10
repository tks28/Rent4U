package com.example.eventaid;

public class InventoryModels {
    private String logoImg;
    private String mtvBrand;

    //constructor
    public InventoryModels() {

    }

    public InventoryModels(String mtvBrand, String logoImg) {
        this.logoImg = logoImg;
        this.mtvBrand = mtvBrand;
    }

    //getter and setter
    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getMtvBrand() {
        return mtvBrand;
    }

    public void setMtvBrand(String mtvBrand) {
        this.mtvBrand = mtvBrand;
    }
}
