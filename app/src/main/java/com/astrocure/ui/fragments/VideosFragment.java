package com.astrocure.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.StoryAdapter;
import com.astrocure.adapters.VideoCategoryAdapter;
import com.astrocure.adapters.VideoContentAdapter;
import com.astrocure.databinding.DialogVideoFilterBinding;
import com.astrocure.databinding.FragmentVideosBinding;
import com.astrocure.models.StoryModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment {

    FragmentVideosBinding binding;
    StoryAdapter adapter;
    VideoContentAdapter contentAdapter;
    List<String> category;
    List<StoryModel> storyList;

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

        storyList = new ArrayList<>();
        storyList.add(new StoryModel(R.drawable.video_gold,"Commodities"));
        storyList.add(new StoryModel(R.drawable.video_sports,"Sports"));
        storyList.add(new StoryModel(R.drawable.video_stock,"Stock"));
        storyList.add(new StoryModel(R.drawable.video_bolly,"Bollywood"));
        storyList.add(new StoryModel(R.drawable.video_bolly,"Miscellaneous"));
        adapter = new StoryAdapter(getContext(),storyList);
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