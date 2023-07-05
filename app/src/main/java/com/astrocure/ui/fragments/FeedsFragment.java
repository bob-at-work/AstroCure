package com.astrocure.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrocure.adapters.FeedsAdapter;
import com.astrocure.databinding.FragmentFeedsBinding;
import com.astrocure.ui.AddPostActivity;

public class FeedsFragment extends Fragment {

    FragmentFeedsBinding binding;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeedsBinding.inflate(inflater,container,false);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("New Feed"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Most Commented"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Most Liked"));

        FeedsAdapter adapter = new FeedsAdapter(getContext());
        binding.feedList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.feedList.setAdapter(adapter);
        binding.addPost.setOnClickListener(v -> startActivity(new Intent(getContext(), AddPostActivity.class)));
        return binding.getRoot();
    }
}