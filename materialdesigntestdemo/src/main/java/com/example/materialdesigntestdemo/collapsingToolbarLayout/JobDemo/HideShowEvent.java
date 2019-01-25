package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

/**
 * Created by fox.hu on 2019/1/25.
 */

public enum HideShowEvent {
    INSTANCE;

    public boolean isAnimating() {
        return isAnimating;
    }

    private boolean isAnimating = false;
    private static final String TAG = HideShowEvent.class.getSimpleName();
    private final int SCROOL_VALUE = 50;
    private boolean isHeadHide = false;
    private final int animationDuration = 500;

    public void setChildHeight(int childHeight) {
        this.childHeight = childHeight;
    }

    private int childHeight;

    public void setChild(View child) {
        this.child = child;
    }

    public void setTarget(View target) {
        this.target = target;
    }

    private View child;
    private View target;

    public interface ScrollEventListener {
        void onScrollDown();

        void onScrollUp();
    }

    public void handleScrollAction(int dy, ScrollEventListener listener) {
        if (dy > SCROOL_VALUE && !isHeadHide) {
            isHeadHide = true;
            hide();
            if (listener != null) {
                Log.e(TAG, "onScrollUp");
                listener.onScrollUp();
            }
        } else if (dy < -SCROOL_VALUE && isHeadHide) {
            isHeadHide = false;
            show();
            if (listener != null) {
                Log.e(TAG, "onScrollDown");
                listener.onScrollDown();
            }
        }
    }

    public void hide() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, childHeight);
        valueAnimator.setDuration(animationDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (child.getBottom() > 0) {
                    int value = (int) animation.getAnimatedValue();
                    Log.e(TAG, "hide top getAnimatedValue = " + value);
                    isAnimating = value != childHeight;
                    child.layout(child.getLeft(), -value, child.getRight(), -value + childHeight);
                    target.layout(target.getLeft(), -value + childHeight, target.getRight(),
                            target.getBottom());
                }
            }
        });
        valueAnimator.start();
    }

    public void show() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, childHeight);
        valueAnimator.setDuration(animationDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (child.getBottom() < childHeight) {
                    int value = (int) animation.getAnimatedValue();
                    Log.e(TAG, "show getAnimatedValue = " + value);
                    isAnimating = value != childHeight;
                    child.layout(child.getLeft(), value - childHeight, child.getRight(), value);
                    target.layout(target.getLeft(), value, target.getRight(), target.getBottom());
                }
            }
        });
        valueAnimator.start();
    }
}
