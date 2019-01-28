package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.materialdesigntestdemo.R;
import com.example.materialdesigntestdemo.Utils;


public class JobDemoActivity extends AppCompatActivity {
    private static final String TAG = JobDemoActivity.class.getSimpleName();
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private NestedScrollView nestedScrollView;
    private RecyclerView rv;
    private int preVerticalOffset = 0;

    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            final boolean nestedVisible = Utils.isVisible(nestedScrollView);
            //            Log.d(TAG, " is nestedScrollView visiable ? " + nestedVisible);
            if (up(verticalOffset)) {
                Log.e(TAG, "onOffsetChanged up");
                if (!nestedVisible) {
                    //如果nestedscorllview不可见则移动到屏幕中央
                }
                //                appBarLayout.setExpanded(false, true);
            } else if (down(verticalOffset)) {
                Log.e(TAG, "onOffsetChanged down");
            } else {

            }
            preVerticalOffset = verticalOffset;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo);

        coordinatorLayout = findViewById(R.id.coordinator);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.app_bar);
        nestedScrollView = findViewById(R.id.nested_scrollview);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
        nestedScrollView.setOnScrollChangeListener(nestedScrollListener);

        rv = findViewById(R.id.rv_content);
        Utils.setRv(rv, this);

    }

    private NestedScrollView.OnScrollChangeListener nestedScrollListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                int oldScrollY) {
            Log.e(TAG, "nestedScrollListener scrollX = " + scrollX);
        }
    };

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
}
