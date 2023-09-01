package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.R;
import com.astrocure.databinding.ItemVideoMiscFilterBinding;

import java.util.List;

public class FilterCheckAdapter extends RecyclerView.Adapter<FilterCheckAdapter.CheckViewHolder> {
    Context context;
    List<String> listItem;

    public FilterCheckAdapter(Context context, List<String> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoMiscFilterBinding binding = ItemVideoMiscFilterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CheckViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckViewHolder holder, int position) {
        holder.binding.item.setText(listItem.get(position));
        holder.binding.item.setOnClickListener(v -> {
            if (holder.binding.item.isChecked()) {
                holder.binding.item.setChecked(false);
                holder.binding.item.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_box_unchecked, 0, 0, 0);
            } else {
                holder.binding.item.setChecked(true);
                holder.binding.item.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_box_checked, 0, 0, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class CheckViewHolder extends RecyclerView.ViewHolder {
        ItemVideoMiscFilterBinding binding;

        public CheckViewHolder(ItemVideoMiscFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
