package com.example.materialdesigntestdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.materialdesigntestdemo.adapter.BasePagerAdapter;
import com.example.materialdesigntestdemo.view.TabFragment1;

import java.util.ArrayList;
import java.util.List;


public class LagouDemoActivity extends AppCompatActivity {
    private static final String TAG = LagouDemoActivity.class.getSimpleName();

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BasePagerAdapter mBasePagerAdapter;
    private AppBarLayout appBarLayout;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private int preVerticalOffset = 0;
    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            Log.e(TAG, "verticalOffset = " + verticalOffset + " getTotalScrollRange = " +
                       appBarLayout.getTotalScrollRange() + " preVerticalOffset = " +
                       preVerticalOffset);
            if (up(verticalOffset)) {
                Log.e(TAG, "up");

            } else if (down(verticalOffset)) {
                Log.e(TAG, "down");
            } else {
                Log.e(TAG, "preVerticalOffset = verticalOffset");
            }
            preVerticalOffset = verticalOffset;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagou_demo);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.app_bar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpage);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
        initData();
    }

    private boolean up(int verticalOffset) {
        return verticalOffset < preVerticalOffset;
    }

    private boolean down(int verticalOffset) {
        return verticalOffset > preVerticalOffset;
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
        mFragmentList.add(TabFragment1.get("tab1"));
        mFragmentList.add(TabFragment1.get("tab2"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_searchview, menu);
        MenuItem item = menu.findItem(R.id.backUp);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
