package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.materialdesigntestdemo.R;


public class JobDemoActivity extends AppCompatActivity {
    private static final String TAG = JobDemoActivity.class.getSimpleName();
    private ObjectAnimator inAnimator;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private MyNestedScrollView nestedScrollView;
    private int preVerticalOffset = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (hasMessages(1)) {
                        return;
                    }
                    appBarLayout.setExpanded(false, true);
                    break;
                default:
                    break;
            }
        }
    };

    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            Log.e(TAG, "verticalOffset = " + verticalOffset + " getTotalScrollRange = " +
                       appBarLayout.getTotalScrollRange() + " preVerticalOffset = " +
                       preVerticalOffset);

            final boolean nestedVisible = isVisible(nestedScrollView);
            Log.d(TAG, " is nestedScrollView visiable ? " + nestedVisible);
            if (up(verticalOffset)) {
                Log.e(TAG, "up");
                if (!nestedVisible) {
                    //如果nestedscorllview不可见则移动到屏幕中央
                    moveNestedToScreenMid();
                }
                Log.e(TAG, "appBarLayout Expanded");
//                handler.sendEmptyMessage(1);
            } else if (down(verticalOffset)) {
                Log.e(TAG, "down");
            } else {
                Log.e(TAG, "preVerticalOffset = verticalOffset");
            }
            preVerticalOffset = verticalOffset;
        }

    };

    private void moveNestedToScreenMid() {
        int[] location = new int[2];
        nestedScrollView.getLocationOnScreen(location);
        int y = location[1];
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int height = outMetrics.heightPixels;
        Log.e(TAG, "y = " + y + "srceen height = " + height);
                final AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)collapsingToolbarLayout.getLayoutParams();
                layoutParams.height = height/2;
                collapsingToolbarLayout.setLayoutParams(layoutParams);

//        int moveHeight = y - height / 2;
//        if (inAnimator == null) {
//            inAnimator = ObjectAnimator.ofFloat(nestedScrollView, "translationY", 0, -moveHeight);
//            inAnimator.setDuration(200);
//        }
//        if (!inAnimator.isRunning()) {
//            inAnimator.start();
//        }

    }

    //    private NestedScrollView.OnScrollChangeListener nestedScrollListener = new NestedScrollView.OnScrollChangeListener() {
    //        @Override
    //        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
    //                int oldScrollY) {
    //            Log.e(TAG, "NestedScrollView onScrollChange ," + "getMaxScrollAmount = " +
    //                       v.getMaxScrollAmount() + "scrollX = " + scrollX + "scrollY = " + scrollY +
    //                       "oldScrollX = " + oldScrollX + "oldScrollY = " + oldScrollY);
    //        }
    //    };

    private MyNestedScrollView.ScrollEventListener listener = new MyNestedScrollView.ScrollEventListener() {
        @Override
        public void onScrollDown() {
        }

        @Override
        public void onScrollUp() {
//            handler.sendEmptyMessage(1);
            appBarLayout.setExpanded(false,true);
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
        nestedScrollView.setScrollEventListener(listener);
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
