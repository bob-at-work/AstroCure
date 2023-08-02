package com.astrocure.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.astrocure.adapters.CompatibilityAdapter;
import com.astrocure.databinding.ActivityCompatibilityBinding;

public class CompatibilityActivity extends AppCompatActivity {
    ActivityCompatibilityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompatibilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SnapHelper snapHelper = new PagerSnapHelper();
//        LinearSnapHelper snapHelper = new LinearSnapHelper();
        CompatibilityAdapter adapter = new CompatibilityAdapter(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.firstList.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(binding.firstList);
        binding.firstList.setOnFlingListener(snapHelper);
        binding.firstList.setAdapter(adapter);
//        int totalVisibleItems = layoutManager.findLastVisibleItemPosition() - layoutManager.findFirstVisibleItemPosition();
//        int centeredItemPosition = totalVisibleItems / 2;
        /*binding.firstList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstItemVisible != 1 && firstItemVisible % *//*itemList.size()*//*12 == 1) {
                    recyclerView.getLayoutManager().scrollToPosition(1);
                }
                int firstCompletelyItemVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                if (firstCompletelyItemVisible == 0) {
                }

                if (firstItemVisible != RecyclerView.NO_POSITION
                        && firstItemVisible == recyclerView.getAdapter().getItemCount() %*//*itemList.size()*//*12 - 1) {
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(*//*itemList.size()*//*12 + 1, 0);
                }
            }
        });*/
    }
}