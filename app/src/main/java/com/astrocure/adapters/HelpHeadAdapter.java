package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemHelpHeadBinding;

public class HelpHeadAdapter extends RecyclerView.Adapter<HelpHeadAdapter.HelpHeadViewHolder> {
    Context context;

    public HelpHeadAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HelpHeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHelpHeadBinding binding = ItemHelpHeadBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HelpHeadViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpHeadViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class HelpHeadViewHolder extends RecyclerView.ViewHolder {
        ItemHelpHeadBinding binding;
        public HelpHeadViewHolder(ItemHelpHeadBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
