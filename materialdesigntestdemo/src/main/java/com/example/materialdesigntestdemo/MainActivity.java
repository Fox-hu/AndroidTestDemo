package com.example.materialdesigntestdemo;

import com.component.common.BaseListActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.CollapsingNormalActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo.JobDemo1Activity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo.JobDemo3Activity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo.JobDemoActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.LagouDemoActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.ScrollingActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.SearchActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.SerachAppBarActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.WebViewCollapsingTestActivity;


public class MainActivity extends BaseListActivity {

    @Override
    protected void initItem() {
        activityMap.put("CollapsingNormalActivity", CollapsingNormalActivity.class);
        activityMap.put("ScrollingActivity", ScrollingActivity.class);
        activityMap.put("SearchActivity", SearchActivity.class);
        activityMap.put("SerachAppBarActivity", SerachAppBarActivity.class);
        activityMap.put("LagouDemoActivity", LagouDemoActivity.class);
        activityMap.put("JobDemoActivity", JobDemoActivity.class);
        activityMap.put("JobDemo1Activity", JobDemo1Activity.class);
        activityMap.put("JobDemo3Activity", JobDemo3Activity.class);
        activityMap.put("WebViewCollapsingTestActivity", WebViewCollapsingTestActivity.class);
    }
}
