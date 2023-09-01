package com.astrocure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.HelpChatAdapter;
import com.astrocure.databinding.ActivityHelpChatBinding;

public class HelpChatActivity extends AppCompatActivity {

    ActivityHelpChatBinding binding;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        HelpChatAdapter adapter = new HelpChatAdapter(getApplicationContext());
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);

        binding.attachment.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(Intent.createChooser(intent, "Select Picture"));
        });

        binding.send.setOnClickListener(v -> {
            binding.msg.setText("");
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    try {
                        if (result.getData() != null) {
//                            Glide.with(requireContext()).load(result.getData().getData())
//                                    .into(binding.profileImg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}