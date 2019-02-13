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
                break;
            case ARRIVED_LIST_HEIGHT:
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

    }
}
