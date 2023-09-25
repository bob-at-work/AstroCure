package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemPlanLayoutBinding;
import com.astrocure.models.PlanModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlanViewHolder> {
    Context context;
    List<PlanModel> list;
    OnItemClickListener onItemClickListener;

    public PlansAdapter(Context context, List<PlanModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlanLayoutBinding binding = ItemPlanLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PlanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanModel planModel = list.get(position);
        if (planModel.getPlanName().matches("Elite")) {
            holder.binding.tagContainer.setVisibility(View.VISIBLE);
        }
        holder.binding.title.setText(planModel.getPlanName());
        if (planModel.isLimited()) {
            holder.binding.tag.setVisibility(View.VISIBLE);
        }
        Glide.with(context).load(planModel.getPlanImage()).into(holder.binding.planImg);
        holder.binding.activePrice.setText("₹" + planModel.getActivePrice() + "/");
        holder.binding.actualPrice.setText("₹" + planModel.getActualPrice() + "/mo");
        PlanFeatureAdapter adapter = new PlanFeatureAdapter(context, planModel.getFeaturesName());
        holder.binding.featureList.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        holder.binding.featureList.setAdapter(adapter);

        holder.binding.featureList.setOnClickListener(v -> onItemClickListener.onItemClick(position, planModel));
        holder.binding.itemClick.setOnClickListener(v -> onItemClickListener.onItemClick(position, planModel));
        holder.binding.getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(position, planModel));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int Position, PlanModel planModel);
    }

    public class PlanViewHolder extends RecyclerView.ViewHolder {
        ItemPlanLayoutBinding binding;

        public PlanViewHolder(ItemPlanLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
