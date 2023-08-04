package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemWalletOfferLayoutBinding;

public class WalletPriceAdapter extends RecyclerView.Adapter<WalletPriceAdapter.PriceViewHolder> {
    Context context;

    public WalletPriceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWalletOfferLayoutBinding binding = ItemWalletOfferLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PriceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class PriceViewHolder extends RecyclerView.ViewHolder {
        ItemWalletOfferLayoutBinding binding;

        public PriceViewHolder(ItemWalletOfferLayoutBinding binding) {
            super(binding.getRoot());
        }
    }
}
