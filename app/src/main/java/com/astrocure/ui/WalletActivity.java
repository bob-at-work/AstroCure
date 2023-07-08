package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.adapters.WalletPriceAdapter;
import com.astrocure.databinding.ActivityWalletBinding;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;
    WalletPriceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
        adapter = new WalletPriceAdapter(getApplicationContext());
        binding.priceList.setLayoutManager(new GridLayoutManager(getApplicationContext(),4, RecyclerView.VERTICAL,false));
        binding.priceList.setAdapter(adapter);

        binding.proceed.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), PaymentActivity.class)));
    }
}