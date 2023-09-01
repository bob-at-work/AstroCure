package com.astrocure.ui;

import android.annotation.SuppressLint;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.astrocure.R;
import com.astrocure.databinding.ActivityVideoViewBinding;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class VideoViewActivity extends AppCompatActivity {

    ActivityVideoViewBinding binding;
    String videoUrl;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.close.setOnClickListener(v -> onBackPressed());

        videoUrl = getIntent().getStringExtra("videoUrl");

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        if (Build.VERSION.SDK_INT >= 14) {
            retriever.setDataSource(videoUrl, new HashMap<>());
        } else {
            retriever.setDataSource(videoUrl);
        }

        long millis = Long.parseLong(Objects.requireNonNull(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
        @SuppressLint("DefaultLocale") String hour = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millis));
        @SuppressLint("DefaultLocale") String duration = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

        binding.duration.setText(" / " + (hour.matches("00") ? "" : hour + ":") + duration);
        binding.seek.setMax((int) millis);
        binding.playPause.setOnClickListener(v -> {
            if (binding.videoView.isPlaying()) {
                binding.videoView.pause();
                binding.playPause.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_video_play));
            } else {
                binding.videoView.start();
                binding.playPause.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_video_pause));
            }
        });

        binding.seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    binding.videoView.seekTo(progress);
                    final int duration1 = binding.videoView.getDuration();
                    int time = (duration1 - binding.videoView.getCurrentPosition());
                    @SuppressLint("DefaultLocale") String interval = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)), TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
                    binding.interval.setText(interval);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Uri uri = Uri.parse(videoUrl);
        binding.videoView.setVideoURI(uri);
        binding.videoView.start();
        binding.videoView.setOnPreparedListener(mp -> {
            float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
            float screenRatio = binding.videoView.getWidth() / (float) binding.videoView.getHeight();
            float scaleX = videoRatio / screenRatio;
            if (scaleX >= 1f) {
                binding.videoView.setScaleX(scaleX);
//                binding.videoView.setScaleX(scaleX);
            } else {
                binding.videoView.setScaleY(1f / scaleX);
//                binding.videoView.setScaleY(scaleX);
            }


            final int duration1 = binding.videoView.getDuration();

            new Thread(() -> {
                do {

                    binding.interval.post(() -> {

                        int time = (duration1 - binding.videoView.getCurrentPosition());
                        @SuppressLint("DefaultLocale") String interval = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)), TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
                        binding.interval.setText(interval);
                        binding.seek.setProgress(binding.videoView.getCurrentPosition());
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//
                } while (binding.videoView.getCurrentPosition() < duration1);
            }).start();
        });


    }
}