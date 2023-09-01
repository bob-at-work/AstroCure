package com.astrocure.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.astrocure.models.PinCodeModel;
import com.astrocure.repository.PincodeRepository;

public class CreateAccountViewModel extends AndroidViewModel {
    private final PincodeRepository pincodeRepository;
    private MutableLiveData<PinCodeModel> location = new MutableLiveData<>();

    public CreateAccountViewModel(@NonNull Application application) {
        super(application);
        this.pincodeRepository = new PincodeRepository();
    }

    public MutableLiveData<PinCodeModel> getLocation(String suffix) {
        location = pincodeRepository.getLocations(suffix);
        return location;
    }

}
