package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.astrocure.R;
import com.astrocure.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}