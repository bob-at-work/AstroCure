package com.astrocure.ui;

import static com.astrocure.utils.AppConstants.OPEN_DRAWER;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.astrocure.R;
import com.astrocure.callback.SideNavigationCallback;
import com.astrocure.databinding.ActivityHomeBinding;
import com.astrocure.ui.fragments.EntertainmentFragment;
import com.astrocure.ui.fragments.HomeFeedFragment;
import com.astrocure.ui.fragments.HoroscopeFragment;
import com.astrocure.ui.fragments.ProfileFragment;
import com.astrocure.ui.fragments.VideosFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SideNavigationCallback {
    ActivityHomeBinding binding;
    HoroscopeFragment horoscopeFragment;
    HomeFeedFragment homeFeedFragment;
    VideosFragment videosFragment;
    EntertainmentFragment entertainmentFragment;
    ProfileFragment profileFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean doubleBackToExitPressedOnce = false;
    boolean isVideoFabClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sideNav.getMenu().addSubMenu(R.id.center, R.id.help_center, 0, "");

        horoscopeFragment = new HoroscopeFragment();
        homeFeedFragment = new HomeFeedFragment();
        videosFragment = new VideosFragment();
        entertainmentFragment = new EntertainmentFragment();
        profileFragment = new ProfileFragment();
        setFragment(horoscopeFragment);

        binding.bottomNav.setItemIconTintList(null);
        binding.videosFab.setImageTintList(null);
        binding.bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.getHeaderView(0).findViewById(R.id.close_drawer).setOnClickListener(v -> binding.drawer.closeDrawer(GravityCompat.END));

        binding.videosFab.setOnClickListener(v -> {
            if (!isVideoFabClicked) {
                isVideoFabClicked = true;
                binding.videosFab.setImageResource(R.drawable.bottom_nav_video_active);
                binding.bottomNav.setSelectedItemId(R.id.videos);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.horoscope) {
            isVideoFabClicked = false;
            binding.videosFab.setImageResource(R.drawable.bottom_nav_video_inactive);
            setFragment(horoscopeFragment);
            return true;
        } else if (item.getItemId() == R.id.feeds) {
            isVideoFabClicked = false;
            binding.videosFab.setImageResource(R.drawable.bottom_nav_video_inactive);
            setFragment(homeFeedFragment);
            return true;
        } else if (item.getItemId() == R.id.videos) {
            setFragment(videosFragment);
            return true;
        } else if (item.getItemId() == R.id.entertainment) {
            isVideoFabClicked = false;
            binding.videosFab.setImageResource(R.drawable.bottom_nav_video_inactive);
            setFragment(entertainmentFragment);
            return true;
        } else if (item.getItemId() == R.id.profile) {
            isVideoFabClicked = false;
            binding.videosFab.setImageResource(R.drawable.bottom_nav_video_inactive);
            setFragment(profileFragment);
            return true;
        } else if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
        } else if (item.getItemId() == R.id.transaction_history) {
            startActivity(new Intent(getApplicationContext(), TransactionHistoryActivity.class));
        } else if (item.getItemId() == R.id.wallet_nav) {
            startActivity(new Intent(getApplicationContext(), WalletActivity.class));
        } else if (item.getItemId() == R.id.rate_the_app) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        } else if (item.getItemId() == R.id.feedback) {
            startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
        } else if (item.getItemId() == R.id.help_center) {
            startActivity(new Intent(getApplicationContext(), HelpCenterActivity.class));
        } else if (item.getItemId() == R.id.favourites) {
            startActivity(new Intent(getApplicationContext(), FavouritesActivity.class));
        }
        binding.drawer.closeDrawer(GravityCompat.END);
        return false;
    }

    private void setFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.mainContainer.getId(), fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void callBackAction(String action) {
        switch (action) {
            case OPEN_DRAWER:
                binding.drawer.openDrawer(GravityCompat.END);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.findFragmentById(binding.mainContainer.getId()) instanceof HoroscopeFragment) {
            if (binding.drawer.isDrawerOpen(GravityCompat.END)) {
                binding.drawer.closeDrawer(GravityCompat.END);
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    finishAffinity();
                    finish();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_LONG).show();

                new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            }
        } else {
            binding.bottomNav.setSelectedItemId(R.id.horoscope);
        }
    }

}