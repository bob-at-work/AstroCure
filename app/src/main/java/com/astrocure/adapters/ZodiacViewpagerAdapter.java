package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.astrocure.R;
import com.astrocure.databinding.ItemZodiacPercentLayoutBinding;
import com.astrocure.models.ZodiacViewPagerModel;

import java.util.List;

public class ZodiacViewpagerAdapter extends PagerAdapter {
    Context context;
    ZodiacViewPagerModel model;

    public ZodiacViewpagerAdapter(Context context, ZodiacViewPagerModel model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemZodiacPercentLayoutBinding binding = ItemZodiacPercentLayoutBinding.inflate(LayoutInflater.from(context),container,false);
        switch (position){
            case 0:
                binding.dayIndicator.setText("Horoscope For Yesterday");
                binding.zodiacName.setText(model.getZodiacName());
                break;
            case 1:
                binding.dayIndicator.setText("Horoscope For Today");
                binding.zodiacName.setText(model.getZodiacName());
                break;
            case 2:
                binding.dayIndicator.setText("Horoscope For Tomorrow");
                binding.zodiacName.setText(model.getZodiacName());
                break;
            default:
                binding.dayIndicator.setText("Horoscope");
        }
        container.addView(binding.getRoot(),0);
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.yesterday);
            case 1:
                return context.getString(R.string.today);
            case 2:
                return context.getString(R.string.tomorrow);
            default:
                return null;
        }
    }
}
