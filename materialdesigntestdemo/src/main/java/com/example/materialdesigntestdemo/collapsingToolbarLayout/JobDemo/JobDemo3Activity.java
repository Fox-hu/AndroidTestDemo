package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.materialdesigntestdemo.R;

import java.util.Arrays;


public class JobDemo3Activity extends AppCompatActivity {
    private static final String TAG = JobDemo3Activity.class.getSimpleName();
    private NestedScrollView nestedScrollView;
    private RecyclerView rv;
    private String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo3);
        nestedScrollView = findViewById(R.id.low_scrollview);
        nestedScrollView.setOnScrollChangeListener(lowListener);

        rv = findViewById(R.id.rv_content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(new NormalAdapter(Arrays.asList(data)));
    }

    private NestedScrollView.OnScrollChangeListener lowListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                int oldScrollY) {
            Log.e(TAG, "low NestedScrollView onScrollChange ," + " getMaxScrollAmount = " +
                       v.getMaxScrollAmount() + " scrollX = " + scrollX + " scrollY = " + scrollY +
                       " oldScrollX = " + oldScrollX + " oldScrollY = " + oldScrollY);
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
