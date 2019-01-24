package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.materialdesigntestdemo.R;


public class JobDemoActivity extends AppCompatActivity {
    private static final String TAG = JobDemoActivity.class.getSimpleName();

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private NestedScrollView nestedScrollView;
    private int preVerticalOffset = 0;
    private SimpleDetector detector;

    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            Log.e(TAG, "verticalOffset = " + verticalOffset + " getTotalScrollRange = " +
                       appBarLayout.getTotalScrollRange() + " preVerticalOffset = " +
                       preVerticalOffset);
            final boolean visiable = isVisible(nestedScrollView);
            Log.d(TAG, " is nestedScrollView visiable ? " + visiable);
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

    private NestedScrollView.OnScrollChangeListener nestedScrollListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                int oldScrollY) {
            Log.e(TAG, "NestedScrollView onScrollChange ," + "getMaxScrollAmount = " +
                       v.getMaxScrollAmount() + "scrollX = " + scrollX + "scrollY = " + scrollY +
                       "oldScrollX = " + oldScrollX + "oldScrollY = " + oldScrollY);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo);


        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.app_bar);
        nestedScrollView = findViewById(R.id.nested_scrollview);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
        //        nestedScrollView.setOnScrollChangeListener(nestedScrollListener);
        detector = new SimpleDetector(this) {
            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrollUp() {
                appBarLayout.setExpanded(false, true);
            }
        };
        nestedScrollView.setOnTouchListener(detector);
    }

    private boolean up(int verticalOffset) {
        return verticalOffset < preVerticalOffset;
    }

    private boolean down(int verticalOffset) {
        return verticalOffset > preVerticalOffset;
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

    private boolean isVisible(View view) {
        Rect rect = new Rect();
        return view.getGlobalVisibleRect(rect);
    }
}
