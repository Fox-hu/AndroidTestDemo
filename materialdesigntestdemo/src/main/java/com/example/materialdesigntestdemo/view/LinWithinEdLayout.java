package com.example.materialdesigntestdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.materialdesigntestdemo.R;

public class LinWithinEdLayout extends LinearLayout {
    private EditText editText;

    public LinWithinEdLayout(Context context) {
        this(context, null, 0);
    }

    public LinWithinEdLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinWithinEdLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        editText = new EditText(getContext());
        addView(editText, lp);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinWithinEdLayout);

        boolean isfocus = typedArray.getBoolean(R.styleable.LinWithinEdLayout_isEtFocus, false);
        if (isfocus) {
            editText.requestFocus();
        }

//        editText.requestFocus();
    }

}
