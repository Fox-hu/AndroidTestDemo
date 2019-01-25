package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyNestedScrollView1 extends NestedScrollView {
    private static final String TAG = MyNestedScrollView.class.getSimpleName();
    private float ox;
    private float oy;
    private int mLastY;
    private HideShowEvent.ScrollEventListener listener;

    public void setScrollEventListener(HideShowEvent.ScrollEventListener listener) {
        this.listener = listener;
    }

    public MyNestedScrollView1(Context context) {
        super(context);
    }

    public MyNestedScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ox = ev.getX();
                oy = ev.getY();
                if (oy < getTop() || oy > getBottom() || ox < getLeft() || ox > getRight()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastY;
                //此处和touchbehive不一样 代表的意义是相反的
                handleScrollAction(-deltaY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastY = y;
        return true;
    }

    private void handleScrollAction(int dy) {
        HideShowEvent.INSTANCE.handleScrollAction(dy, listener);
    }
}
