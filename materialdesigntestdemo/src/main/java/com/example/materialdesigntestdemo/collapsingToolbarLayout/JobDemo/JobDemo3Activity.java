package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.materialdesigntestdemo.R;

import java.util.Arrays;


public class JobDemo3Activity extends AppCompatActivity {
    private static final String TAG = JobDemo3Activity.class.getSimpleName();
    private NestedScrollView nestedScrollView;
    private RecyclerView rv;
    private LinearLayout linearLayout;
    private ImageView head;
    private String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};
    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo3);
        linearLayout = findViewById(R.id.ll_container);
        head = findViewById(R.id.header);

        nestedScrollView = findViewById(R.id.nested_scrollview);
        nestedScrollView.setOnScrollChangeListener(scrollChangeListener);

        rv = findViewById(R.id.rv_content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(new NormalAdapter(Arrays.asList(data)));
    }

    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                int oldScrollY) {
            Log.e(TAG, "low NestedScrollView onScrollChange ," + " getMaxScrollAmount = " +
                       v.getMaxScrollAmount() + " scrollX = " + scrollX + " scrollY = " + scrollY +
                       " oldScrollX = " + oldScrollX + " oldScrollY = " + oldScrollY);
            //基本行为逻辑
            //            if (scrollup()) {
            //                if (header.isvisiable()) {
            //                    if (!body.isvisiable()) {
            //                        body.makeVisiable();
            //                    } else {
            //                        header.goneWithHead();
            //                    }
            //                } else {
            //                    body.scroll();
            //                }
            //            } else {
            //                if (header.isvisiable()) {
            //                    body.scroll();
            //                } else {
            //                    if (scrollY > tY) {
            //                        header.makeVisiable();
            //                    }
            //                }
            //            }
            if (scrollY - oldScrollY > 0 && isShow) {
                isShow = false;
                Log.e(TAG, "onscrollup");
                //                linearLayout.removeView(head);
                head.animate().setDuration(500).translationY(-head.getHeight());
                rv.animate().setDuration(500).translationY(-head.getHeight());
            } else if (scrollY - oldScrollY < 0 && !isShow) {
                Log.e(TAG, "onscrolldown");
                isShow = true;
                //                linearLayout.addView(head, 0);
                head.animate().setDuration(500).translationY(0);
                rv.animate().setDuration(500).translationY(0);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                nestedScrollView.smoothScrollTo(0,2000);
        //            }
        //        },5000);
    }
}
