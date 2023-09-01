package com.astrocure.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.astrocure.R;
import com.astrocure.adapters.BottomSheetHoroscopeAdapter;
import com.astrocure.adapters.HomeTabAdapter;
import com.astrocure.adapters.HomeZodiacAdapter;
import com.astrocure.databinding.ActivityMainBinding;
import com.astrocure.databinding.CheckHoroBottomSheetBinding;
import com.astrocure.databinding.HoroResultBottomSheetBinding;
import com.astrocure.models.HomeZodiacModel;
import com.astrocure.models.PlanetsRequestModel;
import com.astrocure.models.PlanetsResponseModel;
import com.astrocure.network.RetrofitClient;
import com.astrocure.utils.PlanetsHouse;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    ActivityMainBinding binding;
    HomeZodiacAdapter homeZodiacAdapter;
    List<HomeZodiacModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String currentDate = new SimpleDateFormat("EEEE,dd MMM").format(new Date());
        binding.time.setText(currentDate);

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
        homeZodiacAdapter = new HomeZodiacAdapter(getApplicationContext(), modelList);
        binding.zodiacList.setAdapter(homeZodiacAdapter);

        binding.toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        binding.sideNav.getHeaderView(0).findViewById(R.id.close_drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawer.closeDrawer(GravityCompat.END);
            }
        });
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Yesterday"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Today"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tomorrow"));
        HomeTabAdapter adapter = new HomeTabAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.viewPager.setCurrentItem(1);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        PlanetsRequestModel requestModel = new PlanetsRequestModel();
        requestModel.setYear(2009);
        requestModel.setMonth(12);
        requestModel.setDate(27);
        requestModel.setHours(17);
        requestModel.setMinutes(30);
        requestModel.setSeconds(0);
        requestModel.setLatitude(22.11f);
        requestModel.setLongitude(84.34f);
        requestModel.setTimezone(5.5f);
        requestModel.setSettings(new PlanetsRequestModel.Settings("topocentric", "lahiri"));

        RetrofitClient.getKundliClient().planetDetails(requestModel).enqueue(new Callback<PlanetsResponseModel>() {
            @Override
            public void onResponse(Call<PlanetsResponseModel> call, Response<PlanetsResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        PlanetsHouse healthPlanet = new PlanetsHouse(getApplicationContext(), response.body());
                    }
                } catch (Exception e) {
                    Log.e("TAG", "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<PlanetsResponseModel> call, Throwable t) {

            }
        });

        binding.checkHoro.setOnClickListener(v -> {
            CheckHoroBottomSheetBinding dialogBinding = CheckHoroBottomSheetBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            bottomSheetDialog.setContentView(dialogBinding.getRoot());
            dialogBinding.tob.setOnClickListener(v1 -> {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar, (timePicker, selectedHour, selectedMinute) -> dialogBinding.tob.setText(selectedHour + ":" + selectedMinute), hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            });
            dialogBinding.dob.setOnClickListener(v12 -> {
                int selectedYear = 2000;
                int selectedMonth = 5;
                int selectedDayOfMonth = 10;
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar, (view, year, month, dayOfMonth) -> dialogBinding.dob.setText(dayOfMonth + "/" + month + "/" + year), selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            });

            dialogBinding.checkHoro.setOnClickListener(v13 -> {
                HoroResultBottomSheetBinding binding1 = HoroResultBottomSheetBinding.inflate(getLayoutInflater());
                BottomSheetDialog bottomDialog = new BottomSheetDialog(MainActivity.this);
                bottomDialog.setContentView(binding1.getRoot());
                binding1.tabLayout.addTab(binding1.tabLayout.newTab().setText("Yesterday"));
                binding1.tabLayout.addTab(binding1.tabLayout.newTab().setText("Today"));
                binding1.tabLayout.addTab(binding1.tabLayout.newTab().setText("Tomorrow"));
                BottomSheetHoroscopeAdapter adapter1 = new BottomSheetHoroscopeAdapter(getSupportFragmentManager(), MainActivity.this, binding1.tabLayout.getTabCount());
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
            });
            bottomSheetDialog.show();
        });

//        binding.customNav.setMenuItems(menuItem,0);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId() == R.id.wallet) {
//            startActivity(new Intent(getApplicationContext(), FeedsActivity.class));
            Toast.makeText(this, "Wallet Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menu) {
            binding.drawer.openDrawer(GravityCompat.END);
            return true;
        }
        return false;
    }
}