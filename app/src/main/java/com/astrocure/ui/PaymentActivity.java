package com.astrocure.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityPaymentBinding;
import com.astrocure.databinding.DialogAddNewCardBinding;
import com.astrocure.databinding.DialogAddUpiBinding;
import com.astrocure.databinding.DialogNetBankingBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PaymentActivity extends AppCompatActivity {
    ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.card.setOnClickListener(v -> {
            DialogAddNewCardBinding cardBinding = DialogAddNewCardBinding.inflate(getLayoutInflater());
            BottomSheetDialog dialog = new BottomSheetDialog(PaymentActivity.this);
            dialog.setContentView(cardBinding.getRoot());
            dialog.show();
        });

        binding.addNewUpi.setOnClickListener(v -> {
            DialogAddUpiBinding upiBinding = DialogAddUpiBinding.inflate(getLayoutInflater());
            BottomSheetDialog dialog = new BottomSheetDialog(PaymentActivity.this);
            dialog.setContentView(upiBinding.getRoot());
            dialog.show();
        });

        binding.netBanking.setOnClickListener(v -> {
            DialogNetBankingBinding netBankingBinding = DialogNetBankingBinding.inflate(getLayoutInflater());
            BottomSheetDialog dialog = new BottomSheetDialog(PaymentActivity.this);
            dialog.setContentView(netBankingBinding.getRoot());
            dialog.show();
        });
    }
}