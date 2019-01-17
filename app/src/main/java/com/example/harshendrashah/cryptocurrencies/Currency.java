package com.example.harshendrashah.cryptocurrencies;

public class Currency {

    String fullName;
    String code;
    String currentRate;
    String openDay;
    String lowDay;
    String highDay;
    String imageURL;

    public Currency() {
    }

    public Currency(String fullName, String code, String currentRate, String lowDay,
                    String highDay, String openDay, String imageURL) {
        this.fullName = fullName;
        this.code = code;
        this.currentRate = currentRate;
        this.lowDay = lowDay;
        this.highDay = highDay;
        this.openDay = openDay;
        this.imageURL = imageURL;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(String currentRate) {
        this.currentRate = currentRate;
    }

    public String getLowDay() {
        return lowDay;
    }

    public void setLowDay(String lowDay) {
        this.lowDay = lowDay;
    }

    public String getHighDay() {
        return highDay;
    }

    public void setHighDay(String highDay) {
        this.highDay = highDay;
    }

    public String getOpenDay() {
        return openDay;
    }

    public void setOpenDay(String openDay) {
        this.openDay = openDay;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}