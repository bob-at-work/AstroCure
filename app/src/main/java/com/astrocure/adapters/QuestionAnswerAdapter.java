package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemQNAAdminBinding;
import com.astrocure.databinding.ItemQNAUserBinding;
import com.astrocure.models.QuestionAnswerChatModel;
import com.astrocure.ui.AstrologerChatActivity;

import java.util.List;

public class QuestionAnswerAdapter extends RecyclerView.Adapter {
    Context context;
    List<QuestionAnswerChatModel> models;

    public QuestionAnswerAdapter(Context context, List<QuestionAnswerChatModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQNAUserBinding userBinding = ItemQNAUserBinding.inflate(LayoutInflater.from(context),parent,false);
        ItemQNAAdminBinding adminBinding = ItemQNAAdminBinding.inflate(LayoutInflater.from(context),parent,false);
        switch (viewType) {
            case 0:
                return new UserViewHolder(userBinding);
            case 1:
                return new AdminViewHolder(adminBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionAnswerChatModel model = models.get(position);
        if (model.getSentBy().matches("user")){
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.binding.message.setText(model.getMessage());
            userViewHolder.binding.time.setText(model.getTime());
        }else {
            AdminViewHolder adminViewHolder = (AdminViewHolder) holder;
            adminViewHolder.binding.message.setText(model.getMessage());
            adminViewHolder.binding.time.setText(model.getTime());
            if (model.isLink()){
                adminViewHolder.binding.message.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                adminViewHolder.binding.getRoot().setOnClickListener(v -> {
                    Intent intent = new Intent(context, AstrologerChatActivity.class);
                    context.startActivity(intent);
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (models.get(position).getSentBy()){
            case "admin":
                return 1;
            default:
                return 0;
        }
//        return position % 2 * 2;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ItemQNAUserBinding binding;

        public UserViewHolder(ItemQNAUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        ItemQNAAdminBinding binding;

        public AdminViewHolder(ItemQNAAdminBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
