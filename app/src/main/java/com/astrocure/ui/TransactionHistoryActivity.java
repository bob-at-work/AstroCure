package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.astrocure.R;
import com.astrocure.adapters.TransactionHistoryAdapter;
import com.astrocure.databinding.ActivityTransactionHistoryBinding;

public class TransactionHistoryActivity extends AppCompatActivity {
    TransactionHistoryAdapter adapter;
    ActivityTransactionHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityTransactionHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adapter = new TransactionHistoryAdapter(getApplicationContext());
        binding.transactionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.transactionList.setAdapter(adapter);

    }
}