package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.R;
import com.astrocure.databinding.ItemVideoHighlightLayoutBinding;
import com.astrocure.models.StoryModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    Context context;
    List<StoryModel> storyList;

    public StoryAdapter(Context context, List<StoryModel> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoHighlightLayoutBinding binding = ItemVideoHighlightLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new StoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        if (position == 0) {
            holder.binding.imageView8.setBackground(context.getDrawable(R.drawable.video_highlight_bg_active));
        }
        Glide.with(context).load(storyList.get(position).getImage())
                .into(holder.binding.logo);
        holder.binding.storyCatName.setText(storyList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        ItemVideoHighlightLayoutBinding binding;

        public StoryViewHolder(ItemVideoHighlightLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
