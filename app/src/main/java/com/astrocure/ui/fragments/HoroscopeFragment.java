package com.astrocure.ui.fragments;

import static com.astrocure.utils.AppConstantMethods.loadJSONFromAsset;
import static com.astrocure.utils.AppConstants.OPEN_DRAWER;
import static com.astrocure.utils.AstrologyApiConstants.LAHIRI;
import static com.astrocure.utils.AstrologyApiConstants.LOVE_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.TOPOCENTRIC;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.astrocure.R;
import com.astrocure.adapters.HomeZodiacAdapter;
import com.astrocure.adapters.ZodiacViewpagerAdapter;
import com.astrocure.callback.SideNavigationCallback;
import com.astrocure.databinding.CheckHoroBottomSheetBinding;
import com.astrocure.databinding.DialogDateBinding;
import com.astrocure.databinding.DialogHomeZodiacPreviewBinding;
import com.astrocure.databinding.DialogTimeBinding;
import com.astrocure.databinding.FragmentHoroscopeBinding;
import com.astrocure.models.HomeZodiacModel;
import com.astrocure.models.PlanetsRequestModel;
import com.astrocure.models.PlanetsResponseModel;
import com.astrocure.models.ZodiacViewPagerModel;
import com.astrocure.network.RetrofitClient;
import com.astrocure.ui.WalletActivity;
import com.astrocure.utils.PlanetsHouse;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoroscopeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    FragmentHoroscopeBinding binding;
    HomeZodiacAdapter homeZodiacAdapter;
    List<HomeZodiacModel> modelList;
    SideNavigationCallback callback;
    ZodiacViewPagerModel zodiacViewPagerModel;
    String health;
    String love;
    String career;

    public HoroscopeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (SideNavigationCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "implementation failed");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHoroscopeBinding.inflate(inflater, container, false);

        binding.toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        binding.toolbar.setNavigationOnClickListener(view -> callback.callBackAction(OPEN_DRAWER));
        setPercentData(17, 30, 0, 24, 6, 2000, 22.11f, 85.34f);
        modelList = new ArrayList<>();
        modelList.add(new HomeZodiacModel("Aries", R.drawable.aries_top));
        modelList.add(new HomeZodiacModel("Taurus", R.drawable.taurus_top));
        modelList.add(new HomeZodiacModel("Gemini", R.drawable.gemini_top));
        modelList.add(new HomeZodiacModel("Cancer", R.drawable.cancer_top));
        modelList.add(new HomeZodiacModel("Leo", R.drawable.leo_top));
        modelList.add(new HomeZodiacModel("Capricorn", R.drawable.capricorn_top));
        modelList.add(new HomeZodiacModel("Virgo", R.drawable.virgo_top));
        modelList.add(new HomeZodiacModel("Libra", R.drawable.libra_top));
        modelList.add(new HomeZodiacModel("Scorpius", R.drawable.scorpius_top));
        modelList.add(new HomeZodiacModel("Sagittarius", R.drawable.sagittarius_top));
        modelList.add(new HomeZodiacModel("Aquarius", R.drawable.aquarius_top));
        modelList.add(new HomeZodiacModel("Pisces", R.drawable.piseces_top));
        homeZodiacAdapter = new HomeZodiacAdapter(getContext(), modelList);
        binding.zodiacList.setAdapter(homeZodiacAdapter);
        homeZodiacAdapter.setOnItemClick((position, name, icon) -> {
            Dialog dialog = new Dialog(getActivity(), R.style.Theme_AstroCure);
            DialogHomeZodiacPreviewBinding zodiacPreviewBinding = DialogHomeZodiacPreviewBinding.inflate(inflater, container, false);
            dialog.setContentView(zodiacPreviewBinding.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
            zodiacPreviewBinding.zodiacName.setText(name);
            Glide.with(requireContext()).load(icon).into(zodiacPreviewBinding.zodiacLogo);
            zodiacPreviewBinding.imageContainer.setBackgroundResource(R.drawable.gradient_stroke_bg);
            zodiacPreviewBinding.getRoot().setOnClickListener(v -> dialog.cancel());
            zodiacPreviewBinding.mainContainer.setOnClickListener(v -> {
            });
            dialog.show();
        });

        binding.time.setText(new SimpleDateFormat("EEEE,dd MMM").format(new Date()));
        binding.zodiacLayout2.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(new Date()));
        binding.zodiacLayout1.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(new Date()));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Yesterday"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Today"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tomorrow"));
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Calendar calendar = Calendar.getInstance();
                switch (tab.getPosition()) {
                    case 0:
                        calendar.add(Calendar.DATE, -1);
                        binding.zodiacLayout2.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(calendar.getTime()));
                        binding.zodiacLayout1.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(calendar.getTime()));
                        break;
                    case 1:
