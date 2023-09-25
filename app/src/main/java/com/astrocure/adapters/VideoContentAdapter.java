package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.R;
import com.astrocure.databinding.ItemVideoContentBinding;
import com.astrocure.databinding.ItemVideoTextContentBinding;
import com.astrocure.models.VideoContentModel;
import com.astrocure.ui.VideoViewActivity;
import com.astrocure.utils.AppConstantMethods;
import com.bumptech.glide.Glide;

import java.util.List;

public class VideoContentAdapter extends RecyclerView.Adapter {
    Context context;
    List<VideoContentModel> list;

    public VideoContentAdapter(Context context, List<VideoContentModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoContentBinding videoContentBinding = ItemVideoContentBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemVideoTextContentBinding textContentBinding = ItemVideoTextContentBinding.inflate(LayoutInflater.from(context), parent, false);
        if (viewType == 0) {
            return new TextViewHolder(textContentBinding);
        }
        return new VideoViewHolder(videoContentBinding);
    }


    @Override
    public int getItemViewType(int position) {
        if (!list.get(position).isVideo()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoContentModel model = list.get(position);
        if (!list.get(position).isVideo()) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            Glide.with(context).load(model.getProfileImgUrl())
                    .centerCrop()
                    .into(textViewHolder.binding.profileImg);
            Glide.with(context).load(model.getImageUrl())
                    .into(textViewHolder.binding.thumbnail);
            String hashtag = AppConstantMethods.getColoredSpanned("#stock #gold", "#0EE3BC");
            textViewHolder.binding.textContent.append(" \n");
            textViewHolder.binding.textContent.append(Html.fromHtml(hashtag));
        } else {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            Glide.with(context).load(model.getProfileImgUrl())
                    .centerCrop()
                    .into(videoViewHolder.binding.profileImg);
            Glide.with(context).load(model.getThumbnail())
                    .into(videoViewHolder.binding.thumbnail);
//            Uri uri = Uri.parse(videoUrl);
//            videoViewHolder.binding.video.setVideoURI(uri);
//            MediaController mediaController = new MediaController(context);
//        mediaController.setAnchorView(holder.binding.video);
//        mediaController.setMediaPlayer(holder.binding.video);
//        holder.binding.video.setMediaController(mediaController);
//            videoViewHolder.binding.video.setOnClickListener(v -> {
//            holder.binding.thumbnail.setVisibility(View.GONE);
//            holder.binding.video.start();
//            });
            String hashtag = AppConstantMethods.getColoredSpanned("#stock #gold", "#0EE3BC");
            videoViewHolder.binding.title.append(" ");
            videoViewHolder.binding.title.append(Html.fromHtml(hashtag));
            videoViewHolder.binding.favourite.setOnClickListener(v -> {
                videoViewHolder.binding.favourite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fav_star_filled));
            });
            videoViewHolder.binding.thumbnail.setOnClickListener(v -> {
                Intent intent = new Intent(context, VideoViewActivity.class);
                intent.putExtra("videoUrl", model.getVideoUrl());
                context.startActivity(intent);
            });
            if (!videoViewHolder.binding.video.isPlaying()) {
                videoViewHolder.binding.thumbnail.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ItemVideoContentBinding binding;

        public VideoViewHolder(ItemVideoContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {

        ItemVideoTextContentBinding binding;

        public TextViewHolder(ItemVideoTextContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
