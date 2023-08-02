package com.astrocure.ui.fragments;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astrocure.R;
import com.astrocure.databinding.FragmentProfileBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        Glide.with(getContext()).load("https://images.pexels.com/photos/227294/pexels-photo-227294.jpeg?auto=compress&cs=tinysrgb&w=600")
                .into(binding.profileImg);

        binding.editProfile.setOnClickListener(v -> {
            binding.fullName.setEnabled(true);
            binding.dateOfBirth.setEnabled(true);
            binding.timeOfBirth.setEnabled(true);
            binding.location.setEnabled(true);
            binding.emailAddress.setEnabled(true);
            binding.language.setEnabled(true);
            binding.fullName.requestFocus();
        });
        
        binding.saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}