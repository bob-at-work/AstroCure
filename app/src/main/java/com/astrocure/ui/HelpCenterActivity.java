package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.astrocure.R;
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

        adapter = new HelpHeadAdapter(getApplicationContext());
        binding.headingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.headingList.setAdapter(adapter);

        questionAdapter = new HelpQuestionAdapter(getApplicationContext());
        binding.answerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
        binding.answerList.setAdapter(questionAdapter);

    }
}