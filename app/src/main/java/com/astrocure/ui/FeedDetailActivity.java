package com.astrocure.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.FeedCommentAdapter;
import com.astrocure.databinding.ActivityFeedDetailBinding;
import com.astrocure.databinding.DialogBottomCommentMoreBinding;
import com.astrocure.databinding.DialogCommentDeleteConfirmBinding;
import com.astrocure.databinding.DialogCommentMoreConfirmBinding;
import com.astrocure.databinding.DialogCommentReplyBinding;
import com.astrocure.databinding.DialogMoreOptionBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;

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
                .load(imageUrl).centerCrop().into(binding.image);

        adapter = new FeedCommentAdapter(getApplicationContext());
        binding.commentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.commentList.setAdapter(adapter);
        adapter.setOnClick(new FeedCommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DialogCommentReplyBinding replyBinding = DialogCommentReplyBinding.inflate(getLayoutInflater());
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(FeedDetailActivity.this, R.style.BottomSheetDialog);
                bottomSheetDialog.setContentView(replyBinding.getRoot());
                replyBinding.send.setOnClickListener(v -> {
                    Toast.makeText(FeedDetailActivity.this, "Comment Posted", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });
                bottomSheetDialog.show();
            }

            @Override
            public void onItemMoreOption(int position, String comment) {
                DialogBottomCommentMoreBinding bottomBinding = DialogBottomCommentMoreBinding.inflate(getLayoutInflater());
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(FeedDetailActivity.this, R.style.BottomSheetDialog);
                bottomSheetDialog.setContentView(bottomBinding.getRoot());
                bottomBinding.editComment.setOnClickListener(v -> {
                    DialogCommentReplyBinding replyBinding = DialogCommentReplyBinding.inflate(getLayoutInflater());
                    BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(FeedDetailActivity.this, R.style.BottomSheetDialog);
                    bottomSheetDialog1.setContentView(replyBinding.getRoot());
                    replyBinding.textComment.setText(comment);
                    bottomSheetDialog1.show();
                    bottomSheetDialog.cancel();
                });
                bottomBinding.deleteComment.setOnClickListener(v -> {
                    DialogCommentDeleteConfirmBinding deleteBinding = DialogCommentDeleteConfirmBinding.inflate(getLayoutInflater());
                    Dialog dialog = new Dialog(FeedDetailActivity.this, R.style.Theme_AstroCure);
//                    Bitmap bitmap = AppConstantMethods.takeScreenShot(FeedDetailActivity.this);
//                    Bitmap fast = AppConstantMethods.fastBlur(bitmap, 5);
//                    final Drawable draw = new BitmapDrawable(getResources(), fast);

                    dialog.setContentView(deleteBinding.getRoot());
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
                    dialog.setCancelable(true);
                    deleteBinding.confirmed.setOnClickListener(v13 -> {
                        Toast.makeText(FeedDetailActivity.this, "We will delete in future, keep Calm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        bottomSheetDialog.dismiss();
                    });
                    deleteBinding.notSelected.setOnClickListener(v14 -> {
                        dialog.dismiss();
//                        bottomSheetDialog.dismiss();
                    });
                    deleteBinding.getRoot().setOnClickListener(v19 -> {
                        dialog.cancel();
                    });
                    deleteBinding.container.setOnClickListener(v110 -> {
                    });
                    dialog.show();
                });
                bottomBinding.reportComment.setOnClickListener(v -> {
                    DialogCommentMoreConfirmBinding moreConfirmBinding = DialogCommentMoreConfirmBinding.inflate(getLayoutInflater());
                    Dialog confirmDialog = new Dialog(FeedDetailActivity.this, R.style.Theme_AstroCure);
                    confirmDialog.setContentView(moreConfirmBinding.getRoot());
                    confirmDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    confirmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
                    confirmDialog.setCancelable(true);
                    moreConfirmBinding.confirmed.setOnClickListener(v1 -> {
                        Toast.makeText(FeedDetailActivity.this, "Successfully Reported", Toast.LENGTH_SHORT).show();
                        confirmDialog.dismiss();
//                        dialog.dismiss();
                        bottomSheetDialog.dismiss();
                    });
                    moreConfirmBinding.notSelected.setOnClickListener(v12 -> confirmDialog.cancel());
                    moreConfirmBinding.getRoot().setOnClickListener(v115 -> {
                        confirmDialog.cancel();
                    });
                    moreConfirmBinding.container.setOnClickListener(v116 -> {
                    });
                    confirmDialog.show();
                });
                bottomSheetDialog.show();
            }
        });

        binding.moreOption.setOnClickListener(v -> {
            DialogMoreOptionBinding moreOptionBinding = DialogMoreOptionBinding.inflate(getLayoutInflater());
            Dialog dialog = new Dialog(FeedDetailActivity.this, R.style.Theme_AstroCure);
            dialog.setContentView(moreOptionBinding.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
            dialog.setCancelable(true);
            moreOptionBinding.getRoot().setOnClickListener(v15 -> {
                dialog.cancel();
            });
            moreOptionBinding.container.setOnClickListener(v16 -> {
            });
            moreOptionBinding.edit.setOnClickListener(v17 -> {
                Intent intent = new Intent(getApplicationContext(), AddPostActivity.class);
                intent.putExtra("feed_content", binding.content.getText().toString());
                startActivity(intent);
                dialog.cancel();
            });
            moreOptionBinding.delete.setOnClickListener(v18 -> {
                DialogCommentDeleteConfirmBinding confirmBinding = DialogCommentDeleteConfirmBinding.inflate(getLayoutInflater());
                Dialog deleteDialog = new Dialog(FeedDetailActivity.this, R.style.Theme_AstroCure);
                deleteDialog.setContentView(confirmBinding.getRoot());
                deleteDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
                deleteDialog.setCancelable(true);
                confirmBinding.textView24.setText("Are you sure you want to delete your POST?");
                confirmBinding.container.setOnClickListener(v111 -> {
                });
                confirmBinding.getRoot().setOnClickListener(v112 -> deleteDialog.cancel());
                confirmBinding.confirmed.setOnClickListener(v113 -> {
                    deleteDialog.dismiss();
                });
                confirmBinding.notSelected.setOnClickListener(v114 -> {
                    deleteDialog.cancel();
                });
                deleteDialog.show();
                dialog.cancel();
            });
            dialog.show();
        });

        binding.share.setOnClickListener(v -> {
            Bitmap bitmap = ((BitmapDrawable) binding.image.getDrawable()).getBitmap();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
            Uri bmpUri = Uri.parse(path);
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, binding.content.getText().toString());
            shareIntent.setType("image/png");
            startActivity(Intent.createChooser(shareIntent, "Share with"));
        });

        binding.commentContainer.setOnClickListener(v -> {
            DialogCommentReplyBinding replyBinding = DialogCommentReplyBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(FeedDetailActivity.this, R.style.BottomSheetDialog);
            bottomSheetDialog.setContentView(replyBinding.getRoot());
            replyBinding.send.setOnClickListener(v33 -> {
                Toast.makeText(getApplicationContext(), "Comment Posted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.show();
        });

    }
}