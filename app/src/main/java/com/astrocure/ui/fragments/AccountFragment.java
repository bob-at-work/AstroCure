package com.astrocure.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.astrocure.databinding.FragmentAccountBinding;
import com.google.android.material.tabs.TabLayout;


public class AccountFragment extends Fragment {
    FragmentAccountBinding binding;
    ProfileFragment profileFragment;
    SettingFragment settingFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("PROFILE"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("SETTINGS"));

        profileFragment = new ProfileFragment();
        settingFragment = new SettingFragment();
        setFragment(profileFragment);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setFragment(profileFragment);
                        break;
                    case 1:
                        setFragment(settingFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();
    }

    private void setFragment(Fragment fragment) {
        fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.container.getId(), fragment);
        fragmentTransaction.commit();
    }
}