package com.astrocure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoroscopeResponseModel {
    @SerializedName("status")
    @Expose
    public int Status;
    @SerializedName("response")
    @Expose
    public PredictionResponseModel.Response response;


}
