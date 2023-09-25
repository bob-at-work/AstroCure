package com.astrocure.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.PlanFeatureAdapter;
import com.astrocure.adapters.PlansAdapter;
import com.astrocure.databinding.ActivityPlansBinding;
import com.astrocure.databinding.DialogPlanPreviewBinding;
import com.astrocure.models.PlanModel;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlansActivity extends AppCompatActivity {
    ActivityPlansBinding binding;
    List<PlanModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlansBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.close.setOnClickListener(v -> onBackPressed());

        list = new ArrayList<>();


        getPlanData();
        PlansAdapter adapter = new PlansAdapter(getApplicationContext(), list);
        binding.planList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, LinearLayoutManager.VERTICAL, false));
        binding.planList.setAdapter(adapter);

        adapter.setOnItemClick((Position, planModel) -> {
            Dialog dialog = new Dialog(PlansActivity.this, R.style.Theme_AstroCure);
            DialogPlanPreviewBinding previewBinding = DialogPlanPreviewBinding.inflate(getLayoutInflater());
            dialog.setContentView(previewBinding.getRoot());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(200, 0, 0, 0)));
            previewBinding.getRoot().setOnClickListener(v -> dialog.dismiss());
            if (planModel.getPlanName().matches("Elite")) {
                previewBinding.tagContainer.setVisibility(View.VISIBLE);
            }
            previewBinding.title.setText(planModel.getPlanName());
            Glide.with(getApplicationContext()).load(planModel.getPlanImage()).into(previewBinding.planImg);
            previewBinding.activePrice.setText("₹" + planModel.getActivePrice() + "/Month");
            previewBinding.actualPrice.setText("₹" + planModel.getActualPrice() + "/mo");
            PlanFeatureAdapter planFeatureAdapter = new PlanFeatureAdapter(getApplicationContext(), planModel.getFeaturesName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            previewBinding.featureList.setLayoutManager(linearLayoutManager);
            previewBinding.featureList.setAdapter(planFeatureAdapter);
            previewBinding.selectPlan.setOnClickListener(v -> {
                Intent intent = new Intent(new Intent(getApplicationContext(), BillingPreviewActivity.class));
                intent.putExtra("plan_model", (Serializable) planModel);
                startActivity(intent);
            });
            dialog.show();
        });
    }

    private void getPlanData() {
        List<PlanModel.FeaturesName> standardFeature = new ArrayList<>();
        standardFeature.add(new PlanModel.FeaturesName("Movies", R.drawable.ic_plan_movies));
        standardFeature.add(new PlanModel.FeaturesName("Stock", R.drawable.ic_plan_stock));
        standardFeature.add(new PlanModel.FeaturesName("Sports", R.drawable.ic_plan_sports));
        standardFeature.add(new PlanModel.FeaturesName("Miscellaneous", R.drawable.ic_plan_miscellaneous));
        list.add(new PlanModel("Standard", R.drawable.ic_plan_standard, "4500", "4899", false, standardFeature));

        List<PlanModel.FeaturesName> standardSecondFeature = new ArrayList<>();
        standardSecondFeature.add(new PlanModel.FeaturesName("Commodities", R.drawable.ic_plan_commodities));
        standardSecondFeature.add(new PlanModel.FeaturesName("Movies", R.drawable.ic_plan_movies));
        standardSecondFeature.add(new PlanModel.FeaturesName("Stock", R.drawable.ic_plan_stock));
        standardSecondFeature.add(new PlanModel.FeaturesName("Miscellaneous", R.drawable.ic_plan_miscellaneous));
        list.add(new PlanModel("Standard", R.drawable.ic_plan_standard, "4500", "5599", true, standardSecondFeature));

        List<PlanModel.FeaturesName> professionalFeature = new ArrayList<>();
        professionalFeature.add(new PlanModel.FeaturesName("Commodities", R.drawable.ic_plan_commodities));
        professionalFeature.add(new PlanModel.FeaturesName("Stock", R.drawable.ic_plan_stock));
        list.add(new PlanModel("Professional", R.drawable.ic_plan_professional, "3999", "4599", true, professionalFeature));

        List<PlanModel.FeaturesName> professionalSecondFeature = new ArrayList<>();
        professionalSecondFeature.add(new PlanModel.FeaturesName("Sports", R.drawable.ic_plan_sports));
        professionalSecondFeature.add(new PlanModel.FeaturesName("Miscellaneous", R.drawable.ic_plan_miscellaneous));
        list.add(new PlanModel("Professional", R.drawable.ic_plan_professional, "3999", "4599", true, professionalSecondFeature));

        List<PlanModel.FeaturesName> premiumFeature = new ArrayList<>();
        premiumFeature.add(new PlanModel.FeaturesName("Commodities", R.drawable.ic_plan_commodities));
        premiumFeature.add(new PlanModel.FeaturesName("Sports", R.drawable.ic_plan_sports));
        list.add(new PlanModel("Premium", R.drawable.ic_plan_professional, "7500", "8599", true, premiumFeature));

        List<PlanModel.FeaturesName> eliteFeature = new ArrayList<>();
        eliteFeature.add(new PlanModel.FeaturesName("Commodities", R.drawable.ic_plan_commodities));
        eliteFeature.add(new PlanModel.FeaturesName("Movies", R.drawable.ic_plan_movies));
        eliteFeature.add(new PlanModel.FeaturesName("Stock", R.drawable.ic_plan_stock));
        eliteFeature.add(new PlanModel.FeaturesName("Sports", R.drawable.ic_plan_sports));
        eliteFeature.add(new PlanModel.FeaturesName("Miscellaneous", R.drawable.ic_plan_miscellaneous));
        list.add(new PlanModel("Elite", R.drawable.ic_plan_standard, "8000", "8899", false, eliteFeature));
    }
}