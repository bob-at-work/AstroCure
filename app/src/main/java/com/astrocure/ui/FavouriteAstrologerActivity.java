package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.FavouriteAstrologerAdapter;
import com.astrocure.databinding.ActivityFavouriteAstrologerBinding;

public class FavouriteAstrologerActivity extends AppCompatActivity {
    ActivityFavouriteAstrologerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouriteAstrologerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        FavouriteAstrologerAdapter adapter = new FavouriteAstrologerAdapter(getApplicationContext());
        binding.contentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.contentList.setAdapter(adapter);

    }
}