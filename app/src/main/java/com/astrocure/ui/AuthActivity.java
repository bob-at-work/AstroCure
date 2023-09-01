package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.send.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), OtpActivity.class));
        });

        binding.recoverAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RecoverAccountActivity.class)));

    }
}