package com.astrocure.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.FeedCommentAdapter;
import com.astrocure.databinding.ActivityFeedDetailBinding;
import com.astrocure.databinding.DialogCommentReplyBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FeedDetailActivity extends AppCompatActivity {
    ActivityFeedDetailBinding binding;
    FeedCommentAdapter adapter;
    String imageUrl = "https://www.shutterstock.com/shutterstock/photos/1924800845/display_1500/stock-photo-business-and-finance-concept-on-the-table-are-a-notebook-a-pen-documents-and-a-folder-with-the-1924800845.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Glide.with(getApplicationContext())
//                .load(getIntent().getStringExtra("image"))
                .load(imageUrl)
                .centerCrop()
                .into(binding.image);

        adapter = new FeedCommentAdapter(getApplicationContext());
        binding.commentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.commentList.setAdapter(adapter);
        adapter.setOnClick(position -> {
            DialogCommentReplyBinding replyBinding = DialogCommentReplyBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(FeedDetailActivity.this);
            bottomSheetDialog.setContentView(replyBinding.getRoot());
            bottomSheetDialog.show();
        });

        binding.moreOption.setOnClickListener(v -> {
            Dialog dialog = new Dialog(FeedDetailActivity.this);
            dialog.setContentView(R.layout.dialog_more_option);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.show();
        });

        binding.share.setOnClickListener(v -> {
            Bitmap bitmap = ((BitmapDrawable)binding.image.getDrawable()).getBitmap();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
            Uri bmpUri = Uri.parse(path);
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT,binding.content.getText().toString());
            shareIntent.setType("image/png");
            startActivity(Intent.createChooser(shareIntent,"Share with"));
        });

    }
}