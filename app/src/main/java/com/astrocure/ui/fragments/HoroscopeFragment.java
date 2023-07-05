package com.astrocure.ui.fragments;

import static com.astrocure.utils.AppConstants.OPEN_DRAWER;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.astrocure.R;
import com.astrocure.adapters.HomeZodiacAdapter;
import com.astrocure.adapters.ZodiacViewpagerAdapter;
import com.astrocure.callback.SideNavigationCallback;
import com.astrocure.databinding.FragmentHoroscopeBinding;
import com.astrocure.models.HomeZodiacModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoroscopeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    FragmentHoroscopeBinding binding;
    HomeZodiacAdapter homeZodiacAdapter;
    List<HomeZodiacModel> modelList;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SideNavigationCallback callBackAction;

    public HoroscopeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHoroscopeBinding.inflate(inflater, container, false);

        binding.toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        binding.toolbar.setNavigationOnClickListener(view -> callBackAction.callBackAction(OPEN_DRAWER));

        modelList = new ArrayList<>();
        modelList.add(new HomeZodiacModel("Aries", R.drawable.aries_top));
        modelList.add(new HomeZodiacModel("Taurus", R.drawable.taurus_top));
        modelList.add(new HomeZodiacModel("Gemini", R.drawable.gemini_top));
        modelList.add(new HomeZodiacModel("Cancer", R.drawable.cancer_top));
        modelList.add(new HomeZodiacModel("Leo", R.drawable.leo_top));
        modelList.add(new HomeZodiacModel("Capricorn", R.drawable.capricorn_top));
        modelList.add(new HomeZodiacModel("Virgo", R.drawable.virgo_top));
        modelList.add(new HomeZodiacModel("Libra", R.drawable.libra_top));
        modelList.add(new HomeZodiacModel("Scorpius", R.drawable.scorpius_top));
        modelList.add(new HomeZodiacModel("Sagittarius", R.drawable.sagittarius_top));
        modelList.add(new HomeZodiacModel("Aquarius", R.drawable.aquarius_top));
        modelList.add(new HomeZodiacModel("Pisces", R.drawable.piseces_top));
        homeZodiacAdapter = new HomeZodiacAdapter(getContext(), modelList);
        binding.zodiacList.setAdapter(homeZodiacAdapter);

        binding.time.setText(new SimpleDateFormat("EEEE,dd MMM").format(new Date()));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Yesterday"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Today"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tomorrow"));

        ZodiacViewpagerAdapter viewpagerAdapter = new ZodiacViewpagerAdapter(getContext());
        binding.chartPercent.setAdapter(viewpagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.chartPercent);
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1));

        binding.zodiacLayout2.time.setText(new SimpleDateFormat("EEEE,MMMM d ").format(new Date()));
        binding.zodiacLayout1.currentDate.setText(new SimpleDateFormat("EEEE d MMMM").format(new Date()));
        binding.zodiacLayout2.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        binding.zodiacLayout2.loveBtn.setOnClickListener(v -> {
            binding.zodiacLayout2.description.setText(R.string.dummy_1);
            binding.zodiacLayout2.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiacLayout2.loveBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.zodiacLayout2.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiacLayout2.careerBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiacLayout2.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiacLayout2.healthBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        binding.zodiacLayout2.careerBtn.setOnClickListener(v -> {
            binding.zodiacLayout2.description.setText(R.string.dummy_2);
            binding.zodiacLayout2.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiacLayout2.careerBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.zodiacLayout2.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiacLayout2.loveBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiacLayout2.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiacLayout2.healthBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        binding.zodiacLayout2.healthBtn.setOnClickListener(v -> {
            binding.zodiacLayout2.description.setText(R.string.dummy_3);
            binding.zodiacLayout2.healthBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiacLayout2.healthBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.zodiacLayout2.careerBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiacLayout2.careerBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.zodiacLayout2.loveBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.zodiacLayout2.loveBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });

        return binding.getRoot();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

        }
        if (item.getItemId() == R.id.wallet) {
//            startActivity(new Intent(getContext(), FeedsActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu) {

//            binding.drawer.openDrawer(GravityCompat.END);
            return true;
        } else if (item.getItemId() == R.id.horoscope) {
            Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}