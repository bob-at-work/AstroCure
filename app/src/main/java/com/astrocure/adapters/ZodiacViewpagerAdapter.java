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
import com.astrocure.models.ZodiacViewPagerModel;

import java.util.List;

public class ZodiacViewpagerAdapter extends PagerAdapter {
    Context context;
    List<ZodiacViewPagerModel> models;

    public ZodiacViewpagerAdapter(Context context/*, List<ZodiacViewPagerModel> models*/) {
        this.context = context;
//        this.models = models;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_zodiac_percent_layout,container,false);
        TextView textView = view.findViewById(R.id.day_indicator);
        switch (position){
            case 0:
                textView.setText("Horoscope For Yesterday");
                break;
            case 1:
                textView.setText("Horoscope For Today");
                break;
            case 2:
                textView.setText("Horoscope For Tomorrow");
                break;
            default:
                textView.setText("Horoscope");
        }
        container.addView(view,0);
        return view;
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
