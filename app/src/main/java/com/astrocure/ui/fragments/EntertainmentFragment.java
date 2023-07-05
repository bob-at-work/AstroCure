package com.astrocure.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astrocure.R;
import com.astrocure.databinding.FragmentEntertainmentBinding;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEntertainmentBinding.inflate(inflater,container,false);

//        binding.back.setOnClickListener(v -> onBackPressed());
        binding.fortuneCookie.setOnClickListener(v -> startActivity(new Intent(getContext(), FortuneCookieActivity.class)));
        binding.crystalBall.setOnClickListener(v -> startActivity(new Intent(getContext(), CrystalBallActivity.class)));
        binding.bookOfLove.setOnClickListener(v -> startActivity(new Intent(getContext(), BookOfLoveActivity.class)));
        binding.astroGenie.setOnClickListener(v -> startActivity(new Intent(getContext(), AstroGenieActivity.class)));
        binding.colorTherapy.setOnClickListener(v -> startActivity(new Intent(getContext(), ColorTherapyActivity.class)));
        return binding.getRoot();
    }
}