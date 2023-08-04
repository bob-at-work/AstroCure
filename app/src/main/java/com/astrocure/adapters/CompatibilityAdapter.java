package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemCompatibilityFirstBinding;
import com.astrocure.models.CompatibilityZodiacModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class CompatibilityAdapter extends RecyclerView.Adapter<CompatibilityAdapter.ZodiacViewHolder> {
    Context context;
    List<CompatibilityZodiacModel> list;

    public CompatibilityAdapter(Context context, List<CompatibilityZodiacModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ZodiacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompatibilityFirstBinding binding = ItemCompatibilityFirstBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ZodiacViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacViewHolder holder, int position) {
        Glide.with(context).load(list.get(position % list.size()).getZodiacImg()).into(holder.binding.zodiacImage);
        holder.binding.zodacName.setText(list.get(position % list.size()).getZodiacName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() * 2;
    }

    public class ZodiacViewHolder extends RecyclerView.ViewHolder {
        ItemCompatibilityFirstBinding binding;

        public ZodiacViewHolder(ItemCompatibilityFirstBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    /*public class ZodiacTextViewHolder extends RecyclerView.ViewHolder {
        ItemCompatibilitySecondBinding binding;

        public ZodiacTextViewHolder(ItemCompatibilitySecondBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }*/
}
