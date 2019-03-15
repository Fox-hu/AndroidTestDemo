package com.example.materialdesigntestdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.materialdesigntestdemo.adapter.BasePagerAdapter;
import com.example.materialdesigntestdemo.view.TabFragment;

import java.util.ArrayList;
import java.util.List;


public class WebViewCollapsingTestActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private RelativeLayout searchBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private WebView webView;
    private ViewPager viewPager;
    private BasePagerAdapter mBasePagerAdapter;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    //toolbar的高度 包含tablayout的高度
    float toolbarHeight;

    //toolbar的高度 不包含tablayout的高度
    float searchBarInToolbarHeight;

    //appbar的高度
    float appbarHeight;

    //searchbar的高度
    float searchBarHeight;

    //searchbar移动的距离
    float searchBarScrollDistance;

    //collapsebar滑动的距离
    float appBarScrollDistance;

    //每移动一像素 searchbar位置变化的像素比值
    float searchBarDistanceScale;


    FrameLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_collapse);

        searchBarInToolbarHeight = getResources().getDimension(
                R.dimen.search_bar_height_in_toolbar);
        toolbarHeight = getResources().getDimension(R.dimen.tool_bar_height);
        appbarHeight = getResources().getDimension(R.dimen.app_bar_height_360);
        appBarScrollDistance = appbarHeight - toolbarHeight;

        frameLayout = findViewById(R.id.fl_container);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        searchBar = findViewById(R.id.search_rl);
        appBarLayout = findViewById(R.id.app_bar);
        toolbar = findViewById(R.id.toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpage);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        webView = findViewById(R.id.webview);
        initWebView();


        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initData();

        setSearchView();
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://api.51job.com/api/job/get_co_hot_jobs.php?format=html&serviceid=28841143&_version=833&_uuid=960ce389827ab1c0dbcdaad62d33c6bb&_partner=a11842aceb47afc4680e983815660402");
    }

    private void setSearchView() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (searchBarHeight == 0) {
                    searchBarHeight = searchBar.getMeasuredHeight();
                    params = (FrameLayout.LayoutParams) searchBar.getLayoutParams();
                    searchBarScrollDistance = params.topMargin;
                    searchBarDistanceScale = searchBarScrollDistance / appBarScrollDistance;

                }
                float searchBarMoveDistanceByOffset =
                        searchBarScrollDistance - (-verticalOffset) * searchBarDistanceScale;

                params.setMargins(0, (int) searchBarMoveDistanceByOffset, 0, 0);
                frameLayout.requestLayout();
            }
        });
    }

    private void initData() {
        initTabs();
        mBasePagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), mTitleList,
                mFragmentList);
        viewPager.setAdapter(mBasePagerAdapter);
        viewPager.setOffscreenPageLimit(15);
    }

    private void initTabs() {
        mTitleList.add("tab1");
        mTitleList.add("tab2");
        mFragmentList.add(TabFragment.get("tab1"));
        mFragmentList.add(TabFragment.get("tab2"));
    }
}
