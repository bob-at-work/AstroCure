package com.astrocure.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.astrocure.R;
import com.astrocure.databinding.ActivityAddPostBinding;

public class AddPostActivity extends AppCompatActivity {
    ActivityAddPostBinding binding;
    Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
        binding.postInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int stringSize = binding.postInput.getText().toString().length();
                binding.inputSize.setText(stringSize+"/470");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.camera.setOnClickListener(v -> {
            Intent chooser = Intent.createChooser(galleryIntent, "Select Image");
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { cameraIntent });
            startActivityForResult(chooser, 1221);
        });
    }
}