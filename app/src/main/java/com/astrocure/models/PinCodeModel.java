package com.astrocure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PinCodeModel {
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("Status")
    @Expose
    public String status;
    @SerializedName("PostOffice")
    @Expose
    public List<PostOffice> postOffice;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PostOffice> getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(List<PostOffice> postOffice) {
        this.postOffice = postOffice;
    }

    public class PostOffice {

        @SerializedName("Name")
        @Expose
        public String name;
        @SerializedName("Description")
        @Expose
        public String description;
        @SerializedName("Pincode")
        @Expose
        public String pINCode;
        @SerializedName("BranchType")
        @Expose
        public String branchType;
        @SerializedName("DeliveryStatus")
        @Expose
        public String deliveryStatus;
        @SerializedName("Circle")
        @Expose
        public String circle;
        @SerializedName("District")
        @Expose
        public String district;
        @SerializedName("Division")
        @Expose
        public String division;
        @SerializedName("Region")
        @Expose
        public String region;
        @SerializedName("State")
        @Expose
        public String state;
        @SerializedName("Country")
        @Expose
        public String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getpINCode() {
            return pINCode;
        }

        public void setpINCode(String pINCode) {
            this.pINCode = pINCode;
        }

        public String getBranchType() {
            return branchType;
        }

        public void setBranchType(String branchType) {
            this.branchType = branchType;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getCircle() {
            return circle;
        }

        public void setCircle(String circle) {
            this.circle = circle;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDivision() {
            return division;
        }

        public void setDivision(String division) {
            this.division = division;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

}
