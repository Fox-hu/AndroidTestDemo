package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.materialdesigntestdemo.R;
import com.example.materialdesigntestdemo.Utils;


public class JobDemo3Activity extends AppCompatActivity {
    private static final String TAG = JobDemo3Activity.class.getSimpleName();
    private NestedScrollView nestedScrollView;
    private RecyclerView rv;
    private LinearLayout linearLayout;
    private ImageView head;
    private boolean isAnimating = false;
    private int oldscrollY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo3);
        linearLayout = findViewById(R.id.ll_container);
        head = findViewById(R.id.header);

        nestedScrollView = findViewById(R.id.nested_scrollview);
        nestedScrollView.setOnScrollChangeListener(scrollChangeListener);

        rv = findViewById(R.id.rv_content);
        Utils.setRv(rv, this);
    }

    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                int oldScrollY) {

            if (isAnimating) {
                return;
            }

            Log.e(TAG,"NestedScrollView onScrollChange ,  scrollY = " + scrollY + " oldScrollY = " +
                    oldScrollY + " preScrolly = " + oldscrollY);

            if (scrollY - oldscrollY > 0) {
                if (0 <= scrollY && scrollY < 2000 && !Utils.isVisible(rv)) {
                    Log.e(TAG, "ScrollEvent bringToMid");
                    bringToMid();
                } else if (2200 < scrollY && scrollY < 3500) {
                    Log.e(TAG, "ScrollEvent hideHead");
                    hideHead();
                }
            }
            oldscrollY = scrollY;
        }

    };

    private void showHead() {

    }

    public void bringToMid() {
        if (isAnimating) {
            return;
        }
        final int scrollY = nestedScrollView.getScrollY();
        final int i = 2000 - scrollY;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, i);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int scrollY1 = nestedScrollView.getScrollY();
                int value = (int) animation.getAnimatedValue();
                Log.e(TAG,
                        "bringToMid , animation value = " + value + " i = " + i + " scrollY1 = " +
                        scrollY1 + " isAnimating = " + isAnimating);
                nestedScrollView.scrollTo(0, scrollY + value);
                isAnimating = value != i;
            }
        });
        valueAnimator.start();
    }

    public void hideHead() {
        if (isAnimating) {
            return;
        }
        final int scrollY = nestedScrollView.getScrollY();
        final int i = 3500 - scrollY;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, i);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                int scrollY1 = nestedScrollView.getScrollY();
                Log.e(TAG, "head hide , animation value = " + value + " i = " + i + " scrollY1 = " +
                           scrollY1 + " isAnimating = " + isAnimating);
                nestedScrollView.scrollTo(0, scrollY + value);
                isAnimating = value != i;
            }
        });
        valueAnimator.start();
    }

}
