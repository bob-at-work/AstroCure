package com.astrocure.ui;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.astrocure.utils.AppConstants.profileImg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
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
import com.astrocure.adapters.AstrologerChatAdapter;
import com.astrocure.databinding.ActivityAstrologerChatBinding;
import com.astrocure.databinding.ItemFullscreenImageViewBinding;
import com.astrocure.models.AstrologerChatModel;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AstrologerChatActivity extends AppCompatActivity {
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    private static final int CAMERA_REQUEST = 603;
    ActivityAstrologerChatBinding binding;
    AstrologerChatAdapter adapter;
    List<AstrologerChatModel> models;
    MediaRecorder recorder;
    MediaPlayer mediaPlayer;
    String fileName;
    boolean isRecording = false;
    ActivityResultLauncher<Intent> launcher;
    Intent cameraIntent;
    private Uri photoUri;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        String prev = getIntent().getStringExtra("previous");
        if (prev != null && (prev.matches("TransactionChatHistoryAdapter") || prev.matches("ChatCallLogAdapter"))) {
            binding.messageContainer.setVisibility(View.GONE);
            binding.voiceRecord.setVisibility(View.GONE);
            binding.typingStatus.setVisibility(View.GONE);
            binding.timer.setVisibility(View.GONE);
            binding.timerLabel.setVisibility(View.GONE);
            binding.imageView10.setVisibility(View.GONE);
            binding.toolbar.getMenu().findItem(R.id.end_chat).setVisible(false);
            binding.toolbar.getMenu().findItem(R.id.report_astrologer).setVisible(false);
            binding.toolbar.getMenu().findItem(R.id.delete_chat).setVisible(false);
        }

        binding.toolbar.getOverflowIcon().setTint(Color.WHITE);

        Glide.with(getApplicationContext()).load(profileImg).into(binding.image);
        binding.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.message.getText().length() != 0) {
                    binding.voiceRecord.setVisibility(View.GONE);
                    binding.send.setVisibility(View.VISIBLE);
                    binding.camera.setVisibility(View.GONE);
//                    binding.message.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.send, 0);
                } else {
                    binding.voiceRecord.setVisibility(View.VISIBLE);
                    binding.camera.setVisibility(View.VISIBLE);
                    binding.send.setVisibility(View.GONE);
//                    binding.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fluent_camera, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        models = new ArrayList<>();
        models.add(new AstrologerChatModel("Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.", "5:45pm", true, null, null));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", false, null, null));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", true, null, null));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", false, null, null));
        models.add(new AstrologerChatModel("Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.", "5:45pm", true, null, null));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", false, null, null));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", true, null, null));
        models.add(new AstrologerChatModel("Tempus nunc ametTempus nunc amet..", "5:45pm", false, null, null));
        models.add(new AstrologerChatModel("Tempus nunc ametTempus nunc amet..", "5:45pm", true, null, profileImg));
        models.add(new AstrologerChatModel("Tempus nunc ametTempus nunc amet..", "5:45pm", false, null, profileImg));
        adapter = new AstrologerChatAdapter(getApplicationContext(), models);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.chatList.smoothScrollToPosition(models.size());
        adapter.setOnClick((position, imageUrl) -> {
            Dialog dialog = new Dialog(AstrologerChatActivity.this, R.style.Theme_AstroCure);
            ItemFullscreenImageViewBinding imageViewBinding = ItemFullscreenImageViewBinding.inflate(getLayoutInflater());
            dialog.setContentView(imageViewBinding.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 0, 0, 0)));
            Glide.with(getApplicationContext()).load(imageUrl).into(imageViewBinding.image);
            dialog.setCancelable(false);
            imageViewBinding.close.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        });

        binding.send.setOnClickListener(v -> {
            if (!binding.message.getText().toString().isEmpty()) {
                models.add(new AstrologerChatModel(binding.message.getText().toString(), "5:45pm", false, null, null));
                adapter.notifyDataSetChanged();
                binding.chatList.smoothScrollToPosition(models.size());
                binding.message.setText("");
            }
        });
        binding.voiceRecord.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // start recording.
                if (!isRecording) {
                    try {
                        fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/astrocure.mp3";
                        startRecording(fileName);
                    } catch (IOException e) {
                        fileName = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC) + "/astrocure.mp3";
                        try {
                            startRecording(fileName);
                        } catch (IOException ex) {
                            e.printStackTrace();
                            Toast.makeText(this, "Unable To Record", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Stop recording and save file
                new Handler().postDelayed(() -> {
                    if (isRecording) {
                        stopRecording();
                    }
                }, 2000);

                return true;
            }
            return false;
        });
        binding.camera.setOnClickListener(v -> {
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
                        models.add(new AstrologerChatModel(null, currentTime, false, null, photoUri.toString()));
                        adapter.notifyDataSetChanged();
                        binding.chatList.smoothScrollToPosition(models.size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Failed to capture", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case CAMERA_REQUEST:
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
                break;
        }
    }

    private void startRecording(String fileName) throws IOException {
        if (CheckPermissions()) {

            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            recorder.setOutputFile(fileName);
//            try {
            recorder.prepare();
            recorder.start();
            isRecording = true;
            Toast.makeText(this, "Recording Started", Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Unable To Record", Toast.LENGTH_SHORT).show();
//            }

        } else {
            RequestPermissions();
        }
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
        isRecording = false;
        models.add(new AstrologerChatModel("", "03:55pm", false, fileName, null));
        adapter.notifyDataSetChanged();
        binding.chatList.smoothScrollToPosition(models.size());
        Toast.makeText(this, "Recording Stopped", Toast.LENGTH_SHORT).show();
//        playAudio(fileName);
    }

    private void RequestPermissions() {
        ActivityCompat.requestPermissions(AstrologerChatActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }

    private void playAudio(String fileName) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(timeStamp, ".jpg", storageDir);
    }
}