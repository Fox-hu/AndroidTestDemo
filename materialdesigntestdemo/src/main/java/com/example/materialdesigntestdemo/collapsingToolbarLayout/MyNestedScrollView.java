package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyNestedScrollView extends NestedScrollView {
    private static final String TAG = MyNestedScrollView.class.getSimpleName();

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
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.VISIBLE) {
            Log.e(TAG, "可见");
        } else if (visibility == INVISIBLE || visibility == GONE) {
            Log.e(TAG, "不可见");
        }
    }
}
