package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.astrocure.R;
import com.astrocure.databinding.ItemCallChatFilterBinding;

import java.util.List;

public class FilterExpertiseAdapter extends RecyclerView.Adapter<FilterExpertiseAdapter.ExpertiseViewHolder> {
    Context context;
    List<String> expertiseList;

    public FilterExpertiseAdapter(Context context, List<String> expertiseList) {
        this.context = context;
        this.expertiseList = expertiseList;
    }

    @NonNull
    @Override
    public ExpertiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCallChatFilterBinding binding = ItemCallChatFilterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ExpertiseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertiseViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && (holder.getLayoutPosition() == 6 || holder.getLayoutPosition() == 13)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
//            p.setFullSpan(true);
        }
        holder.setIsRecyclable(false);
        holder.binding.item.setText(expertiseList.get(position));
        holder.binding.item.setOnClickListener(v -> {
            if (holder.binding.item.isChecked()) {
                holder.binding.item.setChecked(false);
                holder.binding.item.setTextColor(context.getColor(R.color.filter_purple));
                holder.binding.item.setBackgroundColor(context.getColor(R.color.white));
            } else {
                holder.binding.item.setChecked(true);
                holder.binding.item.setTextColor(context.getColor(R.color.white));
                holder.binding.item.setBackgroundColor(context.getColor(R.color.filter_purple));
            }
        });

    }

    @Override
    public int getItemCount() {
        return expertiseList.size();
    }

    public class ExpertiseViewHolder extends RecyclerView.ViewHolder {
        ItemCallChatFilterBinding binding;

        public ExpertiseViewHolder(ItemCallChatFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
