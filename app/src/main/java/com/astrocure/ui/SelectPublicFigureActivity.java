package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.adapters.TrendingPublicFigureAdapter;
import com.astrocure.databinding.ActivitySelectPublicFigureBinding;

public class SelectPublicFigureActivity extends AppCompatActivity {
    ActivitySelectPublicFigureBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectPublicFigureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.close.setOnClickListener(v -> onBackPressed());

        TrendingPublicFigureAdapter adapter = new TrendingPublicFigureAdapter(getApplicationContext());
        binding.trendingList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4, RecyclerView.VERTICAL, false));
        binding.trendingList.setAdapter(adapter);
        binding.actorList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4, RecyclerView.VERTICAL, false));
        binding.actorList.setAdapter(adapter);
        binding.actressList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4, RecyclerView.VERTICAL, false));
        binding.actressList.setAdapter(adapter);
    }
}