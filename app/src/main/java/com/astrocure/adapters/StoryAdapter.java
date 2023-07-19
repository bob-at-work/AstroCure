package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemVideoHighlightLayoutBinding;
import com.bumptech.glide.Glide;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    Context context;

    public StoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoHighlightLayoutBinding binding = ItemVideoHighlightLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new StoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Glide.with(context).load("https://media.istockphoto.com/id/913219882/photo/financial-graph-on-technology-abstract-background.jpg?s=612x612&w=0&k=20&c=0P0vbPiPsHOH_uzZEzL6CmpZwIDIArtNj_PsQVwxkEM=")
                .into(holder.binding.logo);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        ItemVideoHighlightLayoutBinding binding;
        public StoryViewHolder(ItemVideoHighlightLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
