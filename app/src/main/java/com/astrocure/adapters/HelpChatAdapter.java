package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemHelpChatAdminBinding;
import com.astrocure.databinding.ItemHelpChatUserBinding;

public class HelpChatAdapter extends RecyclerView.Adapter {
    Context context;
    private static int USER = 1;
    private static int ADMIN = 2;

    public HelpChatAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHelpChatUserBinding userBinding = ItemHelpChatUserBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemHelpChatAdminBinding adminBinding = ItemHelpChatAdminBinding.inflate(LayoutInflater.from(context), parent, false);
        if (viewType == USER) {
            return new UserViewHolder(userBinding);
        } else {
            return new AdminViewHolder(adminBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (position % 2 == 0) {
            return USER;
        } else {
            return ADMIN;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ItemHelpChatUserBinding binding;

        public UserViewHolder(ItemHelpChatUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        ItemHelpChatAdminBinding binding;

        public AdminViewHolder(ItemHelpChatAdminBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
