package app.mrobot.cn.toutiaoexample;


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
import java.util.List;

/**
 * Created by admin on 2018/1/24.
 */

public class NewsFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = NewsFragment.class.getSimpleName();
    private NewsChannelDao mNewsChannelDao = new NewsChannelDao();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

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
    }

    private void initTabs() {
    }

    private void initView(View view) {
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_news);
        ViewPager viewPager = view.findViewById(R.id.view_pager_news);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        view.findViewById(R.id.add_channel_iv).setOnClickListener(this);
        
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
