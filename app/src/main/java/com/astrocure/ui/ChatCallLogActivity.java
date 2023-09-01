package com.astrocure.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.ChatCallLogAdapter;
import com.astrocure.databinding.ActivityChatCallLogBinding;

public class ChatCallLogActivity extends AppCompatActivity {
    ActivityChatCallLogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatCallLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        binding.callLogBtn.setOnClickListener(v -> {
            binding.callLogBtn.setBackgroundColor(Color.WHITE);
            binding.callText.setTextColor(Color.rgb(84, 20, 126));
            binding.callText.getCompoundDrawables()[0].setTint(Color.rgb(84, 20, 126));
            binding.chatLogBtn.setBackgroundColor(Color.rgb(84, 20, 126));
            binding.chatText.setTextColor(Color.WHITE);
            binding.chatText.getCompoundDrawables()[0].setTint(Color.WHITE);
        });
        binding.chatLogBtn.setOnClickListener(v -> {
            binding.chatLogBtn.setBackgroundColor(Color.WHITE);
            binding.chatText.setTextColor(Color.rgb(84, 20, 126));
            binding.chatText.getCompoundDrawables()[0].setTint(Color.rgb(84, 20, 126));
            binding.callLogBtn.setBackgroundColor(Color.rgb(84, 20, 126));
            binding.callText.setTextColor(Color.WHITE);
            binding.callText.getCompoundDrawables()[0].setTint(Color.WHITE);
        });

        ChatCallLogAdapter adapter = new ChatCallLogAdapter(getApplicationContext());
        binding.callChatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.callChatList.setAdapter(adapter);
    }
}