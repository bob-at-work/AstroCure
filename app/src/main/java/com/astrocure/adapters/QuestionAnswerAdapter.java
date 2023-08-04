package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemQNAAdminBinding;
import com.astrocure.databinding.ItemQNAAstrologerBinding;
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
        ItemQNAUserBinding userBinding = ItemQNAUserBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemQNAAdminBinding adminBinding = ItemQNAAdminBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemQNAAstrologerBinding astrologerBinding = ItemQNAAstrologerBinding.inflate(LayoutInflater.from(context), parent, false);
        switch (viewType) {
            case 0:
                return new UserViewHolder(userBinding);
            case 1:
                return new AdminViewHolder(adminBinding);
            case 2:
                return new AstrologerViewHolder(astrologerBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionAnswerChatModel model = models.get(position);
        if (model.getSentBy().matches("user")) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.binding.message.setText(model.getMessage());
            userViewHolder.binding.time.setText(model.getTime());
        } else if (model.isLink()) {
            AstrologerViewHolder astrologerViewHolder = (AstrologerViewHolder) holder;
            astrologerViewHolder.binding.call.setOnClickListener(v -> Toast.makeText(context, "Unavailable, Please try after some time.", Toast.LENGTH_SHORT).show());
            astrologerViewHolder.binding.chat.setOnClickListener(v -> {
                Intent intent = new Intent(context, AstrologerChatActivity.class);
                context.startActivity(intent);
            });

        } else {
            AdminViewHolder adminViewHolder = (AdminViewHolder) holder;
            adminViewHolder.binding.message.setText(model.getMessage());
            adminViewHolder.binding.time.setText(model.getTime());
//            adminViewHolder.binding.message.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//            adminViewHolder.binding.getRoot().setOnClickListener(v -> {
//                Intent intent = new Intent(context, AstrologerChatActivity.class);
//                context.startActivity(intent);
//            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (models.get(position).getSentBy().equals("admin")) {
            if (!models.get(position).isLink()) {
                return 1;
            } else {
                return 2;
            }
        }
        return 0;
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

    public class AstrologerViewHolder extends RecyclerView.ViewHolder {
        ItemQNAAstrologerBinding binding;

        public AstrologerViewHolder(ItemQNAAstrologerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
