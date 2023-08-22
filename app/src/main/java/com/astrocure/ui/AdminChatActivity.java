package com.astrocure.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.QuestionAnswerAdapter;
import com.astrocure.databinding.ActivityAdminChatBinding;
import com.astrocure.models.QuestionAnswerChatModel;

import java.util.ArrayList;
import java.util.List;

public class AdminChatActivity extends AppCompatActivity {

    ActivityAdminChatBinding binding;
    QuestionAnswerAdapter adapter;
    List<QuestionAnswerChatModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        modelList = new ArrayList<>();
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm", false));
        modelList.add(new QuestionAnswerChatModel("Apple", "user", "12:06pm", false));
        modelList.add(new QuestionAnswerChatModel("generally yes, because different view types can correspond to completely different view layouts. ViewHolder is a common design pattern in android described", "admin", "12:06pm", false));
        modelList.add(new QuestionAnswerChatModel("That's my point since only one ViewHolder is available in one RecyclerView.Adapter", "user", "12:06pm", false));
        modelList.add(new QuestionAnswerChatModel("Connect to the astrologer", "admin", "12:06pm", false));
        modelList.add(new QuestionAnswerChatModel("Astrologer", "admin", "12:06pm", true));
        modelList.add(new QuestionAnswerChatModel("Hello", "user", "12:06pm", false));
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm", false));
        adapter = new QuestionAnswerAdapter(getApplicationContext(), modelList);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.message.getText().length() != 0) {
                    binding.voiceRecord.setVisibility(View.GONE);
                    binding.message.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.send, 0);
                } else {
                    binding.voiceRecord.setVisibility(View.VISIBLE);
                    binding.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fluent_camera, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*binding.message.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            final int DRAWABLE_BOTTOM = 3;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.message.getRight() - binding.message.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
//                    modelList.add(new QuestionAnswerChatModel(binding.message.getText().toString(),"user","12:49pm"));
                    return true;
                }
            }
            return false;
        });*/


    }
}