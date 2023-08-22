package com.astrocure.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.astrocure.R;
import com.astrocure.databinding.FragmentHomeFeedBinding;


public class HomeFeedFragment extends Fragment {
    FragmentHomeFeedBinding binding;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;

    public HomeFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeFeedBinding.inflate(inflater, container, false);
        fragment = new FeedsFragment();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.container.getId(), fragment);
        fragmentTransaction.commit();
        binding.feeds.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        binding.feeds.setOnClickListener(v -> {
            fragment = new FeedsFragment();
//            binding.getRoot().setBackground(getContext().getDrawable(R.drawable.background_2));
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), fragment);
            fragmentTransaction.commit();
            binding.feeds.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.feeds.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.questionAnswer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.questionAnswer.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        binding.questionAnswer.setOnClickListener(v -> {
            fragment = new QuestionAnswerFragment();
//            binding.getRoot().setBackground(getContext().getDrawable(R.drawable.entertainment_bg));
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), fragment);
            fragmentTransaction.commit();
            binding.questionAnswer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.questionAnswer.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.feeds.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.feeds.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        return binding.getRoot();
    }
}