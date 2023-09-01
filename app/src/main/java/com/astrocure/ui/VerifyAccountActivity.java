package com.astrocure.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityVerifyAccountBinding;
import com.astrocure.databinding.DialogVerificationSuccessBinding;

import java.util.Objects;

public class VerifyAccountActivity extends AppCompatActivity {
    ActivityVerifyAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        binding.send.setOnClickListener(v -> {
            DialogVerificationSuccessBinding dialogBinding = DialogVerificationSuccessBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(VerifyAccountActivity.this);
            dialog.setContentView(dialogBinding.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialogBinding.done.setOnClickListener(v1 -> dialog.dismiss());
            dialog.show();
        });
    }
}