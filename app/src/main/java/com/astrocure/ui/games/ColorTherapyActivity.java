package com.astrocure.ui.games;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;

import com.astrocure.R;
import com.astrocure.databinding.ActivityColorTherapyBinding;

public class ColorTherapyActivity extends AppCompatActivity {
    ActivityColorTherapyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColorTherapyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
        TextPaint paint = binding.title.getPaint();
        Shader textShader = new LinearGradient(paint.measureText(binding.title.getText().toString()), binding.title.getTextSize(), 0, 0,
                new int[]{
                        Color.parseColor("#DCF924"),
                        Color.parseColor("#FF003D"),
                        Color.parseColor("#DCF924"),
                        Color.parseColor("#FF003D"),
//                        Color.parseColor("#FFD80E"),
//                        Color.parseColor("#08FB21"),
//                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.MIRROR);
        binding.title.getPaint().setShader(textShader);

    }
}