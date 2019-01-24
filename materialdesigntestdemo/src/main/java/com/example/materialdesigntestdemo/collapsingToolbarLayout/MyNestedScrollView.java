package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyNestedScrollView extends NestedScrollView {
    private static final String TAG = MyNestedScrollView.class.getSimpleName();
    private int mLastY;
    private ScrollEventListener listener;
    private ScrollType type = ScrollType.NORMAL;

    private enum ScrollType {
        UP, DOWN, NORMAL
    }

    public interface ScrollEventListener {
        void onScrollDown();

        void onScrollUp();
    }

    public void setScrollEventListener(ScrollEventListener listener) {
        this.listener = listener;
    }

    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                type = ScrollType.NORMAL;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastY;
                Log.e(TAG, " move,deltay = " + deltaY + " raw y = " + y + "mLastY" + mLastY);
                if (deltaY < 0) {
                    //上滑
                    type = ScrollType.UP;
                } else {
                    //下滑
                    type = ScrollType.DOWN;
                }
                break;
            case MotionEvent.ACTION_UP:
                handleScrollAction();
                type = ScrollType.NORMAL;
                break;
            default:
                break;
        }
        mLastY = y;
        return super.onTouchEvent(ev);
    }

    private void handleScrollAction() {
        switch (type) {
            case UP:
                if (listener != null) {
                    Log.e(TAG, "onScrollUp");
                    listener.onScrollUp();
                }
                break;
            case DOWN:
                if (listener != null) {
                    Log.e(TAG, "onScrollDown");
                    listener.onScrollDown();
                }
                break;
            default:
                break;
        }
    }
}
