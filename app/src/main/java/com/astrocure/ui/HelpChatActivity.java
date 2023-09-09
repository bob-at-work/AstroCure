package com.astrocure.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.HelpChatAdapter;
import com.astrocure.databinding.ActivityHelpChatBinding;
import com.astrocure.databinding.ItemFullscreenImageViewBinding;
import com.astrocure.models.HelpChatModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HelpChatActivity extends AppCompatActivity {

    ActivityHelpChatBinding binding;
    ActivityResultLauncher<Intent> launcher;
    List<HelpChatModel> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        chatList = new ArrayList<>();
        chatList.add(new HelpChatModel("How to do XYZ in the Application", false, null, "05:49 pm"));
        chatList.add(new HelpChatModel("Welcome to AstroCure HELP CENTRE", true, null, "05:49 pm"));
        chatList.add(new HelpChatModel("We will give our best to solve the XYZ", true, null, "05:49 pm"));
        chatList.add(new HelpChatModel(null, true, "https://wmstatic.global.ssl.fastly.net/ml/7271022-f-60ba0110-3053-41ec-94f5-14d67e1c9685.png?width=422&height=916", "05:49 pm"));
        chatList.add(new HelpChatModel("Please refer to the above image", true, null, "05:49 pm"));
        chatList.add(new HelpChatModel("Hold on, Let me check first", false, null, "05:49 pm"));
        chatList.add(new HelpChatModel(null, false, "https://wmstatic.global.ssl.fastly.net/ml/7271022-f-60ba0110-3053-41ec-94f5-14d67e1c9685.png?width=422&height=916", "05:49 pm"));
        chatList.add(new HelpChatModel("The XYZ still remains, above is the screenshot", false, null, "05:49 pm"));

        HelpChatAdapter adapter = new HelpChatAdapter(getApplicationContext(), chatList);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.chatList.smoothScrollToPosition(chatList.size());
        adapter.setOnClick((position, imageUrl) -> {
            Dialog dialog = new Dialog(HelpChatActivity.this, R.style.Theme_AstroCure);
            ItemFullscreenImageViewBinding imageViewBinding = ItemFullscreenImageViewBinding.inflate(getLayoutInflater());
            dialog.setContentView(imageViewBinding.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 0, 0, 0)));
            Glide.with(getApplicationContext()).load(imageUrl).into(imageViewBinding.image);
            dialog.setCancelable(false);
            imageViewBinding.close.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        });

        binding.attachment.setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            launcher.launch(Intent.createChooser(intent, "Select Picture"));
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        binding.send.setOnClickListener(v -> {
            if (!binding.msg.getText().toString().isEmpty()) {
                chatList.add(new HelpChatModel(binding.msg.getText().toString(), false, null, "6:28 pm"));
                adapter.notifyDataSetChanged();
                binding.chatList.smoothScrollToPosition(chatList.size());
                binding.msg.setText("");
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    try {
                        if (result.getData() != null) {
                            chatList.add(new HelpChatModel(null, false, result.getData().getData().toString(), "06:34 pm"));
                            adapter.notifyDataSetChanged();
                            binding.chatList.smoothScrollToPosition(chatList.size());
//                            Glide.with(requireContext()).load(result.getData().getData())
//                                    .into(binding.profileImg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("TAG", "onActivityResult: ", e);
                    }
                } else {
                    Toast.makeText(HelpChatActivity.this, "Unsupported Format", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}