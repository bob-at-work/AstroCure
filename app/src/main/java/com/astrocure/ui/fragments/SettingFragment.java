package com.astrocure.ui.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.astrocure.databinding.DialogCeoFeedbackBinding;
import com.astrocure.databinding.DialogLogoutBinding;
import com.astrocure.databinding.FragmentSettingBinding;

import java.util.Objects;

public class SettingFragment extends Fragment {
    FragmentSettingBinding binding;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);

        binding.logout.setOnClickListener(v -> {
            DialogLogoutBinding dialogBinding = DialogLogoutBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(requireContext());
            dialog.setContentView(dialogBinding.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialogBinding.yes.setOnClickListener(v1 -> dialog.dismiss());
            dialogBinding.no.setOnClickListener(v12 -> dialog.cancel());
            dialog.show();
        });

        binding.ceoFeedback.setOnClickListener(v -> {
            DialogCeoFeedbackBinding dialogBinding = DialogCeoFeedbackBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(requireContext());
            dialog.setContentView(dialogBinding.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.show();
        });

        return binding.getRoot();
    }
}