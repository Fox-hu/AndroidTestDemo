package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fox on 2018/1/28.
 */

public class NestedScrollBehavior extends AppBarLayout.ScrollingViewBehavior {
    private static final String TAG = NestedScrollBehavior.class.getSimpleName();

    public NestedScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
            @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        Log.e(TAG, " dx = " + dx + " dy = " + dy);
    }
}
