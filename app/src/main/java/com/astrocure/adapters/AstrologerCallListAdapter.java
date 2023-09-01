package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemAstrologerListBinding;
import com.astrocure.ui.AstrologerProfileActivity;

public class AstrologerCallListAdapter extends RecyclerView.Adapter<AstrologerCallListAdapter.AstrologerViewHolder> {
    Context context;

    public AstrologerCallListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AstrologerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAstrologerListBinding binding = ItemAstrologerListBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AstrologerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AstrologerViewHolder holder, int position) {
        holder.binding.btnAction.setText("Call");
//        holder.binding.btnAction.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call_btn,0,0,0);
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, AstrologerProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class AstrologerViewHolder extends RecyclerView.ViewHolder {
        ItemAstrologerListBinding binding;

        public AstrologerViewHolder(ItemAstrologerListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
