package com.astrocure.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.astrocure.R;

public class HoroscopeView extends ConstraintLayout {

    TextView mood, love, wellBeing, socialLife, career, finance;
    @StyleableRes
    int index0 = 0;
    @SuppressLint("ResourceType")
    @StyleableRes
    int index1 = 1;
    @SuppressLint("ResourceType")
    @StyleableRes
    int index2 = 2;
    int index3 = 3;
    int index4 = 4;
    int index5 = 5;

    public HoroscopeView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.control_horoscope,this);
        inflate(context, R.layout.control_horoscope, this);
        int[] sets = {
                R.attr.moodPercent,
                R.attr.lovePercent,
                R.attr.wellBeingPercent,
                R.attr.socialPercent,
                R.attr.careerPercent,
                R.attr.financePercent
        };
        @SuppressLint("ResourceType") TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence moodPercent = typedArray.getText(index0);
        CharSequence lovePercent = typedArray.getText(index1);
        CharSequence wellBeingPercent = typedArray.getText(index2);
        CharSequence socialPercent = typedArray.getText(index3);
        CharSequence careerPercent = typedArray.getText(index4);
        CharSequence financePercent = typedArray.getText(index5);
        typedArray.recycle();

        initComponents();
    }
    private void initComponents() {
        mood = findViewById(R.id.mood);
        love = findViewById(R.id.love);
        wellBeing = findViewById(R.id.well_being);
        socialLife = findViewById(R.id.social_life);
        career = findViewById(R.id.career);
        finance = findViewById(R.id.finance);
    }

    public CharSequence getMood() {
        return mood.getText();
    }

    public void setMood(CharSequence mood) {
        this.mood.setText(mood);
    }

    public CharSequence getLove() {
        return love.getText();
    }

    public void setLove(CharSequence love) {
        this.love.setText(love);
    }

    public CharSequence getWellBeing() {
        return wellBeing.getText();
    }

    public void setWellBeing(CharSequence wellBeing) {
        this.wellBeing.setText(wellBeing);
    }

    public CharSequence getSocialLife() {
        return socialLife.getText();
    }

    public void setSocialLife(CharSequence socialLife) {
        this.socialLife.setText(socialLife);
    }

    public CharSequence getCareer() {
        return career.getText();
    }

    public void setCareer(CharSequence career) {
        this.career.setText(career);
    }

    public CharSequence getFinance() {
        return finance.getText();
    }

    public void setFinance(CharSequence finance) {
        this.finance.setText(finance);
    }
}
