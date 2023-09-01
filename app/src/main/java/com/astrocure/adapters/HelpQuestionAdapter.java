package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.R;
import com.astrocure.databinding.ItemHelpQuestionAnswerBinding;

public class HelpQuestionAdapter extends RecyclerView.Adapter<HelpQuestionAdapter.AnswerViewHolder> {
    Context context;

    public HelpQuestionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHelpQuestionAnswerBinding binding = ItemHelpQuestionAnswerBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AnswerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        holder.binding.expand.setOnClickListener(v -> {
            if (holder.binding.answers.getVisibility() == View.VISIBLE) {
                holder.binding.answers.setVisibility(View.GONE);
                holder.binding.expand.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_line));
            } else {
                holder.binding.answers.setVisibility(View.VISIBLE);
                holder.binding.expand.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_add));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        ItemHelpQuestionAnswerBinding binding;

        public AnswerViewHolder(ItemHelpQuestionAnswerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
