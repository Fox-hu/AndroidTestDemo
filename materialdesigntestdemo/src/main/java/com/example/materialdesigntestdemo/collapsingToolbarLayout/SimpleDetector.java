package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by fox.hu on 2019/1/24.
 */

public abstract class SimpleDetector extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    private static final String TAG = SimpleDetector.class.getSimpleName();

    private final GestureDetector mDetector;
    private final int mSlop = 0;

    public abstract void onScrollDown();

    public abstract void onScrollUp();

    public SimpleDetector(Context context) {
        mDetector = new GestureDetector(context, this);
    }

    protected int getSlop(Context context) {
        return ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e(TAG, "onFling ,velocityX = " + velocityX + "velocityY = " + velocityY + "y1 = " +
                   e1.getY() + " y2 = " + e2.getY());
        if (e1.getY() - e2.getY() > mSlop) {
            Log.e(TAG, "onFling ,onScrollUp");
            onScrollUp();
        }
        if (e2.getY() - e1.getY() > mSlop) {
            Log.e(TAG, "onFling ,onScrollDown");
            onScrollDown();
        }
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG, "onScroll ,distanceX = " + distanceX + "distanceY = " + distanceY);
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mDetector.onTouchEvent(event);
        return false;
    }
}
