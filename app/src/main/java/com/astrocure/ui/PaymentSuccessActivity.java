package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityPaymentSuccessBinding;

public class PaymentSuccessActivity extends AppCompatActivity {
    ActivityPaymentSuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gotoHome.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)));
    }
}