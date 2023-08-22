package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.astrocure.R;
import com.astrocure.adapters.AstrologerChatListAdapter;
import com.astrocure.databinding.ActivityAstrologerChatListBinding;

public class AstrologerChatListActivity extends AppCompatActivity {

    ActivityAstrologerChatListBinding binding;
    AstrologerChatListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        adapter = new AstrologerChatListAdapter(getApplicationContext());
        binding.contentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.contentList.setAdapter(adapter);
    }
}