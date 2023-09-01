package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends AppCompatActivity {

    ActivityFeedbackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }
}