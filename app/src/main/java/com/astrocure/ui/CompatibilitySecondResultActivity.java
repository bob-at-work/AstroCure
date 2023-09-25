package com.astrocure.ui;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.R;
import com.astrocure.adapters.CompatibilityViewpagerAdapter;
import com.astrocure.databinding.ActivityCompatibilitySecondResultBinding;
import com.astrocure.models.CompatibilitySecondResultModel;
import com.astrocure.utils.AppConstants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompatibilitySecondResultActivity extends AppCompatActivity {
    ActivityCompatibilitySecondResultBinding binding;
    List<CompatibilitySecondResultModel> resultModelList;
    boolean one, two, three, four = false;
    String[] first = {"Euphoria", "Spicy", "Erotic"};
    String[] second = {"Unreal", "Exceptional", "Mythological"};
    String[] third = {"Magical", "Rare", "Benevolent"};
    String[] fourth = {"Respectable", "Romantic", "Pure"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompatibilitySecondResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(getApplicationContext()).load(AppConstants.profileImg).into(binding.userImg);
        Glide.with(getApplicationContext()).load("https://cdn4.sharechat.com/WhatsAppprofiledpboys_d7f9b06_1658641555734_sc_cmprsd_75.jpg?tenant=sc&referrer=pwa-sharechat-service&f=rsd_75.jpg").centerCrop().into(binding.partnerImg);

        binding.slider.setCurrentItem(0);
        setAnimation(1);
        binding.one.setOnClickListener(v -> {
            binding.slider.setCurrentItem(0);
            setAnimation(1);
        });
        binding.two.setOnClickListener(v -> {
            binding.slider.setCurrentItem(1);
            setAnimation(2);
        });
        binding.three.setOnClickListener(v -> {
            binding.slider.setCurrentItem(2);
            setAnimation(3);
        });
        binding.four.setOnClickListener(v -> {
            binding.slider.setCurrentItem(3);
            setAnimation(4);
        });

        resultModelList = new ArrayList<>();
        resultModelList.add(new CompatibilitySecondResultModel("In a depression", "Here is my example for colouring just the first part of a TextView text", ""));
        resultModelList.add(new CompatibilitySecondResultModel("In a relationship", "Here is my example for colouring just the first part of a TextView text", ""));
        resultModelList.add(new CompatibilitySecondResultModel("In a compatibility", "Here is my example for colouring just the first part of a TextView text", ""));
        resultModelList.add(new CompatibilitySecondResultModel("In a friend zone", "Here is my example for colouring just the first part of a TextView text", ""));
        CompatibilityViewpagerAdapter adapter = new CompatibilityViewpagerAdapter(getApplicationContext(), resultModelList);
        binding.slider.setClipToPadding(false);
        binding.slider.setPadding(25, 0, 25, 0);
        binding.slider.setPageMargin(50);
        binding.slider.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.slider);
    }

    private void setAnimation(int btnNum) {
        Animation animSelect = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.compatibility_btn_anim);
        Animation animDeSelect = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.compatibility_btn_rev_anim);
        switch (btnNum) {
            case 1:
                binding.one.startAnimation(animSelect);
                binding.imgOne.startAnimation(animSelect);
                binding.selectedText.setText(randomString(first));
                Glide.with(getApplicationContext()).load(R.drawable.ic_compatibility_four).into(binding.selectedImg);
                one = true;
                if (two) {
                    binding.two.startAnimation(animDeSelect);
                    binding.two.startAnimation(animDeSelect);
                    two = false;
                } else if (three) {
                    binding.three.startAnimation(animDeSelect);
                    binding.imgThree.startAnimation(animDeSelect);
                    three = false;
                } else if (four) {
                    binding.four.startAnimation(animDeSelect);
                    binding.imgFour.startAnimation(animDeSelect);
                    four = false;
                }
                break;
            case 2:
                binding.two.startAnimation(animSelect);
                binding.imgTwo.startAnimation(animSelect);
                binding.selectedText.setText(randomString(second));
                Glide.with(getApplicationContext()).load(R.drawable.ic_compatibility_heart).into(binding.selectedImg);
                two = true;
                if (one) {
                    binding.one.startAnimation(animDeSelect);
                    binding.imgOne.startAnimation(animDeSelect);
                    one = false;
                } else if (three) {
                    binding.three.startAnimation(animDeSelect);
                    binding.imgThree.startAnimation(animDeSelect);
                    three = false;
                } else if (four) {
                    binding.four.startAnimation(animDeSelect);
                    binding.imgFour.startAnimation(animDeSelect);
                    four = false;
                }
                break;
            case 3:
                binding.three.startAnimation(animSelect);
                binding.imgThree.startAnimation(animSelect);
                binding.selectedText.setText(randomString(third));
                Glide.with(getApplicationContext()).load(R.drawable.ic_compatibility_two).into(binding.selectedImg);
                three = true;
                if (one) {
                    binding.one.startAnimation(animDeSelect);
                    binding.imgOne.startAnimation(animDeSelect);
                    one = false;
                } else if (two) {
                    binding.two.startAnimation(animDeSelect);
                    binding.imgTwo.startAnimation(animDeSelect);
                    two = false;
                } else if (four) {
                    binding.four.startAnimation(animDeSelect);
                    binding.imgFour.startAnimation(animDeSelect);
                    four = false;
                }
                break;
            case 4:
                binding.four.startAnimation(animSelect);
                binding.imgFour.startAnimation(animSelect);
                binding.selectedText.setText(randomString(fourth));
                Glide.with(getApplicationContext()).load(R.drawable.ic_compatibility_three).into(binding.selectedImg);
                four = true;
                if (one) {
                    binding.one.startAnimation(animDeSelect);
                    binding.imgOne.startAnimation(animDeSelect);
                    one = false;
                } else if (two) {
                    binding.two.startAnimation(animDeSelect);
                    binding.imgTwo.startAnimation(animDeSelect);
                    two = false;
                } else if (three) {
                    binding.three.startAnimation(animDeSelect);
                    binding.imgThree.startAnimation(animDeSelect);
                    three = false;
                }
                break;
        }
    }

    public String randomString(String[] data) {
        Random random = new Random();
        int index = random.nextInt(data.length);
        return data[index];
    }

}