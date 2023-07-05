package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFeedCommentBinding;

public class FeedCommentAdapter extends RecyclerView.Adapter<FeedCommentAdapter.CommentViewHolder> {
    Context context;

    public FeedCommentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedCommentBinding binding = ItemFeedCommentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        ItemFeedCommentBinding binding;
        public CommentViewHolder(ItemFeedCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
