package com.astrocure.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemChatAstrologerBinding;
import com.astrocure.databinding.ItemChatUserBinding;
import com.astrocure.databinding.ItemQNAAstrologerBinding;
import com.astrocure.databinding.ItemQNAStaticBinding;
import com.astrocure.databinding.ItemQNAUserImageBinding;
import com.astrocure.models.QuestionAnswerChatModel;
import com.astrocure.ui.AstrologerChatActivity;

import java.util.List;

public class QuestionAnswerAdapter extends RecyclerView.Adapter {
    Context context;
    List<QuestionAnswerChatModel> models;
    private final static int STATIC_VIEW = 0;
    private final static int ADMIN_TEXT = 1;
    private final static int USER_TEXT = 2;
    private final static int ASTROLOGER_CONTACT = 3;
    private final static int ADMIN_IMAGE = 4;
    private final static int USER_IMAGE = 5;

    public QuestionAnswerAdapter(Context context, List<QuestionAnswerChatModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatUserBinding userBinding = ItemChatUserBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemChatAstrologerBinding adminBinding = ItemChatAstrologerBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemQNAAstrologerBinding astrologerBinding = ItemQNAAstrologerBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemQNAStaticBinding staticBinding = ItemQNAStaticBinding.inflate(LayoutInflater.from(context), parent, false);
        ItemQNAUserImageBinding userImageBinding = ItemQNAUserImageBinding.inflate(LayoutInflater.from(context), parent, false);
        switch (viewType) {
            case USER_TEXT:
                return new UserViewHolder(userBinding);
            case ADMIN_TEXT:
                return new AdminViewHolder(adminBinding);
            case ASTROLOGER_CONTACT:
                return new AstrologerViewHolder(astrologerBinding);
            case STATIC_VIEW:
                return new StaticViewHolder(staticBinding);
            case USER_IMAGE:
                return new UImageViewHolder(userImageBinding);
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
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });

        } else if (model.getSentBy().matches("app")) {
            StaticViewHolder staticViewHolder = (StaticViewHolder) holder;
            staticViewHolder.binding.text.setText("Feel Free to ask anything to personalized therapist");
        } /*else if (model.isImage()) {
            UImageViewHolder uImageViewHolder = (UImageViewHolder) holder;
            Glide.with(context).load(model.getUri()).into(uImageViewHolder.binding.messageImage);
            uImageViewHolder.binding.time.setText(model.getTime());
        }*/ else {
            AdminViewHolder adminViewHolder = (AdminViewHolder) holder;
            adminViewHolder.binding.message.setText(model.getMessage());
            adminViewHolder.binding.time.setText(model.getTime());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (models.get(position).getSentBy().equals("admin")) {
            if (!models.get(position).isLink()) {
                return ADMIN_TEXT;
            } else {
                return ASTROLOGER_CONTACT;
            }
        } else if (models.get(position).getSentBy().equals("app")) {
            return STATIC_VIEW;
        } else if (models.get(position).isImage()) {
            return USER_IMAGE;
        }
        return USER_TEXT;
//        return position % 2 * 2;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ItemChatUserBinding binding;

        public UserViewHolder(ItemChatUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        ItemChatAstrologerBinding binding;

        public AdminViewHolder(ItemChatAstrologerBinding binding) {
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

    public class StaticViewHolder extends RecyclerView.ViewHolder {
        ItemQNAStaticBinding binding;

        public StaticViewHolder(ItemQNAStaticBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class UImageViewHolder extends RecyclerView.ViewHolder {
        ItemQNAUserImageBinding binding;

        public UImageViewHolder(ItemQNAUserImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
