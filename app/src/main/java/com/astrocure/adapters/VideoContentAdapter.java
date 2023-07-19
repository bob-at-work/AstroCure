package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemVideoContentBinding;
import com.bumptech.glide.Glide;

public class VideoContentAdapter extends RecyclerView.Adapter<VideoContentAdapter.VideoViewHolder> {
    Context context;

    public VideoContentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoContentBinding binding = ItemVideoContentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Glide.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTZdaieBWs1FmM6BNf5x8bJojQ04hKM6WwhM_6C4fSyPZ9ZEyO2bNH6SkT-s_49p7WWsE&usqp=CAU")
                .centerCrop()
                .into(holder.binding.profileImg);
        Glide.with(context).load("https://cdn.britannica.com/18/216518-050-7EA8BFA5/traders-professionals-opening-bell-New-York-Stock-Exchange-NYSE-January-2-2019.jpg")
                .into(holder.binding.videoView);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ItemVideoContentBinding binding;
        public VideoViewHolder(ItemVideoContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
