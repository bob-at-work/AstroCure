package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.astrocure.databinding.ItemOnboardingViewPagerBinding;
import com.astrocure.models.OnboardingSwipeModel;

import java.util.List;

public class OnboardingViewpagerAdapter extends PagerAdapter {
    Context context;
    List<OnboardingSwipeModel> swipeModels;

    public OnboardingViewpagerAdapter(Context context, List<OnboardingSwipeModel> swipeModels) {
        this.context = context;
        this.swipeModels = swipeModels;
    }

    @Override
    public int getCount() {
        return swipeModels.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemOnboardingViewPagerBinding binding = ItemOnboardingViewPagerBinding.inflate(LayoutInflater.from(context), container, false);
        binding.anim.setAnimation(swipeModels.get(position).getRawAnim());
        binding.title.setText(swipeModels.get(position).getTitle());
        binding.content.setText(swipeModels.get(position).getContent());
        container.addView(binding.getRoot(), 0);
        return binding.getRoot();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
