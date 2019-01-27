package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

/**
 * Created by fox.hu on 2019/1/24.
 */

public class MyAppBarLayout extends AppBarLayout {

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

    private State mCurrentState = State.IDLE;

    private OnOffsetChangedListener listener = new OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (verticalOffset == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }
    };

    protected void onStateChanged(AppBarLayout appBarLayout, State idle) {

    }

    @Override
    public void setExpanded(boolean expanded, boolean animate) {
        super.setExpanded(expanded, animate);
    }
}