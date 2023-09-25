package com.astrocure.utils;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ActionMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

public class AppTextInputLayout extends TextInputLayout implements com.astrocure.callback.AppTextInputLayout {
    private final float mSpace = 24; //24 dp by default, space between the lines
    private final float mNumChars = 4;
    private final float mLineSpacing = 8; //8dp by default, height of the text from our lines
    private final int mMaxLength = 6;
    private final float mLineStroke = 2;
    private Paint mLinesPaint;
    private OnClickListener mClickListener;
    private OnFocusChangeListener onFocusChangeListener;

    public AppTextInputLayout(@NonNull Context context) {
        super(context);
    }

    public AppTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        float multi = context.getResources().getDisplayMetrics().density;
        setHelperText(getHint());
        super.setOnFocusChangeListener((v, hasFocus) -> {
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(v, hasFocus);
            }
            if (hasFocus) {
                setHelperText(getHint());
                setHint("");
            }
        });
        super.setOnClickListener(v -> {
            // When tapped, move cursor to end of text.

            if (mClickListener != null) {
                mClickListener.onClick(v);
            }
        });
    }

    @Override
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        throw new RuntimeException("setCustomSelectionActionModeCallback() not supported.");
    }
}
