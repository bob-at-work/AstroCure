package com.astrocure.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.TransactionChatHistoryAdapter;
import com.astrocure.adapters.TransactionHistoryAdapter;
import com.astrocure.databinding.ActivityTransactionHistoryBinding;
import com.google.android.material.tabs.TabLayout;

public class TransactionHistoryActivity extends AppCompatActivity {
    TransactionHistoryAdapter adapter;
    ActivityTransactionHistoryBinding binding;
    TransactionChatHistoryAdapter chatHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        binding.filterTab.addTab(binding.filterTab.newTab().setText("All"));
        binding.filterTab.addTab(binding.filterTab.newTab().setText("Chat"));
        binding.filterTab.addTab(binding.filterTab.newTab().setText("Call"));

        adapter = new TransactionHistoryAdapter(getApplicationContext());
        binding.transactionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.transactionList.setAdapter(adapter);

        chatHistoryAdapter = new TransactionChatHistoryAdapter(getApplicationContext());

        binding.filterTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        binding.transactionList.setAdapter(adapter);
                        break;
                    case 1:
                        binding.transactionList.setAdapter(chatHistoryAdapter);
                        break;
                    case 2:
                        binding.transactionList.setAdapter(chatHistoryAdapter);
                        Toast.makeText(TransactionHistoryActivity.this, "Call", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}