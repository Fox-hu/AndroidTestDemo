package com.example.materialdesigntestdemo.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Created by fox.hu on 2018/6/21.
 */

public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = BasePagerAdapter.class.getSimpleName();
    private List<String> mTitleList;
    private List<Fragment> mFragmentList;

    public BasePagerAdapter(FragmentManager fm, List<String> titleList,
            List<Fragment> fragmentList) {
        super(fm);
        if (titleList.isEmpty() || fragmentList.isEmpty()) {
            throw new IllegalArgumentException("titleList or fragmentList must not null");
        }
        this.mTitleList = titleList;
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitleList.isEmpty() ? 0 : mTitleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

}
