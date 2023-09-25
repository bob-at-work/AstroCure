package com.astrocure.models;

import java.io.Serializable;
import java.util.List;

public class PlanModel implements Serializable {
    String planName;
    int planImage;
    String activePrice;
    String actualPrice;
    boolean isLimited;
    List<FeaturesName> featuresName;

    public PlanModel(String planName, int planImage, String activePrice, String actualPrice, boolean isLimited, List<FeaturesName> featuresName) {
        this.planName = planName;
        this.planImage = planImage;
        this.activePrice = activePrice;
        this.actualPrice = actualPrice;
        this.isLimited = isLimited;
        this.featuresName = featuresName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanImage() {
        return planImage;
    }

    public void setPlanImage(int planImage) {
        this.planImage = planImage;
    }

    public String getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(String activePrice) {
        this.activePrice = activePrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public boolean isLimited() {
        return isLimited;
    }

    public void setLimited(boolean limited) {
        isLimited = limited;
    }

    public List<FeaturesName> getFeaturesName() {
        return featuresName;
    }

    public void setFeaturesName(List<FeaturesName> featuresName) {
        this.featuresName = featuresName;
    }

    public static class FeaturesName implements Serializable {
        String featureName;
        int featureImg;

        public FeaturesName(String featureName, int featureImg) {
            this.featureName = featureName;
            this.featureImg = featureImg;
        }

        public String getFeatureName() {
            return featureName;
        }

        public void setFeatureName(String featureName) {
            this.featureName = featureName;
        }

        public int getFeatureImg() {
            return featureImg;
        }

        public void setFeatureImg(int featureImg) {
            this.featureImg = featureImg;
        }
    }
}
