package com.astrocure.ui.games;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.R;
import com.astrocure.databinding.ActivityAstroGenieBinding;

import java.util.Random;

public class AstroGenieActivity extends AppCompatActivity {
    ActivityAstroGenieBinding binding;
    private boolean isShaken;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstroGenieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        isShaken = false;

        binding.back.setOnClickListener(v -> onBackPressed());

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        TextPaint paint = binding.title.getPaint();
        Shader textShader = new LinearGradient(0, 0, paint.measureText(binding.title.getText().toString()), binding.title.getTextSize(), new int[]{Color.parseColor("#53129E"), Color.parseColor("#FFFFFF"),/*
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),*/}, null, Shader.TileMode.CLAMP);
        binding.title.getPaint().setShader(textShader);

        binding.pot.setOnClickListener(v -> {
            binding.imageView4.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
            binding.result.setText(randomString());
            binding.textView1.setVisibility(View.GONE);
        });

        binding.genieAnim.setOnLongClickListener(v -> {
            if (!isShaken && binding.askAgain.getVisibility() == View.INVISIBLE) {
                isShaken = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(300);
                }
                binding.genieAnim.playAnimation();
                binding.textView1.setVisibility(View.INVISIBLE);
                binding.genieAnim.addAnimatorListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.askAgain.setVisibility(View.VISIBLE);
                        binding.result.setText(randomString());
                        binding.result.setVisibility(View.VISIBLE);
                        binding.genieAnim.removeAllAnimatorListeners();
                    }
                });

            }

            return false;
        });

        binding.askAgain.setOnClickListener(v -> {
            if (isShaken) {
                isShaken = false;
                binding.genieAnim.reverseAnimationSpeed();
                binding.genieAnim.playAnimation();
                binding.genieAnim.addAnimatorListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.askAgain.setVisibility(View.INVISIBLE);
                        binding.textView1.setVisibility(View.VISIBLE);
                        binding.result.setVisibility(View.GONE);
                        binding.genieAnim.reverseAnimationSpeed();
                        binding.genieAnim.removeAllAnimatorListeners();
                    }
                });
            }
        });
    }

    public String randomString() {
        final String[] proper_noun = {"Yes", "Probably", "Perhaps", "Doubtful", "Maybe", "Its Fate!", "Not Yet", "Definitely", "Not Advisable", "No"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }
}