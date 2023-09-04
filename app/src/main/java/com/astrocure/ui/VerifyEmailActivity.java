package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityVerifyEmailBinding;

public class VerifyEmailActivity extends AppCompatActivity {

    ActivityVerifyEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.verify.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),HomeActivity.class)));
    }
}