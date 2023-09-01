package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.FavouritesAdapter;
import com.astrocure.databinding.ActivityFavouritesBinding;

public class FavouritesActivity extends AppCompatActivity {

    ActivityFavouritesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        FavouritesAdapter adapter = new FavouritesAdapter(getApplicationContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.videoList.setAdapter(adapter);
    }
}