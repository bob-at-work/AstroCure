package com.astrocure.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.astrocure.R;
import com.astrocure.databinding.FragmentEntertainmentBinding;
import com.astrocure.ui.CompatibilityActivity;
import com.astrocure.ui.CompatibilitySecondActivity;
import com.astrocure.ui.games.AstroGenieActivity;
import com.astrocure.ui.games.BookOfLoveActivity;
import com.astrocure.ui.games.ColorTherapyActivity;
import com.astrocure.ui.games.CrystalBallActivity;
import com.astrocure.ui.games.FortuneCookieActivity;


public class EntertainmentFragment extends Fragment {
    FragmentEntertainmentBinding binding;

    public EntertainmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEntertainmentBinding.inflate(inflater, container, false);

        binding.fortuneCookie.setOnClickListener(v -> startActivity(new Intent(getContext(), FortuneCookieActivity.class)));
        binding.crystalBall.setOnClickListener(v -> startActivity(new Intent(getContext(), CrystalBallActivity.class)));
        binding.bookOfLove.setOnClickListener(v -> startActivity(new Intent(getContext(), BookOfLoveActivity.class)));
        binding.astroGenie.setOnClickListener(v -> startActivity(new Intent(getContext(), AstroGenieActivity.class)));
        binding.colorTherapy.setOnClickListener(v -> startActivity(new Intent(getContext(), ColorTherapyActivity.class)));
        binding.games.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        binding.games.setOnClickListener(v -> {
//            binding.getRoot().setBackground(getContext().getDrawable(R.drawable.background_2));
            binding.games.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.games.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.compatibility.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.compatibility.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.compatibilityContainer.setVisibility(View.GONE);
            binding.gamesContainer.setVisibility(View.VISIBLE);
        });
        binding.compatibility.setOnClickListener(v -> {
//            binding.getRoot().setBackground(getContext().getDrawable(R.drawable.entertainment_bg));
            binding.compatibility.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.compatibility.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            binding.games.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.btn_inactive));
            binding.games.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            binding.compatibilityContainer.setVisibility(View.VISIBLE);
            binding.gamesContainer.setVisibility(View.GONE);
        });

        binding.bondCard.setOnClickListener(v -> startActivity(new Intent(getContext(), CompatibilitySecondActivity.class)));

        binding.compatibilityCard.setOnClickListener(v -> startActivity(new Intent(getContext(), CompatibilityActivity.class)));
        return binding.getRoot();
    }
}