package com.astrocure.models;

public class CompatibilityZodiacModel {
    String zodiacName;
    int zodiacImg;

    public CompatibilityZodiacModel(String zodiacName, int zodiacImg) {
        this.zodiacName = zodiacName;
        this.zodiacImg = zodiacImg;
    }

    public String getZodiacName() {
        return zodiacName;
    }

    public void setZodiacName(String zodiacName) {
        this.zodiacName = zodiacName;
    }

    public int getZodiacImg() {
        return zodiacImg;
    }

    public void setZodiacImg(int zodiacImg) {
        this.zodiacImg = zodiacImg;
    }
}
