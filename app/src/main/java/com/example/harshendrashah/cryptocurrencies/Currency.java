package com.example.harshendrashah.cryptocurrencies;

public class Currency {

    String name;
    String currentRate;
    String openDay;
    String lowDay;
    String highDay;
    String imageURL;

    public Currency() {
    }

    public Currency(String name, String currentRate, String lowDay,
                    String highDay, String openDay, String imageURL) {
        this.name = name;
        this.currentRate = currentRate;
        this.lowDay = lowDay;
        this.highDay = highDay;
        this.openDay = openDay;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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