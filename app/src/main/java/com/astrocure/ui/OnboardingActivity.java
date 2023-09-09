package com.astrocure.ui;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.R;
import com.astrocure.adapters.OnboardingViewpagerAdapter;
import com.astrocure.databinding.ActivityOnboardingBinding;
import com.astrocure.models.OnboardingSwipeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OnboardingActivity extends AppCompatActivity {
    ActivityOnboardingBinding binding;
    List<OnboardingSwipeModel> swipeModels;
    OnboardingViewpagerAdapter onboardingViewpagerAdapter;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(60000L);
        animator.addUpdateListener(animation -> {
            final float progress = (float) animation.getAnimatedValue();
            final float width = binding.bg1.getWidth();
            final float translationX = width * progress;
            binding.bg1.setTranslationX(translationX);
            binding.bg2.setTranslationX(translationX - width);
        });
        animator.start();

        binding.login.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)));
        binding.recover.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RecoverAccountActivity.class)));
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);
        new Handler().postDelayed(() -> {
            binding.logo.startAnimation(animation);
        }, 1500);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.tabLayout.setVisibility(View.VISIBLE);
                binding.animSlider.setVisibility(View.VISIBLE);
                animator.removeAllListeners();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        swipeModels = new ArrayList<>();
        swipeModels.add(new OnboardingSwipeModel("Daily Horoscopes", "We offer personalised daily horoscope prophecies\nand horoscopes for entire week", R.raw.horoscope));
        swipeModels.add(new OnboardingSwipeModel("Compatibility", "Discover your level of Compatibility with\nwith someone according to astrology", R.raw.compatiblity));
        swipeModels.add(new OnboardingSwipeModel("Feed Section", "Stay Informed and Inspired\nwith Our Captivating Feed", R.raw.feeds));
        swipeModels.add(new OnboardingSwipeModel("Astro Counselling", "\"Enhance Your Journey with\nProfound Astro Guidance", R.raw.astro_counselling));
        swipeModels.add(new OnboardingSwipeModel("Video Section", "Your Vision, Your Fortune\nPredict and Win Big with Video Forecasts!", R.raw.video));
        onboardingViewpagerAdapter = new OnboardingViewpagerAdapter(getApplicationContext(), swipeModels);
        binding.animSlider.setAdapter(onboardingViewpagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.animSlider);

        setBannerLooper();
    }

    private void setBannerLooper() {
        final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (binding.animSlider.getCurrentItem() == onboardingViewpagerAdapter.getCount() - 1) { //adapter is your custom ViewPager's adapter
                binding.animSlider.setCurrentItem(0, false);
            } else {
                binding.animSlider.setCurrentItem(binding.animSlider.getCurrentItem() + 1, false);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }
}