package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFeedTextLayoutBinding;
import com.astrocure.ui.FeedDetailActivity;
import com.bumptech.glide.Glide;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedViewHolder> {
    Context context;
    OnItemClickListener onItemClickListener;

    public FeedsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FeedsAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedTextLayoutBinding binding = ItemFeedTextLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.FeedViewHolder holder, int position) {

        holder.binding.image.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedDetailActivity.class);
            intent.putExtra("image", "https://images.unsplash.com/photo-1590283603385-17ffb3a7f29f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80");
            context.startActivity(intent);
        });
        holder.binding.content.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedDetailActivity.class);
            intent.putExtra("image", "https://images.unsplash.com/photo-1590283603385-17ffb3a7f29f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80");
            context.startActivity(intent);
        });
        holder.binding.comment.setOnClickListener(v -> {
            onItemClickListener.onCommentItemClick(position);
        });

        Glide.with(context).load("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80")
                .centerCrop()
                .into(holder.binding.profile);
        Glide.with(context).load("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80")
                .centerCrop()
                .into(holder.binding.profileImage);

        Glide.with(context)
                .load("https://images.unsplash.com/photo-1590283603385-17ffb3a7f29f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80")
                .centerCrop()
                .into(holder.binding.image);

//        holder.binding.getRoot().setOnClickListener(v -> {
//            Intent intent = new Intent(context, FeedDetailActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        });
        holder.binding.moreOption.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position);
        });
        holder.binding.share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Users Post");
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(intent, "Share Using"));
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public void setOnClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onCommentItemClick(int position);
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemFeedTextLayoutBinding binding;

        public FeedViewHolder(ItemFeedTextLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
