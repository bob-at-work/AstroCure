package com.astrocure.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.AstrologerCallListAdapter;
import com.astrocure.databinding.ActivityAstrologerCallListBinding;

public class AstrologerCallListActivity extends AppCompatActivity {

    ActivityAstrologerCallListBinding binding;
    AstrologerCallListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerCallListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        adapter = new AstrologerCallListAdapter(getApplicationContext());
        binding.contentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.contentList.setAdapter(adapter);
    }
}