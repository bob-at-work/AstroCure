package com.astrocure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemChatAstrologerBinding;
import com.astrocure.databinding.ItemChatUserBinding;
import com.astrocure.models.AstrologerChatModel;

import java.util.List;

public class AstrologerChatAdapter extends RecyclerView.Adapter {
    Context context;
    List<AstrologerChatModel> list;

    public AstrologerChatAdapter(Context context, List<AstrologerChatModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatUserBinding userBinding = ItemChatUserBinding.inflate(LayoutInflater.from(context),parent,false);
        ItemChatAstrologerBinding astrologerBinding = ItemChatAstrologerBinding.inflate(LayoutInflater.from(context),parent,false);
        switch (viewType){
            case 1:
                return new AstrologerViewHolder(astrologerBinding);
            default:
                return new UserChatViewHolder(userBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AstrologerChatModel model = list.get(position);
        if (!list.get(position).getAdmin()){
            UserChatViewHolder viewHolder = (UserChatViewHolder) holder;
            viewHolder.binding.message.setText(model.getMessage());
            viewHolder.binding.time.setText(model.getTime());
        }else {
            AstrologerViewHolder viewHolder = (AstrologerViewHolder) holder;
            viewHolder.binding.message.setText(model.getMessage());
            viewHolder.binding.time.setText(model.getTime());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!list.get(position).getAdmin()){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserChatViewHolder extends RecyclerView.ViewHolder {
        ItemChatUserBinding binding;
        public UserChatViewHolder(ItemChatUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class AstrologerViewHolder extends RecyclerView.ViewHolder {
        ItemChatAstrologerBinding binding;
        public AstrologerViewHolder(ItemChatAstrologerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
