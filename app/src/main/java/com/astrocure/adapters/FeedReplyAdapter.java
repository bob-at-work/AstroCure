package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.astrocure.databinding.ItemFeedReplyBinding;
import com.astrocure.utils.DrawLine;
import com.bumptech.glide.Glide;

import java.util.concurrent.atomic.AtomicInteger;

public class FeedReplyAdapter extends RecyclerView.Adapter<FeedReplyAdapter.ReplyViewHolder> {

    Context context;
    private OnItemClickListener onItemClickListener;
    private ItemViewHeight itemViewHeight;

    public FeedReplyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedReplyBinding binding = ItemFeedReplyBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ReplyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyViewHolder holder, int position) {
        holder.binding.getRoot().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        itemViewHeight.getItemHeight(holder.binding.getRoot().getMeasuredHeight());
        holder.binding.reply.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position);
        });

        Glide.with(context).load("https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=464&q=80")
                .centerCrop()
                .into(holder.binding.logo);

        holder.binding.commentCount.setOnClickListener(v -> {
            if (holder.binding.replyList.getVisibility() == View.GONE) {
                AtomicInteger itemHeight = new AtomicInteger();
                TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
                holder.binding.replyList.setVisibility(View.VISIBLE);
                holder.binding.thread.setVisibility(View.VISIBLE);
                FeedRepliesAdapter adapter = new FeedRepliesAdapter(context);
                holder.binding.replyList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                holder.binding.replyList.setAdapter(adapter);
                adapter.setOnHeight(height -> {
                    itemHeight.set(height);
                    DrawLine drawLine = new DrawLine(context, holder.binding.logo.getHeight(), itemHeight.get());
                    holder.binding.thread.addView(drawLine);
                });
            } else {
                holder.binding.thread.setVisibility(View.GONE);
                holder.binding.replyList.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setOnHeight(ItemViewHeight itemViewHeight) {
        this.itemViewHeight = itemViewHeight;
    }

    public void setOnClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface ItemViewHeight {
        void getItemHeight(int height);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder {
        ItemFeedReplyBinding binding;

        public ReplyViewHolder(ItemFeedReplyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
