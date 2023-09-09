package com.astrocure.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.FeedsAdapter;
import com.astrocure.databinding.DialogCommentReplyBinding;
import com.astrocure.databinding.DialogMoreOptionBinding;
import com.astrocure.databinding.FragmentFeedsBinding;
import com.astrocure.ui.AddPostActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class FeedsFragment extends Fragment {

    FragmentFeedsBinding binding;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedsBinding.inflate(inflater, container, false);

        binding.newest.setOnClickListener(v -> {
            binding.newest.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_selected_bg));
            binding.newest.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.mostCommented.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostCommented.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
            binding.mostLiked.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostLiked.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
        });
        binding.mostCommented.setOnClickListener(v -> {
            binding.mostCommented.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_selected_bg));
            binding.mostCommented.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.newest.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.newest.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
            binding.mostLiked.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostLiked.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
        });
        binding.mostLiked.setOnClickListener(v -> {
            binding.mostLiked.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_selected_bg));
            binding.mostLiked.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.newest.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.newest.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
            binding.mostCommented.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.mostCommented.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.feed_unselected_bg));
        });

        FeedsAdapter adapter = new FeedsAdapter(getContext());
        binding.feedList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.feedList.setAdapter(adapter);
        binding.addPost.setOnClickListener(v -> startActivity(new Intent(getContext(), AddPostActivity.class)));

        adapter.setOnClick(new FeedsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Dialog dialog = new Dialog(requireContext(), R.style.Theme_AstroCure);
                DialogMoreOptionBinding moreOptionBinding = DialogMoreOptionBinding.inflate(inflater, container, false);
                dialog.setContentView(moreOptionBinding.getRoot());
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 255, 255, 255)));
//            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                moreOptionBinding.delete.setOnClickListener(v -> dialog.dismiss());
                moreOptionBinding.getRoot().setOnClickListener(v -> dialog.cancel());
                dialog.setCancelable(true);
                dialog.show();
            }

            @Override
            public void onCommentItemClick(int position) {
                DialogCommentReplyBinding replyBinding = DialogCommentReplyBinding.inflate(getLayoutInflater());
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialog);
                bottomSheetDialog.setContentView(replyBinding.getRoot());
                replyBinding.send.setOnClickListener(v -> {
                    Toast.makeText(requireContext(), "Comment Posted", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });
                bottomSheetDialog.show();
            }
        });

        return binding.getRoot();
    }
}