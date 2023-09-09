package com.astrocure.ui.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.astrocure.R;
import com.astrocure.databinding.DialogDateBinding;
import com.astrocure.databinding.DialogTimeBinding;
import com.astrocure.databinding.FragmentProfileBinding;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    ActivityResultLauncher<Intent> launcher;

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
            binding.fullName.setTextColor(Color.BLACK);
            binding.dateOfBirth.setEnabled(true);
            binding.dateOfBirth.setTextColor(Color.BLACK);
            binding.timeOfBirth.setEnabled(true);
            binding.timeOfBirth.setTextColor(Color.BLACK);
            binding.location.setEnabled(true);
            binding.location.setTextColor(Color.BLACK);
            binding.emailAddress.setEnabled(true);
            binding.emailAddress.setTextColor(Color.BLACK);
            binding.language.setEnabled(true);
            binding.language.setTextColor(Color.BLACK);
            binding.fullName.requestFocus();
            binding.fullName.setTextColor(Color.BLACK);
            binding.saveProfile.setVisibility(View.VISIBLE);
        });

        binding.saveProfile.setOnClickListener(v -> {
            binding.fullName.setEnabled(false);
            binding.fullName.setTextColor(Color.rgb(10, 10, 10));
            binding.dateOfBirth.setEnabled(false);
            binding.dateOfBirth.setTextColor(Color.rgb(10, 10, 10));
            binding.timeOfBirth.setEnabled(false);
            binding.timeOfBirth.setTextColor(Color.rgb(10, 10, 10));
            binding.location.setEnabled(false);
            binding.location.setTextColor(Color.rgb(10, 10, 10));
            binding.emailAddress.setEnabled(false);
            binding.emailAddress.setTextColor(Color.rgb(10, 10, 10));
            binding.language.setEnabled(false);
            binding.language.setTextColor(Color.rgb(10, 10, 10));
            binding.fullName.requestFocus();
            binding.fullName.setTextColor(Color.rgb(10, 10, 10));
            binding.saveProfile.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
        });

        binding.camera.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    try {
                        if (result.getData() != null) {
                            Glide.with(requireContext()).load(result.getData().getData()).into(binding.profileImg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        binding.dateOfBirth.setOnClickListener(v -> {
            DialogDateBinding dateBinding = DialogDateBinding.inflate(getLayoutInflater());
            Dialog dateDialog = new Dialog(requireActivity(), R.style.Theme_AstroCure);
            dateDialog.setContentView(dateBinding.getRoot());
            Objects.requireNonNull(dateDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
            dateBinding.datePicker.setOnClickListener(v2 -> dateDialog.dismiss());
            dateBinding.ok.setOnClickListener(v22 -> {
                binding.dateOfBirth.setText(dateBinding.datePicker.getDayOfMonth() + "/" + dateBinding.datePicker.getMonth() + "/" + dateBinding.datePicker.getYear());
                dateDialog.dismiss();
            });
            dateDialog.show();
        });
        binding.timeOfBirth.setOnClickListener(v -> {
            DialogTimeBinding timeBinding = DialogTimeBinding.inflate(getLayoutInflater());
            Dialog timeDialog = new Dialog(requireActivity());
            timeDialog.setContentView(timeBinding.getRoot());
            Objects.requireNonNull(timeDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
            timeBinding.timePicker.setOnClickListener(v2 -> timeDialog.dismiss());
            timeBinding.ok.setOnClickListener(v22 -> {
                binding.timeOfBirth.setText(timeBinding.timePicker.getHour() + ":" + timeBinding.timePicker.getMinute());
                timeDialog.dismiss();
            });
            timeDialog.show();
        });

        return binding.getRoot();
    }
}