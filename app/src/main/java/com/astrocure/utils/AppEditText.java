package com.astrocure.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.astrocure.R;

public class AppEditText extends ConstraintLayout {

    TextView helperText;
    EditText inputText;
    int index0 = 0;
    int index1 = 1;


    public AppEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        inflate(context, R.layout.app_edit_text, this);
        int[] sets = {
                R.attr.active_hint,
                R.attr.hint,
        };
        @SuppressLint("ResourceType") TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence activeHint = typedArray.getText(index0);
        CharSequence hint = typedArray.getText(index1);
        typedArray.recycle();
        initComponents();
    }

    private void initComponents() {
        helperText = findViewById(R.id.helper_hint);
        inputText = findViewById(R.id.main_text);
//        helperText.setText(inputText.getHint().toString());
    }
}
