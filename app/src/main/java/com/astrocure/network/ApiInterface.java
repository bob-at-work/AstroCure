package com.astrocure.network;

import com.astrocure.models.HoroscopeResponseModel;
import com.astrocure.models.PinCodeModel;
import com.astrocure.models.PlanetsRequestModel;
import com.astrocure.models.PlanetsResponseModel;
import com.astrocure.models.PredictionResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    /*https://vedicastroapi.com/*/
    @GET("prediction/daily-sun")
    Call<PredictionResponseModel> predictionDailySun(@Query("api_key") String api_key,
                                                     @Query("date") String date,
                                                     @Query("zodiac") String zodiac,
                                                     @Query("show_same") boolean showSame,
                                                     @Query("split") boolean split,
                                                     @Query("type") String type,
                                                     @Query("lang") String lang);

    @GET("horoscope/planet-details")
    Call<HoroscopeResponseModel> horoscopePlanetDetails(@Query("api_key") String api_key,
                                                        @Query("dob") String dateOfBirth,
                                                        @Query("tob") String timeOfBirth,
                                                        @Query("lat") String latitude,
                                                        @Query("lon") String longitude,
                                                        @Query("tz") String timeZone,
                                                        @Query("lang") String lang);

    /*http://freeastrologyapi.com/api-reference*/
    @POST("planets")
    Call<PlanetsResponseModel> planetDetails(@Body PlanetsRequestModel planetsRequestModel);

    @GET("postoffice/{suffix}")
    Call<List<PinCodeModel>> locationData(@Path("suffix") String location);

    @GET("pincode/{suffix}")
    Call<List<PinCodeModel>> locationByPin(@Path("suffix") String location);

}
