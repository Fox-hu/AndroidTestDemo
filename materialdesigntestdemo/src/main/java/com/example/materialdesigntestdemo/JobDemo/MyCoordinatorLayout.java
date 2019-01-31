package com.example.materialdesigntestdemo.JobDemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyCoordinatorLayout extends CoordinatorLayout {
    private static final String TAG = MyCoordinatorLayout.class.getSimpleName();

    public MyCoordinatorLayout(Context context) {
        super(context);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"MyCoordinatorLayout MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"MyCoordinatorLayout MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"MyCoordinatorLayout MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
