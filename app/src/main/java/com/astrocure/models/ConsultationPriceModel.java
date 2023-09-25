package com.astrocure.models;

public class ConsultationPriceModel {
    String pricePerMin;
    String backgroundColor;
    String time;
    String price;

    public ConsultationPriceModel(String pricePerMin, String backgroundColor, String time, String price) {
        this.pricePerMin = pricePerMin;
        this.backgroundColor = backgroundColor;
        this.time = time;
        this.price = price;
    }

    public String getPricePerMin() {
        return pricePerMin;
    }

    public void setPricePerMin(String pricePerMin) {
        this.pricePerMin = pricePerMin;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
