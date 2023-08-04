package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemTransactionLayoutBinding;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.TransactionViewHolder> {
    Context context;

    public TransactionHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTransactionLayoutBinding binding = ItemTransactionLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        ItemTransactionLayoutBinding binding;

        public TransactionViewHolder(ItemTransactionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
