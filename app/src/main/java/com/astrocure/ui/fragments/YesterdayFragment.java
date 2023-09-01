package com.astrocure.ui.fragments;

import static com.astrocure.utils.AppConstantMethods.loadJSONFromAsset;
import static com.astrocure.utils.AstrologyApiConstants.CAREER_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.HEALTH_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.LAHIRI;
import static com.astrocure.utils.AstrologyApiConstants.LOVE_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.TOPOCENTRIC;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.astrocure.R;
import com.astrocure.databinding.FragmentYesterdayBinding;
import com.astrocure.models.PlanetsRequestModel;
import com.astrocure.models.PlanetsResponseModel;
import com.astrocure.network.RetrofitClient;
import com.astrocure.utils.PlanetsHouse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YesterdayFragment extends Fragment {

    FragmentYesterdayBinding binding;
    String health;
    String love;
    String career;

    public YesterdayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYesterdayBinding.inflate(inflater, container, false);

        binding.zodiac1.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)));
        binding.zodiac.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)));
        binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        binding.zodiac1.loveBtn.setOnClickListener(v -> {
            binding.zodiac1.description.setText(R.string.dummy_1);
            binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiac1.loveBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.zodiac1.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiac1.careerBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiac1.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiac1.healthBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        binding.zodiac1.careerBtn.setOnClickListener(v -> {
            binding.zodiac1.description.setText(R.string.dummy_2);
            binding.zodiac1.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiac1.careerBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiac1.loveBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiac1.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiac1.healthBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        binding.zodiac1.healthBtn.setOnClickListener(v -> {
            binding.zodiac1.description.setText(R.string.dummy_3);
            binding.zodiac1.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiac1.healthBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.zodiac1.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiac1.careerBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiac1.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiac1.loveBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });

        setFirstComponent();
        return binding.getRoot();
    }

    private void setFirstComponent() {
        Date date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        Log.i("TAG", "setFirstComponent: " + date.getMonth());
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
            public void onResponse(Call<PlanetsResponseModel> call, Response<PlanetsResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        PlanetsHouse healthPlanet = new PlanetsHouse(getContext(), response.body());
                        String planets = healthPlanet.getLovePlanetNum();
                        String zodiac = healthPlanet.getZodiacSign();
                        binding.zodiacName.setText(zodiac);
                        binding.zodiac.zodiac.setText(zodiac);
                        binding.horo.setLove("Love\n" + healthPlanet.getPercent(LOVE_HOUSE) + "%");
                        binding.horo.setWellBeing("Well Being\n" + healthPlanet.getPercent(HEALTH_HOUSE) + "%");
                        binding.horo.setCareer("Career\n" + healthPlanet.getPercent(CAREER_HOUSE) + "%");
                        if (!planets.isEmpty()) {
                            healthPlanet.getHouseCurrentSign(LOVE_HOUSE);
                            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAsset(requireActivity(), "Predictions.json")));
                            JSONArray jsonArray = jsonObject.getJSONArray("double");
                            love = jsonArray.getJSONObject(0).getJSONObject(planets).get("Prediction").toString();
                        } else {
                            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAsset(requireActivity(), "Predictions.json")));
                            JSONArray jsonArray = jsonObject.getJSONArray("zodiac-ruling-planet");
                            love = jsonArray.getJSONObject(0).getJSONObject(planets).get("Prediction").toString();
                        }
                    }
                } catch (Exception e) {
                    Log.e("TAG", "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<PlanetsResponseModel> call, Throwable t) {

            }
        });
    }
}