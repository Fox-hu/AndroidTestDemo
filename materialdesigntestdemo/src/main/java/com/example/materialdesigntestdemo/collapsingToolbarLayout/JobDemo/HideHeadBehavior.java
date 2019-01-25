package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.materialdesigntestdemo.R;

/**
 * Created by fox on 2018/1/28.
 */

public class HideHeadBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = HideHeadBehavior.class.getSimpleName();

    public HideHeadBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
            View directTargetChild, View target, int nestedScrollAxes) {
        if (target.getId() == R.id.rel_body) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
            int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (HideShowEvent.INSTANCE.isAnimating()) {
            return;
        }
        HideShowEvent.INSTANCE.handleScrollAction(dy, listener);
    }

    private HideShowEvent.ScrollEventListener listener = new HideShowEvent.ScrollEventListener() {
        @Override
        public void onScrollDown() {
            Log.e(TAG, "onScrollDown");
        }

        @Override
        public void onScrollUp() {
            Log.e(TAG, "onScrollUp");
        }
    };
}
