package com.astrocure.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFeedPostLayoutBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class FeedImageAdapter extends RecyclerView.Adapter<FeedImageAdapter.FeedImageViewHolder> {

    Context context;
    List<Uri> list;
    OnItemClick onItemClick;

    public FeedImageAdapter(Context context, List<Uri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeedImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedPostLayoutBinding binding = ItemFeedPostLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FeedImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedImageViewHolder holder, int position) {
        Uri uri = list.get(position);
        Glide.with(context).load(uri).into(holder.binding.image);
        int active = holder.getAdapterPosition();
        holder.binding.remove.setOnClickListener(view -> onItemClick.getPosition(active));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int position);
    }

    public class FeedImageViewHolder extends RecyclerView.ViewHolder {
        ItemFeedPostLayoutBinding binding;

        public FeedImageViewHolder(ItemFeedPostLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
