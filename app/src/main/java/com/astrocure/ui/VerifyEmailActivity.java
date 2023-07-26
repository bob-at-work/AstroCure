package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.astrocure.R;
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