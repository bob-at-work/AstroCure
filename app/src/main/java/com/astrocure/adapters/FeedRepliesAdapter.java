package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFeedRepliesBinding;
import com.bumptech.glide.Glide;

public class FeedRepliesAdapter extends RecyclerView.Adapter<FeedRepliesAdapter.RepliesViewHolder> {
    Context context;
    private ItemViewHeight itemViewHeight;

    public FeedRepliesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RepliesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedRepliesBinding binding = ItemFeedRepliesBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RepliesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepliesViewHolder holder, int position) {
        holder.binding.getRoot().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        itemViewHeight.getItemHeight(holder.binding.getRoot().getMeasuredHeight());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class RepliesViewHolder extends RecyclerView.ViewHolder {
        ItemFeedRepliesBinding binding;

        public RepliesViewHolder(ItemFeedRepliesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOnHeight(ItemViewHeight itemViewHeight) {
        this.itemViewHeight = itemViewHeight;
    }

    public interface ItemViewHeight {
        void getItemHeight(int height);
    }
}
