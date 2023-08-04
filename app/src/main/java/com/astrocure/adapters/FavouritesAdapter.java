package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFavouriteVideoBinding;
import com.bumptech.glide.Glide;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouriteViewHolder> {

    Context context;

    public FavouritesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavouriteVideoBinding binding = ItemFavouriteVideoBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FavouriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        Glide.with(context).load("https://media.istockphoto.com/id/1402686701/photo/stack-of-shiny-gold-bars-on-financial-gold-price-graph-3d-illustration.jpg?s=612x612&w=0&k=20&c=TRT8QyzcA6donKgERbfDWW_OvQY6-EguGX_C0oHBffk=")
                .centerCrop()
                .into(holder.binding.thumbnail);
        Glide.with(context).load("https://expertphotography.b-cdn.net/wp-content/uploads/2018/10/cool-profile-picture-natural-light.jpg")
                .centerCrop()
                .into(holder.binding.profileImg);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        ItemFavouriteVideoBinding binding;

        public FavouriteViewHolder(ItemFavouriteVideoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
