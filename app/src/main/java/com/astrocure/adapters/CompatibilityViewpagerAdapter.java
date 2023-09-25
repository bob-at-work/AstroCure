package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.astrocure.databinding.ItemCompatibilityResultBinding;
import com.astrocure.models.CompatibilitySecondResultModel;

import java.util.List;

public class CompatibilityViewpagerAdapter extends PagerAdapter {
    Context context;
    List<CompatibilitySecondResultModel> modelList;

    public CompatibilityViewpagerAdapter(Context context, List<CompatibilitySecondResultModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemCompatibilityResultBinding binding = ItemCompatibilityResultBinding.inflate(LayoutInflater.from(context), container, false);
        binding.title.setText(modelList.get(position).getTitle());
        binding.description.setText(modelList.get(position).getDescription());

        container.addView(binding.getRoot(), 0);
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return modelList.size();
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
