package com.astrocure.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.astrocure.databinding.FragmentProfileBinding;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        Glide.with(requireContext()).load("https://images.pexels.com/photos/227294/pexels-photo-227294.jpeg?auto=compress&cs=tinysrgb&w=600").into(binding.profileImg);

        binding.editProfile.setOnClickListener(v -> {
            binding.fullName.setEnabled(true);
            binding.fullName.setTextColor(Color.WHITE);
            binding.dateOfBirth.setEnabled(true);
            binding.dateOfBirth.setTextColor(Color.WHITE);
            binding.timeOfBirth.setEnabled(true);
            binding.timeOfBirth.setTextColor(Color.WHITE);
            binding.location.setEnabled(true);
            binding.location.setTextColor(Color.WHITE);
            binding.emailAddress.setEnabled(true);
            binding.emailAddress.setTextColor(Color.WHITE);
            binding.language.setEnabled(true);
            binding.language.setTextColor(Color.WHITE);
            binding.fullName.requestFocus();
            binding.fullName.setTextColor(Color.WHITE);
            binding.saveProfile.setVisibility(View.VISIBLE);
        });

        binding.saveProfile.setOnClickListener(v -> {
            binding.fullName.setEnabled(false);
            binding.fullName.setTextColor(Color.GRAY);
            binding.dateOfBirth.setEnabled(false);
            binding.dateOfBirth.setTextColor(Color.GRAY);
            binding.timeOfBirth.setEnabled(false);
            binding.timeOfBirth.setTextColor(Color.GRAY);
            binding.location.setEnabled(false);
            binding.location.setTextColor(Color.GRAY);
            binding.emailAddress.setEnabled(false);
            binding.emailAddress.setTextColor(Color.GRAY);
            binding.language.setEnabled(false);
            binding.language.setTextColor(Color.GRAY);
            binding.fullName.requestFocus();
            binding.fullName.setTextColor(Color.GRAY);
            binding.saveProfile.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
        });


        return binding.getRoot();
    }
}