package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyAppBarLayout extends AppBarLayout {
    private static final String TAG = MyAppBarLayout.class.getSimpleName();
    private State mCurrentState = State.IDLE;
    private AppBarStateListener stateListener;

    public void setStateListener(AppBarStateListener stateListener) {
        this.stateListener = stateListener;
    }


    protected interface AppBarStateListener {
        void onStateChanged(AppBarLayout appBarLayout, State idle);
    }

    public MyAppBarLayout(Context context) {
        super(context);
        addOnOffsetChangedListener(listener);
    }

    public MyAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnOffsetChangedListener(listener);
    }

    public enum State {
        EXPANDED, COLLAPSED, IDLE
    }

    public State getCurrentState() {
        return mCurrentState;
    }


    private OnOffsetChangedListener listener = new OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (stateListener == null) {
                return;
            }

            if (verticalOffset == 0) {
                stateListener.onStateChanged(appBarLayout, State.EXPANDED);
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                stateListener.onStateChanged(appBarLayout, State.COLLAPSED);
                mCurrentState = State.COLLAPSED;
            } else {
                stateListener.onStateChanged(appBarLayout, State.IDLE);
                mCurrentState = State.IDLE;
            }
        }
    };


}
