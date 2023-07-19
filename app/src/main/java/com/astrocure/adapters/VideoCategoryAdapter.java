package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemVideoCategoryBinding;

import java.util.List;

public class VideoCategoryAdapter extends RecyclerView.Adapter<VideoCategoryAdapter.CategoryViewHolder> {
    Context context;
    List<String> list;

    public VideoCategoryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoCategoryBinding binding = ItemVideoCategoryBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.binding.category.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemVideoCategoryBinding binding;
        public CategoryViewHolder(ItemVideoCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
