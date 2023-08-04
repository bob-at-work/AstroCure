package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemZodiacLayoutBinding;
import com.astrocure.models.HomeZodiacModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeZodiacAdapter extends RecyclerView.Adapter<HomeZodiacAdapter.ZodiacViewHolder> {
    Context context;
    List<HomeZodiacModel> zodiacModels;

    public HomeZodiacAdapter(Context context, List<HomeZodiacModel> zodiacModels) {
        this.context = context;
        this.zodiacModels = zodiacModels;
    }

    @NonNull
    @Override
    public ZodiacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemZodiacLayoutBinding binding = ItemZodiacLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ZodiacViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacViewHolder holder, int position) {
        holder.binding.name.setText(zodiacModels.get(position).getName());
        Glide.with(context).load(zodiacModels.get(position).getLogo())
                .into(holder.binding.logo);
    }

    @Override
    public int getItemCount() {
        return zodiacModels.size();
    }

    public class ZodiacViewHolder extends RecyclerView.ViewHolder {
        ItemZodiacLayoutBinding binding;

        public ZodiacViewHolder(ItemZodiacLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
