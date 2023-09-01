package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityRecoverAccountBinding;
import com.google.android.material.tabs.TabLayout;

public class RecoverAccountActivity extends AppCompatActivity {
    ActivityRecoverAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecoverAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        binding.send.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), VerifyAccountActivity.class)));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    binding.countryCode.setVisibility(View.VISIBLE);
                    binding.phoneNum.setVisibility(View.VISIBLE);
                    binding.email.setVisibility(View.GONE);
                } else {
                    binding.countryCode.setVisibility(View.GONE);
                    binding.phoneNum.setVisibility(View.GONE);
                    binding.email.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}