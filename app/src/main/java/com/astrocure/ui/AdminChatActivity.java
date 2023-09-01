package com.astrocure.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.QuestionAnswerAdapter;
import com.astrocure.databinding.ActivityAdminChatBinding;
import com.astrocure.models.QuestionAnswerChatModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminChatActivity extends AppCompatActivity {

    ActivityAdminChatBinding binding;
    QuestionAnswerAdapter adapter;
    List<QuestionAnswerChatModel> modelList;
    ActivityResultLauncher<Intent> launcher;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        modelList = new ArrayList<>();
        modelList.add(new QuestionAnswerChatModel("", "app", "", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Apple", "user", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("generally yes, because different view types can correspond to completely different view layouts. ViewHolder is a common design pattern in android described", "admin", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("That's my point since only one ViewHolder is available in one RecyclerView.Adapter", "user", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Connect to the astrologer", "admin", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Astrologer", "admin", "12:06pm", true, false, null));
        modelList.add(new QuestionAnswerChatModel("Hello", "user", "12:06pm", false, false, null));
        modelList.add(new QuestionAnswerChatModel("Hello", "admin", "12:06pm", false, false, null));
        adapter = new QuestionAnswerAdapter(getApplicationContext(), modelList);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.chatList.smoothScrollToPosition(binding.chatList.getAdapter().getItemCount() - 1);
        binding.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.message.getText().length() != 0) {
                    binding.voiceRecord.setVisibility(View.GONE);
                    binding.camera.setVisibility(View.GONE);
                    binding.send.setVisibility(View.VISIBLE);
                } else {
                    binding.voiceRecord.setVisibility(View.VISIBLE);
                    binding.camera.setVisibility(View.VISIBLE);
                    binding.send.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.send.setOnClickListener(v -> {
            String currentTime = new SimpleDateFormat("HH:mma", Locale.getDefault()).format(new Date());
            modelList.add(new QuestionAnswerChatModel(binding.message.getText().toString(), "user", currentTime, false, false, null));
            adapter.notifyDataSetChanged();
            binding.chatList.smoothScrollToPosition(binding.chatList.getAdapter().getItemCount() - 1);
            binding.message.setText("");
        });

        binding.camera.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(Intent.createChooser(intent, "Select Picture"));
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                try {
                    if (result.getData() != null) {
                        String currentTime = new SimpleDateFormat("HH:mma", Locale.getDefault()).format(new Date());
//                        modelList.add(new QuestionAnswerChatModel("","user",currentTime,false,true,result.getData().getData()));
//                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}