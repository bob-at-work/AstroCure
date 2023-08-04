package com.astrocure.ui.games;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextPaint;
import android.view.View;

import com.astrocure.R;
import com.astrocure.databinding.ActivityCrystallBallBinding;

import java.util.Random;

public class CrystalBallActivity extends AppCompatActivity{
    ActivityCrystallBallBinding binding;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrystallBallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        TextPaint paint = binding.title.getPaint();
        Shader textShader = new LinearGradient(0, 0, paint.measureText(binding.title.getText().toString()), binding.title.getTextSize(),
                new int[]{
                        Color.parseColor("#DBFF00"),
                        Color.parseColor("#FFFFFF"),/*
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),*/
                }, null, Shader.TileMode.CLAMP);
        binding.title.getPaint().setShader(textShader);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.crystalBall.setOnClickListener(v -> {
            binding.output.setText(randomString());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
            }else {
                vibrator.vibrate(300);
            }
            binding.output.setVisibility(View.VISIBLE);
            binding.askAgain.setVisibility(View.VISIBLE);
            binding.crystalBall.setVisibility(View.GONE);
            binding.textView5.setVisibility(View.GONE);
        });

        binding.askAgain.setOnClickListener(v -> {
            binding.output.setVisibility(View.GONE);
            binding.askAgain.setVisibility(View.GONE);
            binding.textView5.setVisibility(View.VISIBLE);
            binding.crystalBall.setVisibility(View.VISIBLE);
        });
    }

    public String randomString(){
        final String[] proper_noun = { "Yes","Probably", "Perhaps","Doubtful","Maybe","Its Fate!","Not Yet","Definitely","Not Advisable", "No"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }

}