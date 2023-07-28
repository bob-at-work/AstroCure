package com.astrocure.utils;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.astrocure.R;

import java.io.IOException;
import java.io.InputStream;

public class AppConstantMethods {

    public static String loadJSONFromAsset(Activity activity, String fileName) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String loadJSONFromAssets(Context context, String fileName) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void flipCard(Context context, View visibleView, View inVisibleView) {
        try {
            visibleView.setVisibility(View.VISIBLE);
            Float scale = context.getResources().getDisplayMetrics().density;
            Float cameraDistance = 8000*scale;
            visibleView.setCameraDistance(cameraDistance);
            inVisibleView.setCameraDistance(cameraDistance);
            AnimatorSet flipOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_out);
            flipOutAnimatorSet.setTarget(inVisibleView);
            AnimatorSet flipInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.flip_in);
            flipInAnimatorSet.setTarget(visibleView);
            flipOutAnimatorSet.start();
            flipInAnimatorSet.start();
            flipInAnimatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {
                    inVisibleView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
