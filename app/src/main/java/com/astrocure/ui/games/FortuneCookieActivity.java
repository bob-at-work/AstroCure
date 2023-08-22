package com.astrocure.ui.games;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.databinding.ActivityFortuneCookieBinding;

import java.util.Random;

public class FortuneCookieActivity extends AppCompatActivity implements SensorEventListener {
    ActivityFortuneCookieBinding binding;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Vibrator vibrator;
    private static final float SHAKE_THRESHOLD = 5.25f;
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    private boolean isShaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFortuneCookieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        isShaken = false;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.askAgain.setOnClickListener(v -> {
            binding.output.setVisibility(View.GONE);
            binding.askAgain.setVisibility(View.GONE);
            binding.animation.reverseAnimationSpeed();
            binding.animation.playAnimation();
            binding.animation.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isShaken = false;
                    binding.animation.reverseAnimationSpeed();
                    binding.textView4.setVisibility(View.VISIBLE);
                    binding.animation.removeAllAnimatorListeners();
                }
            });
        });

    }

    public String randomString() {
        final String[] proper_noun = {"The best way to predict the future is to create it.", "Face the truth with dignity", "Pay attention to your family. Don't take them for granted.", "Tomorrow is another day.", "Lead by example, not by words alone.", "Be at peace with yourself.", "Love is right around the corner.", "A healthy body will benefit you for life.", "Just have fun", "You have good reason to be self confident.", "You might want to run, but you should stay and fight", "Take care of yourself first. Then help others.", "Open your mind and your heart to good things.", "Show everyone what you can do.", "Look in the mirror without admiring your reflection.", "Get ready for a life-changing event!", "You can learn much from people who are different from you.", "Everything will work out for the best.", "Respect your elders.You could inherit a large sum of money.", "Work first, but make sure to play later.", "Lead by example, not by words alone.", "Be thankful, even if your life is not perfect.", "Stop procrastinating. Make a decision already.", "Bravery is to stand up for what you believe in.", "Think it and you can say it. Say it and you can do it.", "A ship in harbor is safe, but that's not why ships are built.", "Sometimes good things fall apart, so better things can fall together.", "Don’t hold onto things that require a tight grip.", "Some things have to be believed to be seen.", "Look how far you've come.", "You attract what you are ready for.", "Progress, not perfection.", "To the world, you're one person. But to one person, you're the world.", "Your road to glory will be rocky, but fulfilling.", "If you want the rainbow, you have to tolerate the rain.", "The wise man is the one that makes you think that he is dumb.", "It takes less time to do a thing right than it does to explain why you did it wrong.", "Never forget that a half truth is a whole lie.", "If you do no run your subconscious mind yourself, someone else will.", "No snowflake in an avalanche ever feels responsible.", "About time, I got out of that cookie.", "Your heart will skip a beat.", "Perhaps, you’ve been focusing too much on spending.", "Three can keep a secret, if you get rid of two.", "Avoid taking unnecessary gambles.", "An old love will come back to you.”", "Your love life will soon be happy and harmonious", "Unlearn everything that you aren’t. Relearn everything that you are.”", "Change comes with embracing the future, not fighting your past.”", "Being deeply loved by someone gives you strength, while loving someone deeply gives you courage", "Always tell someone how you feel about them, you will feel much better when they know.", "Relationships, especially, are not always a competition. Sometimes it is better to be loving than to be right or win."};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                if (acceleration > SHAKE_THRESHOLD) {
                    if (!isShaken) {
                        mLastShakeTime = curTime;
                        binding.textView4.setVisibility(View.GONE);
                        binding.askAgain.setVisibility(View.VISIBLE);
                        binding.animation.playAnimation();
                        binding.animation.addAnimatorListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                binding.output.setVisibility(View.VISIBLE);
                                binding.output.setText(randomString());
                                binding.animation.removeAllAnimatorListeners();
                            }
                        });
                        isShaken = true;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}