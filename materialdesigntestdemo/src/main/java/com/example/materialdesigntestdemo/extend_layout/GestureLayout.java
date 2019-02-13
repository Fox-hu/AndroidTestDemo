package com.example.materialdesigntestdemo.extend_layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class GestureLayout extends RelativeLayout {
    private static final String TAG = GestureLayout.class.getSimpleName();

    private ViewDragHelper mViewDragHelper;

    public GestureLayout(@NonNull Context context) {
        this(context, null);
    }

    public GestureLayout(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new GestureDragCallback());
        mViewDragHelper.setEdgeTrackingEnabled(
                ViewDragHelper.EDGE_LEFT | ViewDragHelper.EDGE_RIGHT | ViewDragHelper.EDGE_BOTTOM);
    }

    private class GestureDragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.e(TAG, "tryCaptureView ,child = " + child.getClass().getSimpleName());
            return false;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.e(TAG, "clampViewPositionVertical ,child = " + child.getClass().getSimpleName());
            return super.clampViewPositionVertical(child, top, dy);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.e(TAG, "clampViewPositionHorizontal ,child = " + child.getClass().getSimpleName());
            return super.clampViewPositionHorizontal(child, left, dx);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.e(TAG, "onViewPositionChanged ,child = " + changedView.getClass().getSimpleName());
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.e(TAG, "onViewReleased ,child = " + releasedChild.getClass().getSimpleName());
        }

        //在边界拖动时回调
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            Log.e(TAG, "onEdgeDragStarted ");
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e(TAG, "onInterceptTouchEvent action = " + event.getAction());
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent action = " + event.getAction());
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}