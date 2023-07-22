package com.astrocure.ui.fragments;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrocure.R;
import com.astrocure.databinding.FragmentProfileBinding;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);


//        binding.animDemo.setAnimation(R.raw.progress_anim);
        binding.execution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.animDemo.resumeAnimation();
            }
        });

        new Handler().postDelayed(() -> binding.animDemo.pauseAnimation(),750);

        binding.animDemo.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

        ShapeAppearanceModel.Builder shape = ShapeAppearanceModel.builder().setAllCornerSizes(16);
//        Drawable drawable = MaterialShapeDrawable.

        return binding.getRoot();
    }
}