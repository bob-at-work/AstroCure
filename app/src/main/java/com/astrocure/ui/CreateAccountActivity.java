package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.R;
import com.astrocure.databinding.ActivityCreateAccountBinding;
import com.astrocure.models.PinCodeModel;
import com.astrocure.viewmodel.CreateAccountViewModel;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding binding;
    CreateAccountViewModel viewModel;
    String[] location = new String[]{};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.next.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CreateAccountSecondActivity.class)));

        binding.fullName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.fullName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.text_input_focused));
                binding.nameHelperText.setText(binding.fullName.getHint());
                binding.fullName.setHint("");
            } else {
                binding.fullName.setHint(binding.nameHelperText.getText());
                binding.nameHelperText.setText("");
                binding.fullName.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.fullName.getText().length() > 0) {
                    binding.nameHelperText.setText(binding.fullName.getHint());
                    binding.fullName.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
        binding.dob.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.dob.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.dobHelperText.setText(binding.dob.getHint());
                binding.dob.setHint("");
            } else {
                binding.dob.setHint(binding.dobHelperText.getText());
                binding.dobHelperText.setText("");
                binding.dob.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.dob.getText().length() > 0) {
                    binding.dobHelperText.setText(binding.dob.getHint());
                    binding.dob.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
        binding.tob.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.tob.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.tobHelperText.setText(binding.tob.getHint());
                binding.tob.setHint("");
            } else {
                binding.tob.setHint(binding.tobHelperText.getText());
                binding.tobHelperText.setText("");
                binding.tob.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.tob.getText().length() > 0) {
                    binding.tobHelperText.setText(binding.tob.getHint());
                    binding.tob.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
        binding.location.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.location.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.locationHelperText.setText(binding.location.getHint());
                binding.location.setHint("");
            } else {
                binding.location.setHint(binding.locationHelperText.getText());
                binding.locationHelperText.setText("");
                binding.location.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.location.getText().length() > 0) {
                    binding.locationHelperText.setText(binding.location.getHint());
                    binding.location.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
        binding.pinCode.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.pinCode.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.pincodeHelperText.setText(binding.pinCode.getHint());
                binding.pinCode.setHint("");
            } else {
                binding.pinCode.setHint(binding.locationHelperText.getText());
                binding.pincodeHelperText.setText("");
                binding.pinCode.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.pinCode.getText().length() > 0) {
                    binding.pincodeHelperText.setText(binding.pinCode.getHint());
                    binding.pinCode.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });

        viewModel = new ViewModelProvider(this).get(CreateAccountViewModel.class);
        binding.location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.location.getText().length() > 2) {
                    new Handler().postDelayed(() -> viewModel.getLocation(binding.location.getText().toString()).observe(CreateAccountActivity.this, pinCodeModel -> {
                        if (pinCodeModel != null) {
                            int i = 0;
                            for (PinCodeModel.PostOffice codeModel : pinCodeModel.getPostOffice()) {
                                location[i] = codeModel.getName() + "," + codeModel.getRegion();
                            }
                            adapter = new ArrayAdapter<String>(CreateAccountActivity.this, android.R.layout.select_dialog_item, location);
                            binding.location.setThreshold(3);
                            binding.location.setAdapter(adapter);
                        }

                    }), 1500);

                }
            }
        });

    }
}