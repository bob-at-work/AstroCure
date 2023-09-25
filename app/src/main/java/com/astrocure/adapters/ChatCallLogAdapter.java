package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemContactLogHistoryBinding;
import com.astrocure.ui.AstrologerChatActivity;
import com.astrocure.utils.AppConstants;
import com.bumptech.glide.Glide;

public class ChatCallLogAdapter extends RecyclerView.Adapter<ChatCallLogAdapter.LogViewHolder> {
    Context context;

    public ChatCallLogAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactLogHistoryBinding binding = ItemContactLogHistoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new LogViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, AstrologerChatActivity.class);
            intent.putExtra("previous", "ChatCallLogAdapter");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        Glide.with(context).load(AppConstants.profileImg).into(holder.binding.profileImage);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class LogViewHolder extends RecyclerView.ViewHolder {
        ItemContactLogHistoryBinding binding;

        public LogViewHolder(ItemContactLogHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
