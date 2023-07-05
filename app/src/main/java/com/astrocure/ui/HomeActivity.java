package com.astrocure.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.astrocure.R;
import com.astrocure.adapters.HomeZodiacAdapter;
import com.astrocure.adapters.ZodiacViewpagerAdapter;
import com.astrocure.databinding.ActivityHomeBinding;
import com.astrocure.ui.fragments.EntertainmentFragment;
import com.astrocure.ui.fragments.HomeFeedFragment;
import com.astrocure.ui.fragments.HoroscopeFragment;
import com.astrocure.ui.fragments.TodayFragment;
import com.astrocure.models.HomeZodiacModel;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityHomeBinding binding;
    HomeZodiacAdapter homeZodiacAdapter;
    List<HomeZodiacModel> modelList;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragment = new HoroscopeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
        fragmentTransaction.commit();

        binding.bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.getHeaderView(0).findViewById(R.id.close_drawer).setOnClickListener(v -> binding.drawer.closeDrawer(GravityCompat.END));

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.horoscope){
            fragment = new HoroscopeFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        } else if (item.getItemId() == R.id.feeds) {
            fragment = new HomeFeedFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        }else if (item.getItemId() == R.id.videos){
            Toast.makeText(this, "Videos", Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId()==R.id.entertainment){
            fragment = new EntertainmentFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        } else if (item.getItemId() == R.id.profile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}