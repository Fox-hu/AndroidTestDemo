package com.example.materialdesigntestdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.materialdesigntestdemo.R;


public class SearchActivity extends AppCompatActivity {
    private float appbarHeight;      //总高度
    private float toolBarHeight;    //toolBar高度
    private float scrollEnableHeight;     //总高度 -  toolBar高度  布局位移值
    private float searchBarHeight;         //搜索框高度

    private float searchBarHeightScale;     //高度差比值
    private float searchBarMoveDistance;        //距离差
    private float searchBarMoveDistanceScale;   //距离差比值
    private FrameLayout fl;
    private FrameLayout.LayoutParams params;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RelativeLayout searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        appbarHeight = getResources().getDimension(R.dimen.app_bar_height_360);
        toolBarHeight = getResources().getDimension(R.dimen.search_bar_height_in_toolbar);
        scrollEnableHeight = appbarHeight - toolBarHeight;

        fl = findViewById(R.id.fl);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = findViewById(R.id.app_bar);

        searchView = findViewById(R.id.search_rl);

        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //第一次进入获取高度，以及差值 高度差比值
                if (searchBarHeight == 0){
                    searchBarHeight = searchView.getMeasuredHeight();
                    params = (FrameLayout.LayoutParams) searchView.getLayoutParams();

                    //算出高度偏移量比值  相对与llHeight
                    searchBarHeightScale = 1.0f - (toolBarHeight / searchBarHeight);

                    //得到滑动差值 就是布局marginTop
                    searchBarMoveDistance = params.topMargin;
                    //得到滑动比值
                    searchBarMoveDistanceScale = searchBarMoveDistance / scrollEnableHeight;
                }

                //滑动一次 得到渐变缩放值
                float alphaScale = (-verticalOffset) / scrollEnableHeight;

                //获取高度缩放值
                float searchHeightScale = 1.0f-(searchBarHeightScale * (alphaScale));
                //计算maigintop值
                float distance = searchBarMoveDistance - (-verticalOffset) *
                                                         searchBarMoveDistanceScale;

                params.height = (int)(searchBarHeight * searchHeightScale);
                params.setMargins(0,(int)distance,0,0);
                fl.requestLayout();
            }
        });

    }

}
