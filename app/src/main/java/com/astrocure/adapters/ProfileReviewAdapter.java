package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemReviewListBinding;
import com.bumptech.glide.Glide;

public class ProfileReviewAdapter extends RecyclerView.Adapter<ProfileReviewAdapter.ReviewViewHolder> {
    Context context;

    public ProfileReviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewListBinding binding = ItemReviewListBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Glide.with(context).load("https://pbs.twimg.com/profile_images/1485050791488483328/UNJ05AV8_400x400.jpg")
                .centerCrop()
                .into(holder.binding.image);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ItemReviewListBinding binding;

        public ReviewViewHolder(ItemReviewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
