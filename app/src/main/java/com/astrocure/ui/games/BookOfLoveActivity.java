package com.astrocure.ui.games;

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

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.R;
import com.astrocure.databinding.ActivityBookOfLoveBinding;

import java.util.Random;

public class BookOfLoveActivity extends AppCompatActivity{
    ActivityBookOfLoveBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookOfLoveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        TextPaint paint = binding.title.getPaint();
        Shader textShader = new LinearGradient(0, 0, paint.measureText(binding.title.getText().toString()), binding.title.getTextSize(),
                new int[]{
                        Color.parseColor("#FF0000"),
                        Color.parseColor("#FFFFFF"),/*
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),*/
                }, null, Shader.TileMode.CLAMP);
        binding.title.getPaint().setShader(textShader);

        binding.mainImage.setOnClickListener(v -> {
            binding.mainImage.setImageDrawable(getDrawable(R.drawable.book_of_love_note));
            binding.output.setText(randomString());
            binding.output.setVisibility(View.VISIBLE);
            binding.askAgain.setVisibility(View.VISIBLE);
            binding.textView6.setVisibility(View.GONE);
        });
        binding.askAgain.setOnClickListener(v -> {
            binding.mainImage.setImageDrawable(getDrawable(R.drawable.book_of_love));
            binding.output.setVisibility(View.GONE);
            binding.askAgain.setVisibility(View.GONE);
            binding.textView6.setVisibility(View.VISIBLE);
        });
    }

    public String randomString(){
        final String[] proper_noun = { "Yes","Probably", "Perhaps","Doubtful","Maybe","Its Fate!","Not Yet","Definitely","Not Advisable", "No"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }

}