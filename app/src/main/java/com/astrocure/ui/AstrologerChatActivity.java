package com.astrocure.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.AstrologerChatAdapter;
import com.astrocure.databinding.ActivityAstrologerChatBinding;
import com.astrocure.models.AstrologerChatModel;
import com.astrocure.utils.AudioRecorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AstrologerChatActivity extends AppCompatActivity {
    ActivityAstrologerChatBinding binding;
    AstrologerChatAdapter adapter;
    List<AstrologerChatModel> models;
    MediaRecorder recorder;
    String fileName;
    AudioRecorder audioRecorder;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        String prev = getIntent().getStringExtra("previous");
        if (prev != null && (prev.matches("TransactionChatHistoryAdapter") || prev.matches("ChatCallLogAdapter"))) {
            binding.message.setVisibility(View.GONE);
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

        binding.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.message.getText().length() != 0) {
                    binding.voiceRecord.setVisibility(View.GONE);
                    binding.message.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.send, 0);
                } else {
                    binding.voiceRecord.setVisibility(View.VISIBLE);
                    binding.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fluent_camera, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        models = new ArrayList<>();
        models.add(new AstrologerChatModel("Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.", "5:45pm", true));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", false));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", true));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", false));
        models.add(new AstrologerChatModel("Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.", "5:45pm", true));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", false));
        models.add(new AstrologerChatModel("Tempus nunc amet.", "5:45pm", true));
        models.add(new AstrologerChatModel("Tempus nunc ametTempus nunc amet..", "5:45pm", false));
        adapter = new AstrologerChatAdapter(getApplicationContext(), models);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);


        binding.voiceRecord.setOnClickListener(v -> {

        });


        binding.voiceRecord.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // start recording.
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(AstrologerChatActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_PERMISSION_CODE);

                } else {
                    try {
                        audioRecorder = new AudioRecorder("Astrocure");
                        audioRecorder.start();
                        Toast.makeText(this, "Recording", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Stop recording and save file
                try {
                    audioRecorder.stop();
                    Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
            return false;
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
        }
    }
}