//                        calendar.add(Calendar.DATE,-1);
                        binding.zodiacLayout2.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(new Date()));
                        binding.zodiacLayout1.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(new Date()));
                        break;
                    case 2:
                        calendar.add(Calendar.DATE, 1);
                        binding.zodiacLayout2.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(calendar.getTime()));
                        binding.zodiacLayout1.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(calendar.getTime()));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        zodiacViewPagerModel = new ZodiacViewPagerModel("Cancer");
        ZodiacViewpagerAdapter viewpagerAdapter = new ZodiacViewpagerAdapter(getContext(), zodiacViewPagerModel);
        binding.chartPercent.setAdapter(viewpagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.chartPercent);
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1));

        binding.zodiacLayout2.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        binding.zodiacLayout2.loveBtn.setOnClickListener(v -> {
            setZodiacLayout2("love", R.string.dummy_1);
        });
        binding.zodiacLayout2.careerBtn.setOnClickListener(v -> {
            setZodiacLayout2("career", R.string.dummy_2);
        });
        binding.zodiacLayout2.healthBtn.setOnClickListener(v -> {
            setZodiacLayout2("health", R.string.dummy_3);
        });
        binding.zodiacLayout1.share.setOnClickListener(v -> {
            String sendContent = binding.zodiacLayout1.zodiac.getText() + "\n\n" + binding.zodiacLayout1.content.getText().toString() + "\n\n" + "Hey check out this app at: https://play.google.com/store/apps/details?id=" + getActivity().getApplicationContext().getPackageName();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, sendContent);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });

        binding.seeNew.setOnClickListener(v -> {
            CheckHoroBottomSheetBinding dialogBinding = CheckHoroBottomSheetBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialog);
            Objects.requireNonNull(bottomSheetDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheetDialog.setContentView(dialogBinding.getRoot());
            dialogBinding.tob.setOnClickListener(v1 -> {
                DialogTimeBinding timeBinding = DialogTimeBinding.inflate(getLayoutInflater());
                Dialog timeDialog = new Dialog(requireActivity(), R.style.Theme_AstroCure);
                timeDialog.setContentView(timeBinding.getRoot());
                Objects.requireNonNull(timeDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
                timeBinding.timePicker.setOnClickListener(v2 -> timeDialog.dismiss());
                timeBinding.ok.setOnClickListener(v22 -> {
                    dialogBinding.tob.setText(timeBinding.timePicker.getHour() + ":" + timeBinding.timePicker.getMinute());
                    timeDialog.dismiss();
                });
                timeDialog.show();
            });
            dialogBinding.dob.setOnClickListener(v12 -> {
                DialogDateBinding dateBinding = DialogDateBinding.inflate(getLayoutInflater());
                Dialog dateDialog = new Dialog(requireActivity(), R.style.Theme_AstroCure);
                dateDialog.setContentView(dateBinding.getRoot());
                Objects.requireNonNull(dateDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
                dateBinding.datePicker.setOnClickListener(v2 -> dateDialog.dismiss());
                dateBinding.ok.setOnClickListener(v22 -> {
                    dialogBinding.dob.setText(dateBinding.datePicker.getDayOfMonth() + "/" + dateBinding.datePicker.getMonth() + "/" + dateBinding.datePicker.getYear());
                    dateDialog.dismiss();
                });
                dateDialog.show();
            });

            /*dialogBinding.checkHoro.setOnClickListener(v13 -> {
                HoroResultBottomSheetBinding binding1 = HoroResultBottomSheetBinding.inflate(inflater);
                BottomSheetDialog bottomDialog = new BottomSheetDialog(getContext());
                bottomDialog.setContentView(binding1.getRoot());
                binding1.tabLayout.addTab(binding1.tabLayout.newTab().setText("Yesterday"));
                binding1.tabLayout.addTab(binding1.tabLayout.newTab().setText("Today"));
                binding1.tabLayout.addTab(binding1.tabLayout.newTab().setText("Tomorrow"));
                BottomSheetHoroscopeAdapter adapter1 = new BottomSheetHoroscopeAdapter(getFragmentManager(),getContext(),binding1.tabLayout.getTabCount());
                binding1.viewPager.setAdapter(adapter1);
                binding1.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding1.tabLayout));
                binding1.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        binding1.viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
                bottomDialog.show();
            });*/
            bottomSheetDialog.show();
        });

        return binding.getRoot();
    }

    private void setZodiacLayout2(String name, int prediction) {
        switch (name) {
            case "love":
                setButtonLayout(R.color.white, R.color.black, R.color.btn_transparent, R.color.white, R.color.btn_transparent, R.color.white);
                break;
            case "career":
                setButtonLayout(R.color.btn_transparent, R.color.white, R.color.white, R.color.black, R.color.btn_transparent, R.color.white);
                break;
            case "health":
                setButtonLayout(R.color.btn_transparent, R.color.white, R.color.btn_transparent, R.color.white, R.color.white, R.color.black);
                break;
        }
        binding.zodiacLayout2.description.setText(prediction);

    }

    private void setButtonLayout(int loveBg, int loveTxt, int careerBg, int careerTxt, int healthBg, int healthTxt) {
        binding.zodiacLayout2.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), healthBg));
        binding.zodiacLayout2.healthBtn.setTextColor(ContextCompat.getColor(getContext(), healthTxt));
        binding.zodiacLayout2.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), careerBg));
        binding.zodiacLayout2.careerBtn.setTextColor(ContextCompat.getColor(getContext(), careerTxt));
        binding.zodiacLayout2.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), loveBg));
        binding.zodiacLayout2.loveBtn.setTextColor(ContextCompat.getColor(getContext(), loveTxt));
    }

    private void setPercentData(int hour, int min, int sec, int date, int month, int year, float latitude, float longitude) {
        PlanetsRequestModel requestModel = new PlanetsRequestModel();
        requestModel.setYear(year);
        requestModel.setMonth(month);
        requestModel.setDate(date);
        requestModel.setHours(hour);
        requestModel.setMinutes(min);
        requestModel.setSeconds(sec);
        requestModel.setLatitude(latitude);
        requestModel.setLongitude(longitude);
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
//                        binding.zodiacName.setText(zodiac);
                        binding.zodiacLayout1.zodiac.setText(zodiac);
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
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PlanetsResponseModel> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.wallet) {
            startActivity(new Intent(getContext(), WalletActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu) {
            callback.callBackAction(OPEN_DRAWER);
//            binding.drawer.openDrawer(GravityCompat.END);
            return true;
        } else if (item.getItemId() == R.id.horoscope) {
            Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}