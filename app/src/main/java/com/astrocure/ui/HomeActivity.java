package com.astrocure.ui;

import static com.astrocure.utils.AppConstants.OPEN_DRAWER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.astrocure.R;
import com.astrocure.adapters.HomeZodiacAdapter;
import com.astrocure.adapters.ZodiacViewpagerAdapter;
import com.astrocure.callback.SideNavigationCallback;
import com.astrocure.databinding.ActivityHomeBinding;
import com.astrocure.ui.fragments.EntertainmentFragment;
import com.astrocure.ui.fragments.HomeFeedFragment;
import com.astrocure.ui.fragments.HoroscopeFragment;
import com.astrocure.ui.fragments.ProfileFragment;
import com.astrocure.ui.fragments.TodayFragment;
import com.astrocure.models.HomeZodiacModel;
import com.astrocure.ui.fragments.VideosFragment;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SideNavigationCallback {
    ActivityHomeBinding binding;
    HomeZodiacAdapter homeZodiacAdapter;
    List<HomeZodiacModel> modelList;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sideNav.getMenu().addSubMenu(R.id.center,R.id.help_center,0,"");

        fragment = new HoroscopeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
        fragmentTransaction.commit();

        binding.bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        binding.sideNav.getHeaderView(0).findViewById(R.id.close_drawer).setOnClickListener(v -> binding.drawer.closeDrawer(GravityCompat.END));

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.horoscope){
            fragment = new HoroscopeFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        } else if (item.getItemId() == R.id.feeds) {
            fragment = new HomeFeedFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        }else if (item.getItemId() == R.id.videos){
            fragment = new VideosFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        }else if (item.getItemId()==R.id.entertainment){
            fragment = new EntertainmentFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        } else if (item.getItemId() == R.id.profile) {
            fragment = new ProfileFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(),fragment);
            fragmentTransaction.commit();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
        } else if (item.getItemId() == R.id.transaction_history) {
            startActivity(new Intent(getApplicationContext(), TransactionHistoryActivity.class));
        }else if (item.getItemId() == R.id.wallet_nav){
            startActivity(new Intent(getApplicationContext(),WalletActivity.class));
        }else if (item.getItemId() == R.id.rate_the_app){
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
//                Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        } else if (item.getItemId() == R.id.feedback) {
            startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
        }else if (item.getItemId()==R.id.help_center){
            startActivity(new Intent(getApplicationContext(), HelpCenterActivity.class));
        } else if (item.getItemId()==R.id.favourites) {
            startActivity(new Intent(getApplicationContext(),FavouritesActivity.class));
        }
        binding.drawer.closeDrawer(GravityCompat.END);
        return false;
    }

    @Override
    public void callBackAction(String action) {
        switch (action){
            case OPEN_DRAWER:
                binding.drawer.openDrawer(GravityCompat.END);
                break;
        }
    }

    @Override
    public void onBackPressed() {
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

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

}