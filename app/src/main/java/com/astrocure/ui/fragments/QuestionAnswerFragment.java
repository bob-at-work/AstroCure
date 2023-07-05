package com.astrocure.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.QuestionAnswerAdapter;
import com.astrocure.databinding.DialogQuestionAnswerBinding;
import com.astrocure.databinding.FragmentQuestionAnswerBinding;
import com.astrocure.models.QuestionAnswerChatModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerFragment extends Fragment {

    FragmentQuestionAnswerBinding binding;
    QuestionAnswerAdapter adapter;
    List<QuestionAnswerChatModel> modelList;

    public QuestionAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuestionAnswerBinding.inflate(inflater, container, false);

        DialogQuestionAnswerBinding dialogBinding = DialogQuestionAnswerBinding.inflate(inflater, container, false);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
        dialogBinding.close.setOnClickListener(v -> dialog.dismiss());
        binding.msgInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int stringSize = binding.msgInput.getText().toString().length();
                binding.inputSize.setText(stringSize + "/470");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.send.setOnClickListener(v -> {
            if (binding.msgInput.getText().toString().isEmpty()) {
                binding.msgInput.setError("Empty");
            } else {
                binding.askContainer.setVisibility(View.GONE);
                binding.send.setVisibility(View.GONE);
                binding.chatContainer.setVisibility(View.VISIBLE);
            }
        });

        modelList = new ArrayList<>();
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm",false));
        modelList.add(new QuestionAnswerChatModel("Apple", "user", "12:06pm",false));
        modelList.add(new QuestionAnswerChatModel("generally yes, because different view types can correspond to completely different view layouts. ViewHolder is a common design pattern in android described", "admin", "12:06pm",false));
        modelList.add(new QuestionAnswerChatModel("That's my point since only one ViewHolder is available in one RecyclerView.Adapter", "user", "12:06pm",false));
        modelList.add(new QuestionAnswerChatModel("Connect to the astrologer", "admin", "12:06pm",false));
        modelList.add(new QuestionAnswerChatModel("Astrologer", "admin", "12:06pm",true));
        modelList.add(new QuestionAnswerChatModel("Hello", "user", "12:06pm",false));
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm",false));
        adapter = new QuestionAnswerAdapter(getContext(), modelList);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);

        binding.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.message.getText().length() != 0) {
                    binding.voiceRecord.setVisibility(View.GONE);
                    binding.message.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.send,0);
                } else {
                    binding.voiceRecord.setVisibility(View.VISIBLE);
                    binding.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fluent_camera,0, 0,0);
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

            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (binding.message.getRight() - binding.message.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    modelList.add(new QuestionAnswerChatModel(binding.message.getText().toString(),"user","12:49pm"));
                    return true;
                }
            }
            return false;
        });*/

        return binding.getRoot();
    }
}