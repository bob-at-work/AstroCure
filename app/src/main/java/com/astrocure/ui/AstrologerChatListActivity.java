package com.astrocure.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.AstrologerChatListAdapter;
import com.astrocure.adapters.FilterExpertiseAdapter;
import com.astrocure.databinding.ActivityAstrologerChatListBinding;
import com.astrocure.databinding.DialogCallChatFilterBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class AstrologerChatListActivity extends AppCompatActivity {

    ActivityAstrologerChatListBinding binding;
    AstrologerChatListAdapter adapter;
    List<String> expertiseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        adapter = new AstrologerChatListAdapter(getApplicationContext());
        binding.contentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.contentList.setAdapter(adapter);

        expertiseList = new ArrayList<>();
        expertiseList.add("Tarot");
        expertiseList.add("Vedic");
        expertiseList.add("Vastu");
        expertiseList.add("Numerology");
        expertiseList.add("Psychic");

        binding.filter.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AstrologerChatListActivity.this, R.style.BottomSheetDialog);
            DialogCallChatFilterBinding filterBinding = DialogCallChatFilterBinding.inflate(getLayoutInflater());
            FilterExpertiseAdapter expertiseAdapter = new FilterExpertiseAdapter(getApplicationContext(), expertiseList);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
            filterBinding.expertiseList.setLayoutManager(staggeredGridLayoutManager);
            filterBinding.expertiseList.setAdapter(expertiseAdapter);
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.setContentView(filterBinding.getRoot());

            filterBinding.reset.setOnClickListener(v12 -> bottomSheetDialog.cancel());
            filterBinding.apply.setOnClickListener(v1 -> {
                Toast.makeText(AstrologerChatListActivity.this, "Filter Applied", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });

            bottomSheetDialog.show();
        });
    }
}