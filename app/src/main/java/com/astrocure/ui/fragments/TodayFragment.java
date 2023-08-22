package com.astrocure.ui.fragments;

import static com.astrocure.utils.AstrologyApiConstants.CAREER_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.HEALTH_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.LAHIRI;
import static com.astrocure.utils.AstrologyApiConstants.LOVE_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.TOPOCENTRIC;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.astrocure.R;
import com.astrocure.databinding.FragmentTodayBinding;
import com.astrocure.models.PlanetsRequestModel;
import com.astrocure.models.PlanetsResponseModel;
import com.astrocure.network.RetrofitClient;
import com.astrocure.utils.PlanetsHouse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayFragment extends Fragment {

    FragmentTodayBinding binding;

    public TodayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTodayBinding.inflate(inflater, container, false);

        binding.zodiac1.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(new Date()));
        binding.zodiac.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(new Date()));
        binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
        binding.zodiac1.loveBtn.setOnClickListener(v -> {
            binding.zodiac1.description.setText(R.string.dummy_1);
            binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.zodiac1.loveBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.zodiac1.careerBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn_inactive));
            binding.zodiac1.careerBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.zodiac1.healthBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn_inactive));
            binding.zodiac1.healthBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        });
        binding.zodiac1.careerBtn.setOnClickListener(v -> {
            binding.zodiac1.description.setText(R.string.dummy_2);
            binding.zodiac1.careerBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.zodiac1.careerBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn_inactive));
            binding.zodiac1.loveBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.zodiac1.healthBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn_inactive));
            binding.zodiac1.healthBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        });
        binding.zodiac1.healthBtn.setOnClickListener(v -> {
            binding.zodiac1.description.setText(R.string.dummy_3);
            binding.zodiac1.healthBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.zodiac1.healthBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.zodiac1.careerBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn_inactive));
            binding.zodiac1.careerBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn_inactive));
            binding.zodiac1.loveBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        });

        setFirstComponent();
        return binding.getRoot();
    }

    private void setFirstComponent() {
        PlanetsRequestModel requestModel = new PlanetsRequestModel();
        requestModel.setYear(2000);
        requestModel.setMonth(6);
        requestModel.setDate(24);
        requestModel.setHours(17);
        requestModel.setMinutes(30);
        requestModel.setSeconds(0);
        requestModel.setLatitude(22.11f);
        requestModel.setLongitude(84.34f);
        requestModel.setTimezone(5.5f);
        requestModel.setSettings(new PlanetsRequestModel.Settings(TOPOCENTRIC, LAHIRI));
        RetrofitClient.getKundliClient().planetDetails(requestModel).enqueue(new Callback<PlanetsResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<PlanetsResponseModel> call, @NonNull Response<PlanetsResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        PlanetsHouse healthPlanet = new PlanetsHouse(requireContext(), response.body());
                        String planets = healthPlanet.getLovePlanetNum();
                        String zodiac = healthPlanet.getZodiacSign();
                        binding.zodiacName.setText(zodiac);
                        binding.zodiac.zodiac.setText(zodiac);
                        binding.horo.setLove("Love\n" + healthPlanet.getPercent(LOVE_HOUSE) + "%");
                        binding.horo.setWellBeing("Well Being\n" + healthPlanet.getPercent(HEALTH_HOUSE) + "%");
                        binding.horo.setCareer("Career\n" + healthPlanet.getPercent(CAREER_HOUSE) + "%");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlanetsResponseModel> call, @NonNull Throwable t) {

            }
        });
    }
}