package com.astrocure.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.FilterCheckAdapter;
import com.astrocure.adapters.FilterExpertiseAdapter;
import com.astrocure.adapters.StoryAdapter;
import com.astrocure.adapters.VideoContentAdapter;
import com.astrocure.databinding.DialogVideoFilterBinding;
import com.astrocure.databinding.FragmentVideosBinding;
import com.astrocure.models.StoryModel;
import com.astrocure.models.VideoContentModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment {

    FragmentVideosBinding binding;
    StoryAdapter adapter;
    VideoContentAdapter contentAdapter;
    List<String> category;
    List<StoryModel> storyList;
    List<VideoContentModel> videoContentModelList;
    String profileImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTZdaieBWs1FmM6BNf5x8bJojQ04hKM6WwhM_6C4fSyPZ9ZEyO2bNH6SkT-s_49p7WWsE&usqp=CAU";
    String contentImg = "https://plus.unsplash.com/premium_photo-1664298994861-c77da4eb0a4b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80";
    String videoUrl = "https://player.vimeo.com/external/291047369.sd.mp4?s=2f1d1301e3fa84499e1e7b17b0cf92d20850400a&profile_id=164&oauth2_token_id=57447761";
    String thumbnail = "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjJ8fHN0b2NrJTIwbWFya2V0fGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60";
    String videoUrl1 = "https://player.vimeo.com/external/412230837.sd.mp4?s=2759dbac227dc9a62851271c056b2fb8f2400aec&profile_id=165&oauth2_token_id=57447761";
    String videoUrl2 = "https://player.vimeo.com/external/263604204.sd.mp4?s=8c253c3b3603e70a627c3d8e208b2fe63564e0ec&profile_id=164&oauth2_token_id=57447761";
    String videoUrl3 = "https://player.vimeo.com/external/511524889.sd.mp4?s=150cec1df5d8eab068351fac399956a751692a8a&profile_id=165&oauth2_token_id=57447761";
    List<String> commoditiesList;
    List<String> sportsList;
    List<String> stockList;
    List<String> moviesList;
    List<String> miscList;
    FilterExpertiseAdapter commoditiesAdapter, sportsAdapter, stockAdapter, moviesAdapter;
    FilterCheckAdapter miscAdapter;

    public VideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVideosBinding.inflate(inflater, container, false);

        addDataToList();

        storyList = new ArrayList<>();
        storyList.add(new StoryModel(R.drawable.video_gold, "Commodities"));
        storyList.add(new StoryModel(R.drawable.video_sports, "Sports"));
        storyList.add(new StoryModel(R.drawable.video_stock, "Stock"));
        storyList.add(new StoryModel(R.drawable.video_bolly, "Bollywood"));
        storyList.add(new StoryModel(R.drawable.video_bolly, "Miscellaneous"));
        adapter = new StoryAdapter(getContext(), storyList);
        binding.storyList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.storyList.setAdapter(adapter);

        videoContentModelList = new ArrayList<>();
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, "", "", false));
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, videoUrl, thumbnail, true));
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, videoUrl1, thumbnail, true));
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, videoUrl2, thumbnail, false));
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, videoUrl2, thumbnail, false));
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, videoUrl2, thumbnail, true));
        videoContentModelList.add(new VideoContentModel(profileImg, "20 BSE500 stocks delivered double-digit weekly returns as markets turn wary of heights", "07:03 pm", "R.string.dummy_video_text", contentImg, videoUrl3, thumbnail, true));
        contentAdapter = new VideoContentAdapter(getContext(), videoContentModelList);
        binding.contentList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.contentList.setAdapter(contentAdapter);

        category = new ArrayList<>();
        category.add("Commodities");
        category.add("Sports");
        category.add("Stock");
        category.add("Bollywood");
        category.add("Miscellaneous");

        binding.filter.setOnClickListener(v -> {
            DialogVideoFilterBinding cardBinding = DialogVideoFilterBinding.inflate(getLayoutInflater());
            BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialog);
            commoditiesAdapter = new FilterExpertiseAdapter(getContext(), commoditiesList);
            sportsAdapter = new FilterExpertiseAdapter(getContext(), sportsList);
            stockAdapter = new FilterExpertiseAdapter(getContext(), stockList);
            moviesAdapter = new FilterExpertiseAdapter(getContext(), moviesList);
            miscAdapter = new FilterCheckAdapter(getContext(), miscList);
            dialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            dialog.setContentView(cardBinding.getRoot());
            cardBinding.commoditiesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.commoditiesList.setAdapter(commoditiesAdapter);
            cardBinding.sportsList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.sportsList.setAdapter(sportsAdapter);
            cardBinding.stockList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.stockList.setAdapter(stockAdapter);
            cardBinding.moviesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.moviesList.setAdapter(moviesAdapter);
            cardBinding.miscellaneousList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            cardBinding.miscellaneousList.setAdapter(miscAdapter);

            dialog.show();
        });

        return binding.getRoot();
    }

    private void addDataToList() {
        commoditiesList = new ArrayList<>();
        sportsList = new ArrayList<>();
        stockList = new ArrayList<>();
        miscList = new ArrayList<>();
        moviesList = new ArrayList<>();

        commoditiesList.add("Gold");
        commoditiesList.add("Silver");
        commoditiesList.add("Crude Oil");
        sportsList.add("Cricket");
        sportsList.add("Football");
        sportsList.add("Tennis");
        sportsList.add("Basketball");
        stockList.add("Global Market");
        moviesList.add("Hollywood");
        moviesList.add("Bollywood");
        moviesList.add("Tollywood");
        moviesList.add("Kollywood");
        miscList.add("Politics");
        miscList.add("Natural Disaster");
        miscList.add("Global Economy");
    }
}