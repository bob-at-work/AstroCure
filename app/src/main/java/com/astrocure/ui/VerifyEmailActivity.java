package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityVerifyEmailBinding;

public class VerifyEmailActivity extends AppCompatActivity {

    ActivityVerifyEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}