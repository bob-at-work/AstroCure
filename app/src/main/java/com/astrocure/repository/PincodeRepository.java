package com.astrocure.repository;

import androidx.lifecycle.MutableLiveData;

import com.astrocure.models.PinCodeModel;
import com.astrocure.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PincodeRepository {

    private static PincodeRepository pincodeRepository;

    public static PincodeRepository getInstance() {
        if (pincodeRepository == null) {
            pincodeRepository = new PincodeRepository();
        }
        return pincodeRepository;
    }

    public MutableLiveData<PinCodeModel> getLocations(String suffix) {
        final MutableLiveData<PinCodeModel> pinInfo = new MutableLiveData<>();
        RetrofitClient.getLocationClient().locationData(suffix).enqueue(new Callback<List<PinCodeModel>>() {
            @Override
            public void onResponse(Call<List<PinCodeModel>> call, Response<List<PinCodeModel>> response) {
                try {
                    if (response.isSuccessful()) {
                        pinInfo.setValue(response.body().get(0));
                    } else {

                    }
                } catch (Exception e) {

                }
//                pinInfo.setValue(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<PinCodeModel>> call, Throwable t) {
                pinInfo.setValue(null);
            }
        });

        return pinInfo;
    }


}
