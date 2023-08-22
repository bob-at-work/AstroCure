package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemSimilarAstrologerBinding;
import com.bumptech.glide.Glide;

public class SimilarAstrologerListAdapter extends RecyclerView.Adapter<SimilarAstrologerListAdapter.ProfileViewHolder> {

    Context context;

    public SimilarAstrologerListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSimilarAstrologerBinding binding = ItemSimilarAstrologerBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Glide.with(context).load("https://images.unsplash.com/photo-1525134479668-1bee5c7c6845?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxjb2xsZWN0aW9uLXRodW1ibmFpbHx8OTc5MjIwMXx8ZW58MHx8fHx8&auto=format&fit=crop&w=420&q=60")
                .centerCrop()
                .into(holder.binding.profileImage);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        ItemSimilarAstrologerBinding binding;

        public ProfileViewHolder(ItemSimilarAstrologerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
