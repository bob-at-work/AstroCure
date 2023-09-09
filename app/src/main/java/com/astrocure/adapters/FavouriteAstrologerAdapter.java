package com.astrocure.adapters;

import static com.astrocure.utils.AppConstants.profileImg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFavouriteAstrologerBinding;
import com.astrocure.ui.AstrologerChatActivity;
import com.astrocure.ui.AstrologerProfileActivity;
import com.bumptech.glide.Glide;

public class FavouriteAstrologerAdapter extends RecyclerView.Adapter<FavouriteAstrologerAdapter.FavouriteViewHolder> {
    Context context;

    public FavouriteAstrologerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavouriteAstrologerBinding binding = ItemFavouriteAstrologerBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FavouriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        Glide.with(context).load(profileImg).into(holder.binding.profileImg);
        if (position == 1) {
            holder.binding.action.setVisibility(View.VISIBLE);
            holder.binding.offer.setVisibility(View.VISIBLE);
        }
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, AstrologerProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        holder.binding.action.setOnClickListener(v -> {
            Intent intent = new Intent(context, AstrologerChatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        ItemFavouriteAstrologerBinding binding;

        public FavouriteViewHolder(ItemFavouriteAstrologerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
