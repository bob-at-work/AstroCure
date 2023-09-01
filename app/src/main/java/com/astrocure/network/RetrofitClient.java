package com.astrocure.network;

import static com.astrocure.utils.AppConstants.POST_OFFICE;
import static com.astrocure.utils.AstrologyApiConstants.API_KEY;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //    private static final String BASE_URL = "https://api.vedicastroapi.com/v3-json/";
    private static final String BASE_URL = "https://json.freeastrologyapi.com/";

    public static ApiInterface getKundliClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .method(original.method(), original.body())
                            .addHeader("x-api-key", API_KEY)
                            .build();
                    Response response = chain.proceed(request);
                    return response;
                })
                .addNetworkInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getLocationClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(POST_OFFICE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(ApiInterface.class);
    }

}
