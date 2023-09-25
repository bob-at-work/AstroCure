package com.astrocure.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.R;
import com.astrocure.databinding.ActivityCompatibilitySecondBinding;
import com.astrocure.databinding.DialogBottomCreateCustomBinding;
import com.astrocure.databinding.DialogBottomYouBinding;
import com.astrocure.utils.AppConstants;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CompatibilitySecondActivity extends AppCompatActivity {

    ActivityCompatibilitySecondBinding binding;
    boolean userSelected = false;
    boolean partnerSelected = false;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompatibilitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
        Glide.with(getApplicationContext()).load(AppConstants.profileImg).into(binding.you);

        binding.checkBond.setOnClickListener(v -> {
//            new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), CompatibilitySecondResultActivity.class));
//            },5000);

        });

        binding.you.setOnClickListener(v -> {
            DialogBottomYouBinding youBinding = DialogBottomYouBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CompatibilitySecondActivity.this, R.style.BottomSheetDialog);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.setContentView(youBinding.getRoot());
            youBinding.selectOwn.setChecked(true);
            youBinding.name.setText("Cameron Diaz");
            youBinding.tob.setText("12:00 PM");
            youBinding.dob.setText("12 Dec 1997");
            youBinding.location.setText("Home Sweet Home");
            youBinding.selectOwn.setOnClickListener(v1 -> {
                if (youBinding.selectOwn.isChecked()) {
                    youBinding.selectOwn.setChecked(false);
                    youBinding.selectOwn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_box_unchecked, 0, 0, 0);
                    youBinding.name.setText("");
                    youBinding.tob.setText("");
                    youBinding.dob.setText("");
                    youBinding.location.setText("");
                    youBinding.name.setEnabled(true);
                    youBinding.tob.setEnabled(true);
                    youBinding.dob.setEnabled(true);
                    youBinding.location.setEnabled(true);
                } else {
                    youBinding.selectOwn.setChecked(true);
                    youBinding.selectOwn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_box_checked, 0, 0, 0);
                    youBinding.name.setText("Cameron Diaz");
                    youBinding.tob.setText("12:00 PM");
                    youBinding.dob.setText("12 Dec 1997");
                    youBinding.location.setText("Home Sweet Home");
                    youBinding.name.setEnabled(false);
                    youBinding.tob.setEnabled(false);
                    youBinding.dob.setEnabled(false);
                    youBinding.location.setEnabled(false);
                }
            });
            youBinding.setProfile.setOnClickListener(v12 -> {
                Glide.with(getApplicationContext()).load(AppConstants.profileImg).into(binding.firstUser);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.user_img_anim_1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        binding.closeUser.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                binding.userContainer.startAnimation(animation);
                userSelected = true;
                if (partnerSelected) {
                    binding.checkBond.setVisibility(View.VISIBLE);
                    binding.heartAnimView.setVisibility(View.VISIBLE);
                }
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.show();
        });

        binding.publicFigures.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SelectPublicFigureActivity.class));
        });

        binding.createCustom.setOnClickListener(v -> {
            DialogBottomCreateCustomBinding customBinding = DialogBottomCreateCustomBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CompatibilitySecondActivity.this, R.style.BottomSheetDialog);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.setContentView(customBinding.getRoot());
            customBinding.check.setOnClickListener(v13 -> {
                Glide.with(getApplicationContext()).load(AppConstants.profileImg).into(binding.secondUser);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.partner_img_anim_1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        binding.closePartner.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                binding.partnerContainer.startAnimation(animation);
                partnerSelected = true;
                if (userSelected) {
                    binding.checkBond.setVisibility(View.VISIBLE);
                    binding.heartAnimView.setVisibility(View.VISIBLE);
                }
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.show();
        });

        binding.closeUser.setOnClickListener(v -> {
            binding.checkBond.setVisibility(View.GONE);
            binding.closeUser.setVisibility(View.GONE);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.user_img_rev_anim_1);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    userSelected = false;
                    Glide.with(getApplicationContext()).load(R.drawable.ic_compatibility_place_holder_first).into(binding.firstUser);
                    binding.heartAnimView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            binding.userContainer.startAnimation(animation);
        });

        binding.closePartner.setOnClickListener(v -> {
            binding.checkBond.setVisibility(View.GONE);
            binding.closePartner.setVisibility(View.GONE);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.partner_img_rev_anim_1);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    partnerSelected = false;
                    Glide.with(getApplicationContext()).load(R.drawable.ic_compatibility_place_holder_second).into(binding.secondUser);
                    binding.heartAnimView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            binding.partnerContainer.startAnimation(animation);
        });

    }
}