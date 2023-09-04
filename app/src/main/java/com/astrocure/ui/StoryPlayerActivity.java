package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityStoryPlayerBinding;
import com.bumptech.glide.Glide;

public class StoryPlayerActivity extends AppCompatActivity {
    ActivityStoryPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        binding = ActivityStoryPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.close.setOnClickListener(v -> onBackPressed());

        String title = getIntent().getStringExtra("title");
        int subImage = getIntent().getIntExtra("subImage",0);

        binding.name.setText(title);
        Glide.with(getApplicationContext()).load(subImage)
                .into(binding.logo);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}