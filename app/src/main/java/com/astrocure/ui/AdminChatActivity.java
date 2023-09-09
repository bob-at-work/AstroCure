package com.astrocure.ui;

import static com.astrocure.utils.AppConstants.profileImg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.QuestionAnswerAdapter;
import com.astrocure.databinding.ActivityAdminChatBinding;
import com.astrocure.databinding.ItemFullscreenImageViewBinding;
import com.astrocure.models.QuestionAnswerChatModel;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminChatActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 603;
    ActivityAdminChatBinding binding;
    QuestionAnswerAdapter adapter;
    List<QuestionAnswerChatModel> modelList;
    ActivityResultLauncher<Intent> launcher;
    Intent cameraIntent;
    private Uri photoUri;

    @SuppressLint({"ClickableViewAccessibility", "QueryPermissionsNeeded", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        modelList = new ArrayList<>();
        modelList.add(new QuestionAnswerChatModel("", "app", "", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Apple", "user", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("generally yes, because different view types can correspond to completely different view layouts. ViewHolder is a common design pattern in android described", "admin", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("That's my point since only one ViewHolder is available in one RecyclerView.Adapter", "user", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Connect to the astrologer", "admin", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Astrologer", "admin", "12:06pm", true, false, null));
        modelList.add(new QuestionAnswerChatModel("Hello", "user", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel(null, "admin", "10:09pm", false, true, profileImg));
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm", false, false, null));
        adapter = new QuestionAnswerAdapter(getApplicationContext(), modelList);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.chatList.smoothScrollToPosition(binding.chatList.getAdapter().getItemCount() - 1);
        adapter.setOnClick((position, imageUrl) -> {
            Dialog dialog = new Dialog(AdminChatActivity.this, R.style.Theme_AstroCure);
            ItemFullscreenImageViewBinding imageViewBinding = ItemFullscreenImageViewBinding.inflate(getLayoutInflater());
            dialog.setContentView(imageViewBinding.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
            Glide.with(getApplicationContext()).load(imageUrl).into(imageViewBinding.image);
            dialog.setCancelable(false);
            imageViewBinding.close.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        });
        binding.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.message.getText().length() != 0) {
                    binding.camera.setVisibility(View.GONE);
                    binding.send.setVisibility(View.VISIBLE);
                } else {
                    binding.camera.setVisibility(View.VISIBLE);
                    binding.send.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.send.setOnClickListener(v -> {
            String currentTime = new SimpleDateFormat("HH:mma", Locale.getDefault()).format(new Date());
            modelList.add(new QuestionAnswerChatModel(binding.message.getText().toString(), "user", currentTime, false, false, null));
            adapter.notifyDataSetChanged();
            binding.chatList.smoothScrollToPosition(binding.chatList.getAdapter().getItemCount() - 1);
            binding.message.setText("");
        });
        binding.camera.setOnClickListener(v -> {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
            } else {
                if (cameraIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    File file = null;
                    try {
                        file = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    photoUri = null;
                    if (file != null) {
                        photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", file);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        launcher.launch(cameraIntent);
                    }
                }
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                try {
                    if (photoUri != null) {
                        String currentTime = new SimpleDateFormat("HH:mma", Locale.getDefault()).format(new Date());
                        modelList.add(new QuestionAnswerChatModel(null, "user", currentTime, false, true, photoUri.toString()));
                        adapter.notifyDataSetChanged();
                        binding.chatList.smoothScrollToPosition(modelList.size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Failed to capture", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (cameraIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    File file = null;
                    try {
                        file = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    photoUri = null;
                    if (file != null) {
                        photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", file);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        launcher.launch(cameraIntent);
                    }
                }
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(timeStamp, ".jpg", storageDir);
    }
}