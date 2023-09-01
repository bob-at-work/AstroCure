package com.astrocure.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.astrocure.databinding.DialogQuestionAnswerBinding;
import com.astrocure.databinding.FragmentQuestionAnswerBinding;
import com.astrocure.ui.AdminChatActivity;
import com.astrocure.ui.AstrologerCallListActivity;
import com.astrocure.ui.AstrologerChatListActivity;

import java.util.Objects;

public class QuestionAnswerFragment extends Fragment {

    FragmentQuestionAnswerBinding binding;

    public QuestionAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuestionAnswerBinding.inflate(inflater, container, false);

        DialogQuestionAnswerBinding dialogBinding = DialogQuestionAnswerBinding.inflate(inflater, container, false);
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(dialogBinding.getRoot());
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                if (stringSize == 0) {
                    binding.send.setVisibility(View.INVISIBLE);
                } else {
                    binding.send.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.send.setOnClickListener(v -> {
            binding.msgInput.setText("");
            startActivity(new Intent(requireContext(), AdminChatActivity.class));
        });

        binding.gotoChat.setOnClickListener(v -> startActivity(new Intent(requireContext(), AstrologerChatListActivity.class)));

        binding.gotoCall.setOnClickListener(v -> startActivity(new Intent(requireContext(), AstrologerCallListActivity.class)));

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