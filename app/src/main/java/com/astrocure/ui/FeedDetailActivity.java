package com.astrocure.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.FeedCommentAdapter;
import com.astrocure.databinding.ActivityFeedDetailBinding;

public class FeedDetailActivity extends AppCompatActivity {
    ActivityFeedDetailBinding binding;
    FeedCommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
        adapter = new FeedCommentAdapter(getApplicationContext());
        binding.commentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.commentList.setAdapter(adapter);
        binding.post.moreOption.setOnClickListener(v -> {
            Dialog dialog = new Dialog(FeedDetailActivity.this);
            dialog.setContentView(R.layout.dialog_more_option);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.show();
        });
    }
}