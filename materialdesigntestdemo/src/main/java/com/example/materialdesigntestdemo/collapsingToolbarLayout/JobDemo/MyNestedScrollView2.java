package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyNestedScrollView2 extends NestedScrollView {
    private static final String TAG = MyNestedScrollView2.class.getSimpleName();

    public void setStopListener(StopListener stopListener) {
        this.stopListener = stopListener;
    }

    private StopListener stopListener;

    public interface StopListener {
        void onScrollStop();
    }

    public MyNestedScrollView2(Context context) {
        super(context);
    }

    public MyNestedScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void stopNestedScroll(int type) {
        super.stopNestedScroll(type);
        if (stopListener != null) {
            stopListener.onScrollStop();
        }
    }
}
