package com.astrocure.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.FavouritesAdapter;
import com.astrocure.adapters.FilterCheckAdapter;
import com.astrocure.adapters.FilterExpertiseAdapter;
import com.astrocure.databinding.ActivityFavouritesBinding;
import com.astrocure.databinding.DialogRemoveFavouriteVideoBinding;
import com.astrocure.databinding.DialogVideoFilterBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    ActivityFavouritesBinding binding;
    List<String> commoditiesList;
    List<String> sportsList;
    List<String> stockList;
    List<String> moviesList;
    List<String> miscList;
    FilterExpertiseAdapter commoditiesAdapter, sportsAdapter, stockAdapter, moviesAdapter;
    FilterCheckAdapter miscAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        addDataToList();
        FavouritesAdapter adapter = new FavouritesAdapter(getApplicationContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.videoList.setAdapter(adapter);
        adapter.setOnItemClickListener(new FavouritesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DialogRemoveFavouriteVideoBinding videoBinding = DialogRemoveFavouriteVideoBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(FavouritesActivity.this, R.style.Theme_AstroCure);
                dialog.setContentView(videoBinding.getRoot());
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        binding.filter.setOnClickListener(v -> {
            DialogVideoFilterBinding cardBinding = DialogVideoFilterBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(FavouritesActivity.this, R.style.BottomSheetDialog);
            commoditiesAdapter = new FilterExpertiseAdapter(getApplicationContext(), commoditiesList);
            sportsAdapter = new FilterExpertiseAdapter(getApplicationContext(), sportsList);
            stockAdapter = new FilterExpertiseAdapter(getApplicationContext(), stockList);
            moviesAdapter = new FilterExpertiseAdapter(getApplicationContext(), moviesList);
            miscAdapter = new FilterCheckAdapter(getApplicationContext(), miscList);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.setContentView(cardBinding.getRoot());
            cardBinding.commoditiesList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.commoditiesList.setAdapter(commoditiesAdapter);
            cardBinding.sportsList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.sportsList.setAdapter(sportsAdapter);
            cardBinding.stockList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.stockList.setAdapter(stockAdapter);
            cardBinding.moviesList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.moviesList.setAdapter(moviesAdapter);
            cardBinding.miscellaneousList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.miscellaneousList.setAdapter(miscAdapter);
            bottomSheetDialog.show();
        });
    }

    private void addDataToList() {
        commoditiesList = new ArrayList<>();
        sportsList = new ArrayList<>();
        stockList = new ArrayList<>();
        miscList = new ArrayList<>();
        moviesList = new ArrayList<>();

        commoditiesList.add("Gold");
        commoditiesList.add("Silver");
        commoditiesList.add("Crude Oil");
        sportsList.add("Cricket");
        sportsList.add("Football");
        sportsList.add("Tennis");
        sportsList.add("Basketball");
        stockList.add("Global Market");
        moviesList.add("Hollywood");
        moviesList.add("Bollywood");
        moviesList.add("Tollywood");
        moviesList.add("Kollywood");
        miscList.add("Politics");
        miscList.add("Natural Disaster");
        miscList.add("Global Economy");
    }
}