package com.astrocure.ui.games;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.astrocure.R;
import com.astrocure.databinding.ActivityBookOfLoveBinding;

import java.util.Random;

public class BookOfLoveActivity extends AppCompatActivity {
    ActivityBookOfLoveBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookOfLoveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        TextPaint paint = binding.title.getPaint();
        Shader textShader = new LinearGradient(0, 0, paint.measureText(binding.title.getText().toString()), binding.title.getTextSize(), new int[]{Color.parseColor("#FF0000"), Color.parseColor("#FFFFFF"),/*
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),*/}, null, Shader.TileMode.CLAMP);
        binding.title.getPaint().setShader(textShader);

        binding.mainImage.setOnClickListener(v -> {
            if (binding.askAgain.getVisibility() == View.GONE) {
                binding.animationView.setMinAndMaxProgress(0.0f, 0.67f);
                binding.animationView.setVisibility(View.VISIBLE);
                binding.mainImage.animate().alpha(0.0f);
                binding.animationView.playAnimation();
                binding.output.setText(randomString());
                binding.askAgain.setVisibility(View.VISIBLE);
                binding.textView6.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.output.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });
        binding.askAgain.setOnClickListener(v -> {
            binding.animationView.setMinAndMaxProgress(0.67f, 1.0f);
            binding.animationView.playAnimation();
            binding.output.setVisibility(View.GONE);
            binding.animationView.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    binding.animationView.setVisibility(View.GONE);
                    binding.askAgain.setVisibility(View.GONE);
                    binding.textView6.setVisibility(View.VISIBLE);
                    binding.mainImage.animate().alpha(1.0f);
                    binding.mainImage.setVisibility(View.VISIBLE);
                    binding.mainImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.book_of_love));
                    binding.animationView.removeAllAnimatorListeners();
                }
            });
        });


    }

    public String randomString() {
        final String[] proper_noun = {"Yes", "Probably", "Perhaps", "Doubtful", "Maybe", "Its Fate!", "Not Yet", "Definitely", "Not Advisable", "No", "Conceivably", "Unlikely", "Quite credible", "Obtainable", "Possible", "Most likely", "Uncertain", "Contingent", "Indecisive", "Unsure", "Borderline", "Ambiguous", "Equivocal", "Destiny", "Kismat", "Euphoria", "Contentment"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }

}