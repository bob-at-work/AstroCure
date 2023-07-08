package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.astrocure.R;
import com.astrocure.databinding.ActivityCompatibilityBinding;

public class CompatibilityActivity extends AppCompatActivity {
    ActivityCompatibilityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompatibilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}