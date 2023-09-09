package com.astrocure.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.FeedImageAdapter;
import com.astrocure.databinding.ActivityAddPostBinding;

import java.util.ArrayList;
import java.util.List;

public class AddPostActivity extends AppCompatActivity {
    ActivityAddPostBinding binding;
    ActivityResultLauncher<Intent> launcher;
    List<Uri> imageUriList;
    FeedImageAdapter imageAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        if (getIntent().getStringExtra("feed_content") != null) {
            binding.postInput.setText(getIntent().getStringExtra("feed_content"));
        }
        imageUriList = new ArrayList<>();
        binding.postInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int stringSize = binding.postInput.getText().toString().length();
                binding.inputSize.setText(stringSize + "/470");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.camera.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            launcher.launch(intent);
        });

        binding.post.setOnClickListener(v -> finish());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Log.i("TAG", "onCreate: gallery " + result.getResultCode());
            if (result.getResultCode() == RESULT_OK) {
                try {
                    if (result.getData().getClipData() != null) {
                        int count = result.getData().getClipData().getItemCount();
                        for (int i = 0; i < count; i++) {
                            if ((imageUriList.size() <= 3)) {
                                imageUriList.add(result.getData().getClipData().getItemAt(i).getUri());
                            }
                        }
                        imageAdapter.notifyDataSetChanged();
                    } else if (result.getData() != null) {
                        if (imageUriList.size() <= 3) {
                            imageUriList.add(result.getData().getData());
                        }
                        imageAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    throw e;
                }

            }
        });
        processImage();
    }

    private void processImage() {
        imageAdapter = new FeedImageAdapter(getApplicationContext(), imageUriList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.imageList.setLayoutManager(linearLayoutManager);
        binding.imageList.setAdapter(imageAdapter);
        imageAdapter.setOnItemClick(position -> {
            imageUriList.remove(position);
            imageAdapter.notifyItemRemoved(position);
            imageAdapter.notifyItemRangeChanged(position, imageUriList.size());
        });
    }
}