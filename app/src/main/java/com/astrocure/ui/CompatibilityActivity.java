package com.astrocure.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.astrocure.R;
import com.astrocure.adapters.CompatibilityAdapter;
import com.astrocure.databinding.ActivityCompatibilityBinding;
import com.astrocure.models.CompatibilityZodiacModel;

import java.util.ArrayList;
import java.util.List;

public class CompatibilityActivity extends AppCompatActivity {
    ActivityCompatibilityBinding binding;
    List<CompatibilityZodiacModel> zodiacModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompatibilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        zodiacModels = new ArrayList<>();
        zodiacModels.add(new CompatibilityZodiacModel("Aries", R.drawable.aries));
        zodiacModels.add(new CompatibilityZodiacModel("Taurus", R.drawable.taurus));
        zodiacModels.add(new CompatibilityZodiacModel("Gemini", R.drawable.gemini));
        zodiacModels.add(new CompatibilityZodiacModel("Cancer", R.drawable.cancer));
        zodiacModels.add(new CompatibilityZodiacModel("Leo", R.drawable.leo));
        zodiacModels.add(new CompatibilityZodiacModel("Virgo", R.drawable.virgo_top));
        zodiacModels.add(new CompatibilityZodiacModel("Libra", R.drawable.libra));
        zodiacModels.add(new CompatibilityZodiacModel("Scorpius", R.drawable.scorpio));
        zodiacModels.add(new CompatibilityZodiacModel("Sagittarius", R.drawable.sagittarius));
        zodiacModels.add(new CompatibilityZodiacModel("Capricorn", R.drawable.capricorn));
        zodiacModels.add(new CompatibilityZodiacModel("Aquarius", R.drawable.aquarius));
        zodiacModels.add(new CompatibilityZodiacModel("Pisces", R.drawable.piseces_top));
        SnapHelper snapHelper = new PagerSnapHelper();
//        LinearSnapHelper snapHelper = new LinearSnapHelper();
        CompatibilityAdapter adapter = new CompatibilityAdapter(getApplicationContext(),zodiacModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.firstList.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(binding.firstList);
        binding.firstList.setOnFlingListener(snapHelper);
        binding.firstList.setAdapter(adapter);
        binding.firstList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log .i("TAG", "onCreate: "+layoutManager.findFirstCompletelyVisibleItemPosition()+" "+layoutManager.findLastCompletelyVisibleItemPosition());
                int firstItemVisible = layoutManager.findFirstVisibleItemPosition();
//                if (firstItemVisible != 0 && firstItemVisible % zodiacModels.size() == 0) {
//                    recyclerView.getLayoutManager().scrollToPosition(0);
//                }
                if (firstItemVisible!=1 && firstItemVisible % zodiacModels.size() == 1){
                    layoutManager.scrollToPosition(1);
                }else {
                    layoutManager.scrollToPositionWithOffset(zodiacModels.size(),-recyclerView.computeHorizontalScrollOffset());
                }
            }
        });


    }
}