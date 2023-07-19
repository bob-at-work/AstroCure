package com.astrocure.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.adapters.StoryAdapter;
import com.astrocure.adapters.VideoCategoryAdapter;
import com.astrocure.adapters.VideoContentAdapter;
import com.astrocure.databinding.DialogVideoFilterBinding;
import com.astrocure.databinding.FragmentVideosBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment {

    FragmentVideosBinding binding;
    StoryAdapter adapter;
    VideoContentAdapter contentAdapter;
    List<String> category;

    public VideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVideosBinding.inflate(inflater,container,false);

        adapter = new StoryAdapter(getContext());
        binding.storyList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.storyList.setAdapter(adapter);

        contentAdapter = new VideoContentAdapter(getContext());
        binding.contentList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.contentList.setAdapter(contentAdapter);

        category = new ArrayList<>();
        category.add("Commodities");
        category.add("Sports");
        category.add("Stock");
        category.add("Bollywood");
        category.add("Miscellaneous");
        category.add("Stock");
        category.add("Bollywood");

        binding.filter.setOnClickListener(v -> {
            DialogVideoFilterBinding cardBinding = DialogVideoFilterBinding.inflate(getLayoutInflater());
            BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            dialog.setContentView(cardBinding.getRoot());

            cardBinding.close.setOnClickListener(v1 -> dialog.dismiss());
            VideoCategoryAdapter adapter1 = new VideoCategoryAdapter(getContext(),category);
            cardBinding.categoryList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            cardBinding.categoryList.setAdapter(adapter1);
            dialog.show();
        });

        return binding.getRoot();
    }
}