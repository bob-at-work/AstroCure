package com.astrocure.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemVideoContentBinding;
import com.bumptech.glide.Glide;

public class VideoContentAdapter extends RecyclerView.Adapter<VideoContentAdapter.VideoViewHolder> {
    Context context;
    String videoUrl = "https://player.vimeo.com/external/291047369.sd.mp4?s=2f1d1301e3fa84499e1e7b17b0cf92d20850400a&profile_id=164&oauth2_token_id=57447761";
    String videoUrl1 = "https://player.vimeo.com/external/412230837.sd.mp4?s=2759dbac227dc9a62851271c056b2fb8f2400aec&profile_id=165&oauth2_token_id=57447761";
    String videoUrl2 = "https://player.vimeo.com/external/263604204.sd.mp4?s=8c253c3b3603e70a627c3d8e208b2fe63564e0ec&profile_id=164&oauth2_token_id=57447761";
    String videoUrl3 = "https://player.vimeo.com/external/511524889.sd.mp4?s=150cec1df5d8eab068351fac399956a751692a8a&profile_id=165&oauth2_token_id=57447761";
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
                .into(holder.binding.thumbnail);
        Uri uri = Uri.parse(videoUrl2);
        holder.binding.video.setVideoURI(uri);
        MediaController mediaController = new MediaController(context);
//        mediaController.setAnchorView(holder.binding.video);
//        mediaController.setMediaPlayer(holder.binding.video);
//        holder.binding.video.setMediaController(mediaController);
        holder.binding.video.setOnClickListener(v -> {
            holder.binding.thumbnail.setVisibility(View.GONE);
            holder.binding.video.start();
        });
        if (!holder.binding.video.isPlaying()){
            holder.binding.thumbnail.setVisibility(View.VISIBLE
            );
        }


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ItemVideoContentBinding binding;
        public VideoViewHolder(ItemVideoContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
