package com.astrocure.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.R;
import com.astrocure.databinding.ItemFeedTextLayoutBinding;
import com.astrocure.ui.FeedDetailActivity;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedViewHolder> {
    Context context;

    public FeedsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FeedsAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedTextLayoutBinding binding = ItemFeedTextLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.FeedViewHolder holder, int position) {

        holder.binding.comment.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedDetailActivity.class);
            context.startActivity(intent);
        });

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        holder.binding.moreOption.setOnClickListener(v -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_more_option);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.show();
        });
        holder.binding.share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Users Post");
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(intent, "Share Using"));
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemFeedTextLayoutBinding binding;
        public FeedViewHolder(ItemFeedTextLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
