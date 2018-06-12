package app.mrobot.cn.toutiaoexample.business.video;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.adapter.BasePagerAdapter;

/**
 * Created by Fox on 2018/1/24.
 */

public class VideoFragment extends Fragment {
    public static final String TAG = VideoFragment.class.getSimpleName();

    private String[] mCategoryId = InitApp.sContext.getResources().getStringArray(
            R.array.mobile_video_id);
    private String[] mCategoryName = InitApp.sContext.getResources().getStringArray(
            R.array.mobile_video_name);
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPager mViewPager;
    private BasePagerAdapter mBasePagerAdapter;
    private TabLayout mTabLayout;


    public static VideoFragment get() {
        return SingletonHolder.sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout_video);
        mViewPager = view.findViewById(R.id.view_pager_video);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setOffscreenPageLimit(mCategoryId.length);

    }

    private void initData() {
        for (String categoryId : mCategoryId) {
            mFragmentList.add(VideoArticleFragment.get(categoryId));
        }
        mBasePagerAdapter = new BasePagerAdapter(getChildFragmentManager(),
                Arrays.asList(mCategoryName), mFragmentList);
        mViewPager.setAdapter(mBasePagerAdapter);
    }


    private static class SingletonHolder {
        private static final VideoFragment sInstance = new VideoFragment();
    }
}
