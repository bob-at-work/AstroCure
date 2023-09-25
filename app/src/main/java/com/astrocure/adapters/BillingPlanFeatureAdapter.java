package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFeaturePlanDarkBinding;
import com.astrocure.models.PlanModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class BillingPlanFeatureAdapter extends RecyclerView.Adapter<BillingPlanFeatureAdapter.FeatureViewHolder> {
    Context context;
    List<PlanModel.FeaturesName> featuresNameList;

    public BillingPlanFeatureAdapter(Context context, List<PlanModel.FeaturesName> featuresNameList) {
        this.context = context;
        this.featuresNameList = featuresNameList;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeaturePlanDarkBinding binding = ItemFeaturePlanDarkBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FeatureViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        Glide.with(context).load(featuresNameList.get(position).getFeatureImg()).into(holder.binding.imageIc);
        holder.binding.featureName.setText(featuresNameList.get(position).getFeatureName());
    }

    @Override
    public int getItemCount() {
        return featuresNameList.size();
    }

    public class FeatureViewHolder extends RecyclerView.ViewHolder {
        ItemFeaturePlanDarkBinding binding;

        public FeatureViewHolder(ItemFeaturePlanDarkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
