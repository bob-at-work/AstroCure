package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemPublicFigureBinding;
import com.astrocure.databinding.ItemPublicFigureSeeMoreBinding;

public class TrendingPublicFigureAdapter extends RecyclerView.Adapter {
    private static final int ITEM = 0;
    private static final int SEE_MORE = 1;
    Context context;
    int size = 11;

    public TrendingPublicFigureAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPublicFigureBinding publicFigureBinding = ItemPublicFigureBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemPublicFigureSeeMoreBinding seeMoreBinding = ItemPublicFigureSeeMoreBinding.inflate(LayoutInflater.from(context), parent, false);
        switch (viewType) {
            case ITEM:
                return new TrendingViewHolder(publicFigureBinding);
            case SEE_MORE:
                return new SeeMoreViewHolder(seeMoreBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (size == position + 1) {
            return SEE_MORE;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {
        ItemPublicFigureBinding binding;

        public TrendingViewHolder(ItemPublicFigureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class SeeMoreViewHolder extends RecyclerView.ViewHolder {
        ItemPublicFigureSeeMoreBinding binding;

        public SeeMoreViewHolder(ItemPublicFigureSeeMoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
