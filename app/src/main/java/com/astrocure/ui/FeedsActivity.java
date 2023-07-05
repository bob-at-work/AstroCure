package com.astrocure.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.astrocure.R;
import com.astrocure.databinding.ActivityFeedsBinding;
import com.astrocure.ui.fragments.FeedsFragment;
import com.astrocure.ui.fragments.QuestionAnswerFragment;

public class FeedsActivity extends AppCompatActivity {
    ActivityFeedsBinding binding;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragment = new FeedsFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.container.getId(), fragment);
        fragmentTransaction.commit();
        binding.feeds.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        binding.feeds.setOnClickListener(v -> {
            fragment = new FeedsFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), fragment);
            fragmentTransaction.commit();
            binding.feeds.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            binding.feeds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            binding.questionAnswer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btn_inactive));
            binding.questionAnswer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
//            binding.addPost.setVisibility(View.VISIBLE);
        });
        binding.questionAnswer.setOnClickListener(v -> {
            fragment = new QuestionAnswerFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), fragment);
            fragmentTransaction.commit();
            binding.questionAnswer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            binding.questionAnswer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            binding.feeds.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btn_inactive));
            binding.feeds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
//            binding.addPost.setVisibility(View.GONE);
        });

    }
}