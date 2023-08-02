package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemCompatibilityFirstBinding;
import com.astrocure.databinding.ItemCompatibilitySecondBinding;

public class CompatibilityAdapter extends RecyclerView.Adapter {
    Context context;
    private static final int VIEW_TYPE_PADDING = 1;
    private static final int VIEW_TYPE_ITEM = 2;
    private int paddingWidthDate = 0;

    private int selectedItem = -1;

    public CompatibilityAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_ITEM) {
            ItemCompatibilityFirstBinding binding = ItemCompatibilityFirstBinding.inflate(LayoutInflater.from(context), parent, false);
            return new ZodiacViewHolder(binding);
//        }else {
//            ItemCompatibilitySecondBinding binding = ItemCompatibilitySecondBinding.inflate(LayoutInflater.from(context), parent, false);
//            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) binding.getRoot().getLayoutParams();
//            layoutParams.width = paddingWidthDate;
//            binding.getRoot().setLayoutParams(layoutParams);
//            return new ZodiacTextViewHolder(binding);
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 12*2+1;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    public class ZodiacViewHolder extends RecyclerView.ViewHolder {
        ItemCompatibilityFirstBinding binding;
        public ZodiacViewHolder(ItemCompatibilityFirstBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public class ZodiacTextViewHolder extends RecyclerView.ViewHolder {
        ItemCompatibilitySecondBinding binding;
        public ZodiacTextViewHolder(ItemCompatibilitySecondBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
