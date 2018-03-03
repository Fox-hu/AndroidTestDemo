package app.mrobot.cn.toutiaoexample.business.news;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.mrobot.cn.toutiaoexample.Constants;
import app.mrobot.cn.toutiaoexample.business.news.article.NewsArticleFragment;
import app.mrobot.cn.toutiaoexample.database.dao.NewsChannelDao;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.adapter.BasePagerAdapter;
import app.mrobot.cn.toutiaoexample.bean.news.NewsChannelBean;

/**
 * Created by admin on 2018/1/24.
 */

public class NewsFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = NewsFragment.class.getSimpleName();
    private NewsChannelDao mNewsChannelDao = new NewsChannelDao();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private Map<String, Fragment> mFragmentMap = new HashMap<>();
    private ViewPager mViewPager;
    private BasePagerAdapter mBasePagerAdapter;
    private LinearLayout mHeadLayout;

    public static NewsFragment get() {
        return SingletonHolder.sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        initTabs();
        mBasePagerAdapter = new BasePagerAdapter(getChildFragmentManager(),
                mTitleList, mFragmentList);
        mViewPager.setAdapter(mBasePagerAdapter);
        mViewPager.setOffscreenPageLimit(15);
    }

    private void initTabs() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mNewsChannelDao.initNewsData();
        List<NewsChannelBean> newsChannelBeanList = mNewsChannelDao.query(
                Constants.NEWS_CHANNEL_ENABLE);

        for (NewsChannelBean bean : newsChannelBeanList) {
            Fragment fragment = null;

            String channelId = bean.getChannelId();

            switch (channelId) {
//                case "essay_joke": {
//                    if (mFragmentMap.containsKey(channelId)) {
//                        mFragmentList.add(mFragmentMap.get(channelId));
//                    } else {
//                        fragment = NewsArticleFragment.get();
//                        mFragmentList.add(fragment);
//                    }
//                }
//                break;

                default:
                    if (mFragmentMap.containsKey(channelId)) {
                        mFragmentList.add(mFragmentMap.get(channelId));
                    } else {
                        fragment = NewsArticleFragment.get(channelId);
                        mFragmentList.add(fragment);
                    }
                    break;
            }

            mTitleList.add(bean.getChannelName());

            if (fragment != null) {
                mFragmentMap.put(bean.getChannelId(), fragment);
            }
        }
        Log.d(TAG,"mFragmentList = " + mFragmentList.toString() + "mTitleList = " + mTitleList.toString());
    }

    private void initView(View view) {
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_news);
        mViewPager = view.findViewById(R.id.view_pager_news);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        view.findViewById(R.id.add_channel_iv).setOnClickListener(this);

        mHeadLayout = view.findViewById(R.id.ll_header);
        mHeadLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_channel_iv: {

            }
            break;
            default:
                break;
        }
    }

    private static class SingletonHolder {
        private static final NewsFragment sInstance = new NewsFragment();
    }
}
