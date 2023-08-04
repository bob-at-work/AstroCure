package com.astrocure.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.astrocure.ui.fragments.TodayFragment;
import com.astrocure.ui.fragments.TomorrowFragment;
import com.astrocure.ui.fragments.YesterdayFragment;

public class BottomSheetHoroscopeAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public BottomSheetHoroscopeAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new YesterdayFragment();
            case 1:
                return new TodayFragment();
            case 2:
                return new TomorrowFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.yesterday);
            case 1:
                return context.getString(R.string.today);
            case 2:
                return context.getString(R.string.tomorrow);
            default:
                return null;
        }
//        return super.getPageTitle(position);
    }*/
}
