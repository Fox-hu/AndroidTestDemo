package com.example.materialdesigntestdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.component.common.utils.Utils;


public class CollapsingNormalActivity extends AppCompatActivity {
    private static final String TAG = CollapsingNormalActivity.class.getSimpleName();

    private ImageView iv;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private NestedScrollView nestedScrollView;
    private RecyclerView recyclerView;

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
        setContentView(R.layout.activity_collapsing_normal);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        nestedScrollView = findViewById(R.id.nested_scrollview);
        iv = findViewById(R.id.iv);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitle("DesignLibrarySample");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        recyclerView = findViewById(R.id.rv_content);
        Utils.setRv(recyclerView,this);

        iv.setImageResource(R.mipmap.ic_bg);
        nestedScrollView.setOnScrollChangeListener(nestedScrollListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
