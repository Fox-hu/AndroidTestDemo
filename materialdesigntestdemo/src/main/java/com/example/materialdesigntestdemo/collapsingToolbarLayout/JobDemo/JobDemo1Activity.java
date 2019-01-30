package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.materialdesigntestdemo.R;
import com.component.common.utils.Utils;


public class JobDemo1Activity extends AppCompatActivity {
    private static final String TAG = JobDemo1Activity.class.getSimpleName();
    private MyNestedScrollView1 head;
    private LinearLayout body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo1);
        head = findViewById(R.id.rel_head);
        body = findViewById(R.id.rel_body);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HideShowEvent.INSTANCE.setChild(head);
        HideShowEvent.INSTANCE.setTarget(body);
        head.setScrollEventListener(listener);
        HideShowEvent.INSTANCE.setChildHeight(Utils.dip2px(this, 200));

//        ViewGroup.LayoutParams headLayoutParams = head.getLayoutParams();
//        headLayoutParams.height = Utils.dip2px(this, 1000);
//        head.setLayoutParams(headLayoutParams);
//        Utils.setTopMargins(body, Utils.dip2px(this, 1000));
//        HideShowEvent.INSTANCE.setChildHeight(Utils.dip2px(this, 1000));
//        head.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
//                    int oldScrollY) {
//
//            }
//        });
    }

    private HideShowEvent.ScrollEventListener listener = new HideShowEvent.ScrollEventListener() {
        @Override
        public void onScrollDown() {
            Log.e(TAG, "onScrollDown");
        }

        @Override
        public void onScrollUp() {
            Log.e(TAG, "onScrollUp");
        }
    };
}
