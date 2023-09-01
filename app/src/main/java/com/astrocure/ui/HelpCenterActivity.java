package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.adapters.HelpHeadAdapter;
import com.astrocure.adapters.HelpQuestionAdapter;
import com.astrocure.databinding.ActivityHelpCenterBinding;

public class HelpCenterActivity extends AppCompatActivity {

    ActivityHelpCenterBinding binding;
    HelpHeadAdapter adapter;
    HelpQuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        adapter = new HelpHeadAdapter(getApplicationContext());
        binding.headingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.headingList.setAdapter(adapter);

        questionAdapter = new HelpQuestionAdapter(getApplicationContext());
        binding.answerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        binding.answerList.setAdapter(questionAdapter);

        binding.helpChat.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HelpChatActivity.class));
        });
    }
}