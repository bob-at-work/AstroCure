package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemContactHistoryBinding;
import com.astrocure.ui.AstrologerChatActivity;
import com.astrocure.ui.HelpChatActivity;

public class TransactionChatHistoryAdapter extends RecyclerView.Adapter<TransactionChatHistoryAdapter.TransactionViewHolder> {
    Context context;

    public TransactionChatHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactHistoryBinding binding = ItemContactHistoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, AstrologerChatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("previous", "TransactionChatHistoryAdapter");
            context.startActivity(intent);
        });

        holder.binding.help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HelpChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        ItemContactHistoryBinding binding;

        public TransactionViewHolder(ItemContactHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
