package com.example.materialdesigntestdemo.collapsingToolbarLayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fox.hu on 2018/6/21.
 */

public class TabFragment extends Fragment {
    private static final String TAG = TabFragment.class.getSimpleName();

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
        View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
        String description = getArguments().getString(TAG);
        ((TextView)view.findViewById(android.R.id.text1)).setText(description);
        return view;
    }
}
