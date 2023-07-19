package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.astrocure.R;
import com.astrocure.databinding.ActivityCreateAccountSecondBinding;

public class CreateAccountSecondActivity extends AppCompatActivity {
    ActivityCreateAccountSecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.language.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.language.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.languageHelperText.setText(binding.language.getHint());
                binding.language.setHint("");
            }else {
                binding.language.setHint(binding.languageHelperText.getText());
                binding.languageHelperText.setText("");
                binding.language.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.language.getText().length()>0){
                    binding.languageHelperText.setText(binding.language.getHint());
                    binding.language.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });

        binding.gender.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.gender.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.genderHelperText.setText(binding.gender.getHint());
                binding.gender.setHint("");
            }else {
                binding.gender.setHint(binding.genderHelperText.getText());
                binding.genderHelperText.setText("");
                binding.gender.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.gender.getText().length()>0){
                    binding.genderHelperText.setText(binding.gender.getHint());
                    binding.gender.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
        binding.email.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.email.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.emailHelperText.setText(binding.email.getHint());
                binding.email.setHint("");
            }else {
                binding.email.setHint(binding.emailHelperText.getText());
                binding.emailHelperText.setText("");
                binding.email.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.email.getText().length()>0){
                    binding.emailHelperText.setText(binding.email.getHint());
                    binding.email.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
        binding.cameraPath.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.cameraPath.setBackground(getDrawable(R.drawable.text_input_focused));
                binding.cameraPathHelperText.setText(binding.cameraPath.getHint());
                binding.cameraPath.setHint("");
            }else {
                binding.cameraPath.setHint(binding.cameraPathHelperText.getText());
                binding.cameraPathHelperText.setText("");
                binding.cameraPath.setBackground(getDrawable(R.drawable.text_input_unfocused));
                if (binding.cameraPath.getText().length()>0){
                    binding.cameraPathHelperText.setText(binding.cameraPath.getHint());
                    binding.cameraPath.setBackground(getDrawable(R.drawable.text_input_complete));
                }
            }
        });
    }
}