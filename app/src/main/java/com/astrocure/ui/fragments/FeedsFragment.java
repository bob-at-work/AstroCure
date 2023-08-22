package com.astrocure.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedsBinding.inflate(inflater, container, false);

        binding.newest.setOnClickListener(v -> {
            binding.newest.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_selected_bg));
            binding.newest.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.mostCommented.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostCommented.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
            binding.mostLiked.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostLiked.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
        });
        binding.mostCommented.setOnClickListener(v -> {
            binding.mostCommented.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_selected_bg));
            binding.mostCommented.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.newest.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.newest.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
            binding.mostLiked.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostLiked.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
        });
        binding.mostLiked.setOnClickListener(v -> {
            binding.mostLiked.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_selected_bg));
            binding.mostLiked.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.newest.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.newest.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
            binding.mostCommented.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostCommented.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
        });

        FeedsAdapter adapter = new FeedsAdapter(getContext());
        binding.feedList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.feedList.setAdapter(adapter);
        binding.addPost.setOnClickListener(v -> startActivity(new Intent(getContext(), AddPostActivity.class)));
        return binding.getRoot();
    }
}