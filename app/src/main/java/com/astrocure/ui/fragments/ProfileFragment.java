package com.astrocure.ui.fragments;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

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


//        binding.animDemo.setAnimation(R.raw.animation);
        binding.execution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.animDemo.resumeAnimation();
//                try {
                    Log.i("TAG", "onCreateView: "+binding.animDemo.getDuration());
//                }catch (Exception e){
//                    Log.i("TAG", "onCreateView: ");
//                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.animDemo.pauseAnimation();
            }
        },500);


        ShapeAppearanceModel.Builder shape = ShapeAppearanceModel.builder().setAllCornerSizes(16);
//        Drawable drawable = MaterialShapeDrawable.

        return binding.getRoot();
    }
}