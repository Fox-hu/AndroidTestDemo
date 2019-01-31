package com.example.materialdesigntestdemo;

import com.component.common.BaseListActivity;

public class MainActivity extends BaseListActivity {

    @Override
    protected void initItem() {
        activityMap.put("CollapsingNormalActivity", CollapsingNormalActivity.class);
        activityMap.put("ScrollingActivity", ScrollingActivity.class);
        activityMap.put("SearchActivity", SearchActivity.class);
        activityMap.put("ViewDragActivity", ViewDragActivity.class);
        activityMap.put("SerachAppBarActivity", SerachAppBarActivity.class);
        activityMap.put("LagouDemoActivity", LagouDemoActivity.class);
        activityMap.put("JobDemoActivity", JobDemoActivity.class);
        activityMap.put("JobDemo3Activity", JobDemo3Activity.class);
        activityMap.put("WebViewCollapsingTestActivity", WebViewCollapsingTestActivity.class);
    }
}
