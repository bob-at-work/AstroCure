package com.astrocure.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.R;
import com.astrocure.databinding.ItemChatAstrologerBinding;
import com.astrocure.databinding.ItemChatUserBinding;
import com.astrocure.databinding.ItemReceiveAudioBinding;
import com.astrocure.databinding.ItemReceiveImageBinding;
import com.astrocure.databinding.ItemSendAudioBinding;
import com.astrocure.databinding.ItemSendImageBinding;
import com.astrocure.models.AstrologerChatModel;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AstrologerChatAdapter extends RecyclerView.Adapter {
    private final static int USER = 0;
    private final static int ASTROLOGER = 1;
    private final static int USER_AUDIO = 2;
    private final static int ASTROLOGER_AUDIO = 3;
    private final static int USER_IMAGE = 4;
    private final static int ASTROLOGER_IMAGE = 5;
    Context context;
    List<AstrologerChatModel> list;
    MediaPlayer mediaPlayer;
    private OnItemClickListener onItemClickListener;

    public AstrologerChatAdapter(Context context, List<AstrologerChatModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatUserBinding userBinding = ItemChatUserBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemChatAstrologerBinding astrologerBinding = ItemChatAstrologerBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemSendAudioBinding userAudioBinding = ItemSendAudioBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemReceiveAudioBinding astrologerAudioBinding = ItemReceiveAudioBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemSendImageBinding sendImageBinding = ItemSendImageBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemReceiveImageBinding receiveImageBinding = ItemReceiveImageBinding.inflate(LayoutInflater.from(context), parent, false);
        switch (viewType) {
            case USER:
                return new UserChatViewHolder(userBinding);
            case ASTROLOGER:
                return new AstrologerViewHolder(astrologerBinding);
            case USER_AUDIO:
                return new AudioSendViewHolder(userAudioBinding);
            case ASTROLOGER_AUDIO:
                return new AudioReceiveViewHolder(astrologerAudioBinding);
            case USER_IMAGE:
                return new ImageSendViewHolder(sendImageBinding);
            case ASTROLOGER_IMAGE:
                return new ImageReceiveViewHolder(receiveImageBinding);
            default:
                return null;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AstrologerChatModel model = list.get(position);
        if (!model.getAdmin()) {
            if (model.getAudioFile() != null) {
                AudioSendViewHolder viewHolder = (AudioSendViewHolder) holder;
                Uri uri = Uri.parse(model.getAudioFile());
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(context, uri);
                String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                int millSecond = Integer.parseInt(durationStr);
                @SuppressLint("DefaultLocale") String hour = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millSecond));
                @SuppressLint("DefaultLocale") String duration = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millSecond) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millSecond)), TimeUnit.MILLISECONDS.toSeconds(millSecond) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millSecond)));
                viewHolder.binding.audioTime.setText(" / " + (hour.matches("00") ? "" : hour + ":") + duration);
                viewHolder.binding.actionAudio.setOnClickListener(v -> {
                    if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
                        playAudio(model.getAudioFile());
                        viewHolder.binding.actionAudio.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_video_pause));
                    } else {
                        pauseAudio();
                        viewHolder.binding.actionAudio.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_video_play));
                    }
                });
                viewHolder.binding.time.setText(model.getTime());
            } else if (model.getImageUrl() != null) {
                ImageSendViewHolder viewHolder = (ImageSendViewHolder) holder;
                viewHolder.binding.time.setText(model.getTime());
                Glide.with(context).load(model.getImageUrl()).centerCrop().into(viewHolder.binding.image);
                viewHolder.binding.image.setOnClickListener(v -> onItemClickListener.onImageItemClick(position, model.getImageUrl()));
            } else {
                UserChatViewHolder viewHolder = (UserChatViewHolder) holder;
                viewHolder.binding.message.setText(model.getMessage());
                viewHolder.binding.time.setText(model.getTime());
            }
        } else {
            if (model.getAudioFile() != null) {
                AudioReceiveViewHolder viewHolder = (AudioReceiveViewHolder) holder;
                Uri uri = Uri.parse(model.getAudioFile());
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(context, uri);
                String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                int millSecond = Integer.parseInt(durationStr);
                @SuppressLint("DefaultLocale") String hour = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millSecond));
                @SuppressLint("DefaultLocale") String duration = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millSecond) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millSecond)), TimeUnit.MILLISECONDS.toSeconds(millSecond) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millSecond)));
                viewHolder.binding.audioTime.setText(" / " + (hour.matches("00") ? "" : hour + ":") + duration);
                viewHolder.binding.actionAudio.setOnClickListener(v -> {

                });
                viewHolder.binding.time.setText(model.getTime());
            } else if (model.getImageUrl() != null) {
                ImageReceiveViewHolder viewHolder = (ImageReceiveViewHolder) holder;
                viewHolder.binding.time.setText(model.getTime());
                Glide.with(context).load(model.getImageUrl()).centerCrop().into(viewHolder.binding.image);
                viewHolder.binding.image.setOnClickListener(v -> onItemClickListener.onImageItemClick(position, model.getImageUrl()));
            } else {
                AstrologerViewHolder viewHolder = (AstrologerViewHolder) holder;
                viewHolder.binding.message.setText(model.getMessage());
                viewHolder.binding.time.setText(model.getTime());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!list.get(position).getAdmin()) {
            if (list.get(position).getAudioFile() != null) {
                return USER_AUDIO;
            }
            if (list.get(position).getImageUrl() != null) {
                return USER_IMAGE;
            } else {
                return USER;
            }
        } else {
            if (list.get(position).getAudioFile() != null) {
                return ASTROLOGER_AUDIO;
            } else if (list.get(position).getImageUrl() != null) {
                return ASTROLOGER_IMAGE;
            } else {
                return ASTROLOGER;
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void playAudio(String fileName) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void pauseAudio() {
        mediaPlayer.pause();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void setOnClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onImageItemClick(int position, String imageUrl);
    }

    public static class UserChatViewHolder extends RecyclerView.ViewHolder {
        ItemChatUserBinding binding;

        public UserChatViewHolder(ItemChatUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class AstrologerViewHolder extends RecyclerView.ViewHolder {
        ItemChatAstrologerBinding binding;

        public AstrologerViewHolder(ItemChatAstrologerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class AudioSendViewHolder extends RecyclerView.ViewHolder {
        ItemSendAudioBinding binding;

        public AudioSendViewHolder(ItemSendAudioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class AudioReceiveViewHolder extends RecyclerView.ViewHolder {
        ItemReceiveAudioBinding binding;

        public AudioReceiveViewHolder(ItemReceiveAudioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ImageSendViewHolder extends RecyclerView.ViewHolder {
        ItemSendImageBinding binding;

        public ImageSendViewHolder(ItemSendImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ImageReceiveViewHolder extends RecyclerView.ViewHolder {
        ItemReceiveImageBinding binding;

        public ImageReceiveViewHolder(ItemReceiveImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
