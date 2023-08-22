package com.astrocure.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.astrocure.databinding.ItemFeedCommentBinding;
import com.astrocure.utils.DrawDotedLine;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FeedCommentAdapter extends RecyclerView.Adapter<FeedCommentAdapter.CommentViewHolder> {
    Context context;
    private OnItemClickListener onItemClickListener;
    private List<Integer> heights;

    public FeedCommentAdapter(Context context) {
        this.context = context;
        this.heights = new ArrayList<>();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedCommentBinding binding = ItemFeedCommentBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Glide.with(context).load("https://d2v5dzhdg4zhx3.cloudfront.net/web-assets/images/storypages/short/linkedin-profile-picture-maker/dummy_image/thumb/004.webp")
                .centerCrop()
                .into(holder.binding.logo);
        holder.binding.reply.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position);
        });
        holder.binding.commentCount.setOnClickListener(v -> {
            if (holder.binding.replyList.getVisibility() == View.GONE) {
                AtomicInteger itemHeight = new AtomicInteger();
                TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
                holder.binding.replyList.setVisibility(View.VISIBLE);
                holder.binding.threadView.setVisibility(View.VISIBLE);
                FeedReplyAdapter adapter = new FeedReplyAdapter(context);
                holder.binding.replyList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                holder.binding.replyList.setAdapter(adapter);
                adapter.setOnHeight((height,heights) -> {
                    itemHeight.set(height);
                    DrawDotedLine drawDotedLine = new DrawDotedLine(context, holder.binding.logo.getHeight(),holder.binding.threadView.getWidth(), heights);
                    holder.binding.threadView.addView(drawDotedLine);
                });

                adapter.setOnClick(position1 -> {
                    onItemClickListener.onItemClick(position);
                });
            } else {
                holder.binding.threadView.setVisibility(View.GONE);
                holder.binding.replyList.setVisibility(View.GONE);
//                TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
            }
        });
        holder.binding.moreOption.setOnClickListener(v1 -> {
            onItemClickListener.onItemMoreOption(position,holder.binding.comment.getText().toString());
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void setOnClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemMoreOption(int position,String comment);
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        ItemFeedCommentBinding binding;

        public CommentViewHolder(ItemFeedCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
