package com.astrocure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanetsRequestModel {
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("month")
    @Expose
    public Integer month;
    @SerializedName("date")
    @Expose
    public Integer date;
    @SerializedName("hours")
    @Expose
    public Integer hours;
    @SerializedName("minutes")
    @Expose
    public Integer minutes;
    @SerializedName("seconds")
    @Expose
    public Integer seconds;
    @SerializedName("latitude")
    @Expose
    public Float latitude;
    @SerializedName("longitude")
    @Expose
    public Float longitude;
    @SerializedName("timezone")
    @Expose
    public Float timezone;
    @SerializedName("settings")
    @Expose
    public Settings settings;
    public static class Settings {

        @SerializedName("observation_point")
        @Expose
        public String observationPoint;
        @SerializedName("ayanamsha")
        @Expose
        public String ayanamsha;

        public Settings(String observationPoint, String ayanamsha) {
            this.observationPoint = observationPoint;
            this.ayanamsha = ayanamsha;
        }
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setTimezone(Float timezone) {
        this.timezone = timezone;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
