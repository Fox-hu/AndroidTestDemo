package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.materialdesigntestdemo.R;

import java.util.ArrayList;
import java.util.List;


public class SerachAppBarActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private RelativeLayout searchBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BasePagerAdapter mBasePagerAdapter;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    //toolbar的高度 包含tablayout的高度
    float toolbarHeight;

    //toolbar的高度 不包含tablayout的高度
    float searchBarInToolbarHeight;

    //因为toolbar包含tablayout的高度，searchview在滑动过程中计算高度的标准为不包括tablayout的高度
    float searchBarInToolbarScrollDistance;

    //appbar的高度
    float appbarHeight;

    //searchbar的高度
    float searchBarHeight;

    //每移动一像素 searchbar高度变化的像素比值
    float searchBarHeightScale;

    //searchbar移动的距离
    float searchBarScrollDistance;

    //collapsebar滑动的距离
    float appBarScrollDistance;

    //每移动一像素 searchbar位置变化的像素比值
    float searchBarDistanceScale;

    //searchbar上移时 左边偏移的距离
    float searchBarLeftMargin;

    //searchbar初始的左边的距离
    float searchBarLeftMarginDistance;

    //每移动一像素,searchbar左边缩小的距离
    float searchBarLeftDistanceScale;

    FrameLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appbar);

        searchBarInToolbarHeight = getResources().getDimension(R.dimen.search_bar_height_in_toolbar);
        toolbarHeight = getResources().getDimension(R.dimen.tool_bar_height);
        appbarHeight = getResources().getDimension(R.dimen.app_bar_height_360);
        searchBarLeftMargin = getResources().getDimension(R.dimen.search_bar_left_margin);
        appBarScrollDistance = appbarHeight - toolbarHeight;
        searchBarInToolbarScrollDistance = appbarHeight - searchBarInToolbarHeight;

        frameLayout = findViewById(R.id.fl_container);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        searchBar = findViewById(R.id.search_rl);
        appBarLayout = findViewById(R.id.app_bar);
        toolbar = findViewById(R.id.toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpage);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initData();

        setSearchView();
    }

    private void setSearchView() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (searchBarHeight == 0) {
                    searchBarHeight = searchBar.getMeasuredHeight();
                    params = (FrameLayout.LayoutParams) searchBar.getLayoutParams();
                    searchBarScrollDistance = params.topMargin;
                    searchBarLeftMarginDistance = params.leftMargin;
                    searchBarHeightScale = 1.0f - (toolbarHeight / searchBarInToolbarScrollDistance);

                    searchBarDistanceScale = searchBarScrollDistance / appBarScrollDistance;
                    searchBarLeftDistanceScale = searchBarLeftMargin / appBarScrollDistance;
                }
                float searchBarHeightScaleByOffset =
                        1.0f - (searchBarHeightScale * (-verticalOffset / searchBarInToolbarScrollDistance));


                float searchBarMoveDistanceByOffset =
                        searchBarScrollDistance - (-verticalOffset) * searchBarDistanceScale;

                float searchBarLeftMoveDistanceByOffset = searchBarLeftMarginDistance +
                                                          (-verticalOffset) *
                                                          searchBarLeftDistanceScale;

                params.height = (int) (searchBarHeight * searchBarHeightScaleByOffset);
                params.setMargins((int) searchBarLeftMoveDistanceByOffset,
                        (int) searchBarMoveDistanceByOffset, 0, 0);


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
