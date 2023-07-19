package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.astrocure.R;
import com.astrocure.databinding.ActivityOtpBinding;

public class OtpActivity extends AppCompatActivity {

    ActivityOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.otp.setTextColor(getColor(R.color.white));

        binding.send.setOnClickListener(v -> {
           startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class));
        });

    }
}