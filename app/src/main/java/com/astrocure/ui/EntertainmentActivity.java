package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityEntertainmentBinding;
import com.astrocure.ui.games.AstroGenieActivity;
import com.astrocure.ui.games.BookOfLoveActivity;
import com.astrocure.ui.games.ColorTherapyActivity;
import com.astrocure.ui.games.CrystalBallActivity;
import com.astrocure.ui.games.FortuneCookieActivity;

public class EntertainmentActivity extends AppCompatActivity {

    ActivityEntertainmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntertainmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
        binding.fortuneCookie.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), FortuneCookieActivity.class)));
        binding.crystalBall.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CrystalBallActivity.class)));
        binding.bookOfLove.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BookOfLoveActivity.class)));
        binding.astroGenie.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AstroGenieActivity.class)));
        binding.colorTherapy.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ColorTherapyActivity.class)));

    }
}