package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemFeedRepliesBinding;
import com.astrocure.models.CommentModel;

import java.util.ArrayList;
import java.util.List;

public class FeedRepliesAdapter extends RecyclerView.Adapter<FeedRepliesAdapter.RepliesViewHolder> {
    Context context;
    private ItemViewHeight itemViewHeight;
    private Integer maxHeight = 0;
    private List<Integer> heights;
    private List<CommentModel> list;

    public FeedRepliesAdapter(Context context, List<CommentModel> list) {
        this.context = context;
        heights = new ArrayList<>();
        this.list = list;
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
        holder.binding.userName.setText(list.get(position).getName());
        holder.binding.reply.setText(list.get(position).getMessage());
        holder.binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            if (holder.binding.getRoot().getHeight() != 0) {
                maxHeight = holder.binding.getRoot().getHeight();
                heights.add(maxHeight);
            }
        });


        itemViewHeight.getItemHeight(holder.binding.reply.getMeasuredHeight(), heights);
    }

    @Override
    public int getItemCount() {
        return list.size();
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
        void getItemHeight(int height, List<Integer> heights);
    }
}
