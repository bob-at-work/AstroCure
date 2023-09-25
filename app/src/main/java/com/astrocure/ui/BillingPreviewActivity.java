package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.BillingPlanFeatureAdapter;
import com.astrocure.databinding.ActivityBillingPreviewBinding;
import com.astrocure.models.PlanModel;

public class BillingPreviewActivity extends AppCompatActivity {
    ActivityBillingPreviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillingPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PlanModel planModel = (PlanModel) getIntent().getSerializableExtra("plan_model");

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        binding.changePlan.setOnClickListener(v -> onBackPressed());

        if (planModel != null) {
            BillingPlanFeatureAdapter planFeatureAdapter = new BillingPlanFeatureAdapter(getApplicationContext(), planModel.getFeaturesName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            binding.featureList.setLayoutManager(linearLayoutManager);
            binding.featureList.setAdapter(planFeatureAdapter);
        }

        binding.oneMonth.setChecked(true);
        binding.oneMonth.setOnClickListener(v -> {
            if (!binding.oneMonth.isChecked()) {
                binding.oneMonth.setChecked(true);
                binding.threeMonth.setChecked(false);
                binding.oneMonth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                binding.threeMonth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                binding.oneMonth.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_billing_selector_active, 0, 0, 0);
                binding.threeMonth.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_billing_selector_inactive, 0, 0, 0);
                binding.oneMonth.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                binding.threeMonth.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                binding.threePrice.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
        });

        binding.threeMonth.setOnClickListener(v -> {
            if (!binding.threeMonth.isChecked()) {
                binding.threeMonth.setChecked(true);
                binding.oneMonth.setChecked(false);
                binding.threeMonth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                binding.oneMonth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                binding.threeMonth.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_billing_selector_active, 0, 0, 0);
                binding.oneMonth.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_billing_selector_inactive, 0, 0, 0);
                binding.threeMonth.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                binding.threePrice.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                binding.oneMonth.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
        });

        binding.proceed.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), PaymentActivity.class)));

    }
}