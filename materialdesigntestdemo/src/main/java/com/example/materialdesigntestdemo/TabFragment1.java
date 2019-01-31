package com.example.materialdesigntestdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fox.hu on 2018/6/21.
 */

public class TabFragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = TabFragment1.class.getSimpleName();
    private RecyclerView recyclerView;

    public static TabFragment1 get(String description) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, description);
        TabFragment1 tabFragment = new TabFragment1();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spring_layout, container, false);
        recyclerView = view.findViewById(R.id.rv_content);
        return view;
    }

    @Override
    public void onRefresh() {

    }
}
