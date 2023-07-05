package com.astrocure.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.AstrologerChatAdapter;
import com.astrocure.databinding.ActivityAstrologerChatBinding;
import com.astrocure.models.AstrologerChatModel;

import java.util.ArrayList;
import java.util.List;

public class AstrologerChatActivity extends AppCompatActivity {
    ActivityAstrologerChatBinding binding;
    AstrologerChatAdapter adapter;
    List<AstrologerChatModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

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

        models = new ArrayList<>();
        models.add(new AstrologerChatModel("Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.","5:45pm",true));
        models.add(new AstrologerChatModel("Tempus nunc amet.","5:45pm",false));
        models.add(new AstrologerChatModel("Tempus nunc amet.","5:45pm",true));
        models.add(new AstrologerChatModel("Tempus nunc amet.","5:45pm",false));
        models.add(new AstrologerChatModel("Tempus nunc amet.Tempus nunc amet.Tempus nunc amet.","5:45pm",true));
        models.add(new AstrologerChatModel("Tempus nunc amet.","5:45pm",false));
        models.add(new AstrologerChatModel("Tempus nunc amet.","5:45pm",true));
        models.add(new AstrologerChatModel("Tempus nunc ametTempus nunc amet..","5:45pm",false));
        adapter = new AstrologerChatAdapter(getApplicationContext(),models);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.chatList.setAdapter(adapter);

    }
}