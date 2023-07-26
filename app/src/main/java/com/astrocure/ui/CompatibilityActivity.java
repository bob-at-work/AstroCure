package com.astrocure.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.astrocure.R;
import com.astrocure.adapters.CompatibilityAdapter;
import com.astrocure.databinding.ActivityCompatibilityBinding;

public class CompatibilityActivity extends AppCompatActivity {
    ActivityCompatibilityBinding binding;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompatibilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SnapHelper snapHelper = new PagerSnapHelper();
        CompatibilityAdapter adapter = new CompatibilityAdapter(getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        binding.firstList.setLayoutManager(manager);
        snapHelper.attachToRecyclerView(binding.firstList);
        binding.firstList.setAdapter(adapter);
        binding.firstList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstItemVisible != 1 && firstItemVisible % /*itemList.size()*/12 == 1) {
                    ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPosition(1);
                }
                int firstCompletelyItemVisible = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                if (firstCompletelyItemVisible == 0)
                {}

                if (firstItemVisible != RecyclerView.NO_POSITION
                        && firstItemVisible== recyclerView.getAdapter().getItemCount()%/*itemList.size()*/12 - 1)
                {
                    ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(/*itemList.size()*/12 + 1, 0);
                }
            }
        });
    }
}