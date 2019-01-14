package com.example.harshendrashah.cryptocurrencies;

public class Currency {

    String name;
    String currentRate;
    String changeInHour;
    String changeInDay;
    String changeInWeek;
    String imageURL;

    public Currency() {
    }

    public Currency(String name, String currentRate, String changeInHour,
                    String changeInDay, String changeInWeek, String imageURL) {
        this.name = name;
        this.currentRate = currentRate;
        this.changeInHour = changeInHour;
        this.changeInDay = changeInDay;
        this.changeInWeek = changeInWeek;
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

    public String getChangeInHour() {
        return changeInHour;
    }

    public void setChangeInHour(String changeInHour) {
        this.changeInHour = changeInHour;
    }

    public String getChangeInDay() {
        return changeInDay;
    }

    public void setChangeInDay(String changeInDay) {
        this.changeInDay = changeInDay;
    }

    public String getChangeInWeek() {
        return changeInWeek;
    }

    public void setChangeInWeek(String changeInWeek) {
        this.changeInWeek = changeInWeek;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}