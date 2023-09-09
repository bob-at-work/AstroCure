package com.astrocure.ui.fragments;

import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.astrocure.R;
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
            Dialog dialog = new Dialog(requireContext(), R.style.Theme_AstroCure);
            dialog.setContentView(dialogBinding.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
            dialog.setCancelable(true);
            dialogBinding.yes.setOnClickListener(v1 -> dialog.dismiss());
            dialogBinding.no.setOnClickListener(v12 -> dialog.cancel());
            dialog.show();
        });

        binding.ceoFeedback.setOnClickListener(v -> {
            DialogCeoFeedbackBinding dialogBinding = DialogCeoFeedbackBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(requireContext(), R.style.Theme_AstroCure);
            dialog.setContentView(dialogBinding.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
            dialog.setCancelable(true);
            dialogBinding.gotoChat.setOnClickListener(v13 -> dialog.dismiss());
            dialog.show();
        });

        try {
            PackageInfo pInfo = requireContext().getPackageManager().getPackageInfo(requireContext().getPackageName(), 0);
            String version = pInfo.versionName;
            binding.appVersion.setText("App version "+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



        return binding.getRoot();
    }
}