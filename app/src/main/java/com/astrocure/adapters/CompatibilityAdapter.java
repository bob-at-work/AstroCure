package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemCompatibilityFirstBinding;

public class CompatibilityAdapter extends RecyclerView.Adapter<CompatibilityAdapter.ZodiacViewHolder> {
    Context context;

    public CompatibilityAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ZodiacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompatibilityFirstBinding binding = ItemCompatibilityFirstBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ZodiacViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 12*2+1;
    }

    public class ZodiacViewHolder extends RecyclerView.ViewHolder {
        ItemCompatibilityFirstBinding binding;
        public ZodiacViewHolder(ItemCompatibilityFirstBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
