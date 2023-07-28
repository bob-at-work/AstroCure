package com.astrocure.ui.games;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.astrocure.R;
import com.astrocure.databinding.ActivityColorTherapyBinding;
import com.astrocure.databinding.DialogColorTherapyBinding;
import com.astrocure.utils.AppConstantMethods;

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


        binding.red.setOnClickListener(v -> {
            setFlipCard(R.drawable.red,R.string.dummy_1);
        });
        binding.white.setOnClickListener(v -> {
            setFlipCard(R.drawable.white,R.string.dummy_2);
        });
        binding.yellow.setOnClickListener(v -> {
            setFlipCard(R.drawable.yellow,R.string.dummy_3);
        });
        binding.brown.setOnClickListener(v -> {
            setFlipCard(R.drawable.brown,R.string.dummy_1);
        });
        binding.purple.setOnClickListener(v -> {
            setFlipCard(R.drawable.purple,R.string.dummy_2);
        });
        binding.pink.setOnClickListener(v -> {
            setFlipCard(R.drawable.pink,R.string.dummy_3);
        });
        binding.gray.setOnClickListener(v -> {
            setFlipCard(R.drawable.grey,R.string.dummy_1);
        });
        binding.black.setOnClickListener(v -> {
            setFlipCard(R.drawable.black,R.string.dummy_2);
        });
        binding.green.setOnClickListener(v -> {
            setFlipCard(R.drawable.green,R.string.dummy_3);
        });

    }

    private void setFlipCard(int drawable, int text) {
        Dialog dialog = new Dialog(ColorTherapyActivity.this);
        DialogColorTherapyBinding therapyBinding = DialogColorTherapyBinding.inflate(getLayoutInflater());
        dialog.setContentView(therapyBinding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        therapyBinding.textLayout.predictedText.setText(text);
        therapyBinding.cardLayout.colorCard.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),drawable));
        AppConstantMethods.flipCard(getApplicationContext(), therapyBinding.viewBack, therapyBinding.viewFront);
        dialog.show();
    }
}