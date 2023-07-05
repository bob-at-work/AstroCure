package com.astrocure.ui.games;

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
            isShaken = false;
            binding.image.setVisibility(View.VISIBLE);
            binding.textView4.setVisibility(View.VISIBLE);
            binding.output.setVisibility(View.GONE);
            binding.askAgain.setVisibility(View.GONE);
            binding.outputImage.setVisibility(View.GONE);
        });
    }

    public String randomString() {
        final String[] proper_noun = {
                "The best way to predict the future is to create it.",
                "Face the truth with dignity",
                "Pay attention to your family. Don't take them for granted.",
                "Tomorrow is another day.",
                "Lead by example, not by words alone.",
                "Be at peace with yourself.",
                "Love is right around the corner.",
                "A healthy body will benefit you for life.",
                "Just have fun",
                "You have good reason to be self confident.",
                "You might want to run, but you should stay and fight",
                "Take care of yourself first. Then help others.",
                "Open your mind and your heart to good things.",
                "Show everyone what you can do.",
                "Look in the mirror without admiring your reflection.",
                "Get ready for a life-changing event!",
                "You can learn much from people who are different from you.",
                "Everything will work out for the best.",
                "Respect your elders.You could inherit a large sum of money.",
                "Work first, but make sure to play later.",
                "Lead by example, not by words alone.",
                "Be thankful, even if your life is not perfect.",
                "Stop procrastinating. Make a decision already."
        };
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

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                if (acceleration > SHAKE_THRESHOLD) {
                    if (!isShaken) {
                        mLastShakeTime = curTime;
                        binding.outputImage.setVisibility(View.VISIBLE);
                        binding.output.setVisibility(View.VISIBLE);
                        binding.textView4.setVisibility(View.GONE);
                        binding.askAgain.setVisibility(View.VISIBLE);
                        binding.output.setText(randomString());
                        binding.image.setVisibility(View.GONE);
//                        binding.imageView.setImageDrawable(getDrawable(R.drawable.open_fortune_cookie));
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