package com.astrocure.adapters;

import static com.astrocure.utils.AppConstants.profileImg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemHelpChatAdminBinding;
import com.astrocure.databinding.ItemHelpChatAdminImageBinding;
import com.astrocure.databinding.ItemHelpChatUserBinding;
import com.astrocure.databinding.ItemHelpChatUserImageBinding;
import com.astrocure.models.HelpChatModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class HelpChatAdapter extends RecyclerView.Adapter {
    private static final int USER = 1;
    private static final int ADMIN = 2;
    private static final int USER_IMAGE = 3;
    private static final int ADMIN_IMAGE = 4;
    Context context;
    List<HelpChatModel> chatModels;
    private OnItemClickListener onItemClickListener;

    public HelpChatAdapter(Context context, List<HelpChatModel> chatModels) {
        this.context = context;
        this.chatModels = chatModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHelpChatUserBinding userBinding = ItemHelpChatUserBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemHelpChatAdminBinding adminBinding = ItemHelpChatAdminBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemHelpChatUserImageBinding userImageBinding = ItemHelpChatUserImageBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemHelpChatAdminImageBinding adminImageBinding = ItemHelpChatAdminImageBinding.inflate(LayoutInflater.from(context), parent, false);
        switch (viewType) {
            case USER:
                return new UserViewHolder(userBinding);
            case ADMIN:
                return new AdminViewHolder(adminBinding);
            case USER_IMAGE:
                return new UserImageViewHolder(userImageBinding);
            case ADMIN_IMAGE:
                return new AdminImageViewHolder(adminImageBinding);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (chatModels.get(position).isAdmin()) {
            if (chatModels.get(position).getImageUrl() != null) {
                return ADMIN_IMAGE;
            } else {
                return ADMIN;
            }

        } else {
            if (chatModels.get(position).getImageUrl() != null) {
                return USER_IMAGE;
            } else {
                return USER;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HelpChatModel chatModel = chatModels.get(position);
        if (chatModel.isAdmin()) {
            if (chatModel.getImageUrl() != null) {
                AdminImageViewHolder viewHolder = (AdminImageViewHolder) holder;
                Glide.with(context).load(chatModel.getImageUrl()).centerCrop().into(viewHolder.binding.msgImg);
                viewHolder.binding.time.setText(chatModel.getTime());
                Glide.with(context).load(profileImg).into(viewHolder.binding.image);
                viewHolder.binding.msgImg.setOnClickListener(v -> {
                    onItemClickListener.onImageItemClick(position, chatModel.getImageUrl());
                });
            } else {
                AdminViewHolder viewHolder = (AdminViewHolder) holder;
                viewHolder.binding.time.setText(chatModel.getTime());
                viewHolder.binding.txtMsg.setText(chatModel.getMessage());
                Glide.with(context).load(profileImg).into(viewHolder.binding.image);
            }
        } else {
            if (chatModel.getImageUrl() != null) {
                UserImageViewHolder viewHolder = (UserImageViewHolder) holder;
                Glide.with(context).load(chatModel.getImageUrl()).centerCrop().into(viewHolder.binding.msgImg);
                viewHolder.binding.time.setText(chatModel.getTime());
                viewHolder.binding.msgImg.setOnClickListener(v -> {
                    onItemClickListener.onImageItemClick(position, chatModel.getImageUrl());
                });
            } else {
                UserViewHolder viewHolder = (UserViewHolder) holder;
                viewHolder.binding.time.setText(chatModel.getTime());
                viewHolder.binding.msgTxt.setText(chatModel.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }

    public void setOnClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onImageItemClick(int position, String imageUrl);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        ItemHelpChatUserBinding binding;

        public UserViewHolder(ItemHelpChatUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class UserImageViewHolder extends RecyclerView.ViewHolder {
        ItemHelpChatUserImageBinding binding;

        public UserImageViewHolder(ItemHelpChatUserImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class AdminViewHolder extends RecyclerView.ViewHolder {
        ItemHelpChatAdminBinding binding;

        public AdminViewHolder(ItemHelpChatAdminBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class AdminImageViewHolder extends RecyclerView.ViewHolder {
        ItemHelpChatAdminImageBinding binding;

        public AdminImageViewHolder(ItemHelpChatAdminImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
