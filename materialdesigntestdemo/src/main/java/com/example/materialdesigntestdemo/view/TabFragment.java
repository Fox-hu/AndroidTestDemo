package com.example.materialdesigntestdemo.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.materialdesigntestdemo.R;

/**
 * Created by fox.hu on 2018/6/21.
 */

public class TabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = TabFragment.class.getSimpleName();
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    public static TabFragment get(String description) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, description);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refresh_layout, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onRefresh() {

    }
}
