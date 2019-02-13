package com.example.materialdesigntestdemo.extend_layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.component.common.utils.Utils;
import com.example.materialdesigntestdemo.R;

public class ExtendListHeader extends ExtendLayout {
    private static final String TAG = ExtendListHeader.class.getSimpleName();

    float containerHeight = Utils.dip2px(60);
    float listHeight = Utils.dip2px(120);
    boolean arrivedListHeight = false;
    private RecyclerView mRecyclerView;

    public ExtendListHeader(Context context) {
        super(context);
    }

    public ExtendListHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendListHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void bindView(View container) {
        mRecyclerView = findViewById(R.id.list);
    }

    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        return LayoutInflater.from(context).inflate(R.layout.extend_header, null);
    }

    @Override
    protected void notifyStateChange(State state) {
        switch (state) {
            case RESET:
                mRecyclerView.setTranslationY(0);
                arrivedListHeight = false;
                break;
            case ARRIVED_LIST_HEIGHT:
                arrivedListHeight = true;
                break;
            default:
                break;
        }
    }

    @Override
    public int getContentSize() {
        return (int) containerHeight;
    }

    @Override
    public int getListSize() {
        return (int) (listHeight);
    }

    @Override
    public void onPull(int offset) {
        if (!arrivedListHeight) {
            float percent = Math.abs(offset) / containerHeight;
            int moreOffset = Math.abs(offset) - (int) containerHeight;
            if (percent <= 1.0f) {
                mRecyclerView.setTranslationY(-containerHeight);
            } else {
                float subPercent = (moreOffset) / (listHeight - containerHeight);
                subPercent = Math.min(1.0f, subPercent);
                mRecyclerView.setTranslationY(-(1 - subPercent) * containerHeight);
            }
        }
        if (Math.abs(offset) >= listHeight) {
            mRecyclerView.setTranslationY(-(Math.abs(offset) - listHeight) / 2);
        }
    }
}
