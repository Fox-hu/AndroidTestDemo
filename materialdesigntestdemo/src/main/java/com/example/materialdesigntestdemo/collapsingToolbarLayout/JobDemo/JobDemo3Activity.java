package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.materialdesigntestdemo.InitApp;
import com.example.materialdesigntestdemo.R;
import com.example.materialdesigntestdemo.Utils;


public class JobDemo3Activity extends AppCompatActivity {
    private static final String TAG = JobDemo3Activity.class.getSimpleName();
    private MyNestedScrollView2 nestedScrollView;
    private RecyclerView rv;
    private LinearLayout linearLayout;
    private ImageView head;
    private boolean isShow = true;
    private int headHeight = Utils.dip2px(InitApp.sContext, 1000);
    private boolean isAnimating = false;
    private boolean isFingerUp = false;
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
            Log.e(TAG,
                    "NestedScrollView onScrollChange ,  scrollY = " + scrollY + " oldScrollY = " +
                    oldScrollY + " preScrolly = " + oldscrollY);

            //注意 y值可能会超过head的大小
            if (scrollY - oldscrollY > 0 && !Utils.isVisible(rv) && !isAnimating) {
                Log.e(TAG, "ScrollEvent bringToMid");
                bringToMid();
                oldscrollY = scrollY;
                return;
            }

            if (2000 + 200 < scrollY && scrollY < 3500 && scrollY - oldscrollY > 0 &&
                !isAnimating) {
                Log.e(TAG, "ScrollEvent onscrollup");
                hideHead();
            }

            if (oldscrollY <= 3500 && scrollY - oldscrollY < 0  && !isAnimating) {
                Log.e(TAG, "ScrollEvent onscrolldown");
                showHead();
            }
            oldscrollY = scrollY;
        }

    };

    private void showHead() {
        //        nestedScrollView.scrollTo(0, -headHeight);
        //TODO 微信效果
        //        head.animate().setDuration(500).translationY(0);
        //        rv.animate().setDuration(500).translationY(0);

        //动画效果
        //        if (isAnimating) {
        //            return;
        //        }
        //        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) head.getLayoutParams();
        //        ValueAnimator valueAnimator = new ValueAnimator();
        //        valueAnimator.setIntValues(0, headHeight);
        //        valueAnimator.setDuration(500);
        //        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        //            @Override
        //            public void onAnimationUpdate(ValueAnimator animation) {
        //                if (head.getBottom() < headHeight) {
        //                    int value = (int) animation.getAnimatedValue();
        //                    Log.e(TAG, "show getAnimatedValue = " + value + " head.getBottom() = " +
        //                               head.getBottom() + " head.getTop()" + head.getTop());
        //                    isAnimating = value != headHeight;
        //                    params.height = value;
        //                    head.setLayoutParams(params);
        //                }
        //            }
        //        });
        //        valueAnimator.start();
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
                int value = (int) animation.getAnimatedValue();
                Log.e(TAG, "bringToMid , animation value = " + value + " i = " + i);
                nestedScrollView.scrollTo(0, scrollY + value);
                isAnimating = value != i;
            }
        });
        valueAnimator.start();
    }

    public void hideHead() {
        //todo 将父布局的底部提上去 要不nestedScrollView.scrollTo(0, 2000)会漏出底部一块内容
        //        head.animate().setDuration(500).translationY(-head.getHeight());
        //        rv.animate().setDuration(500).translationY(-head.getHeight());

        //        if (isAnimating) {
        //            return;
        //        }
        //        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) head.getLayoutParams();
        //        ValueAnimator valueAnimator = new ValueAnimator();
        //        valueAnimator.setIntValues(0, headHeight);
        //        valueAnimator.setDuration(500);
        //        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        //            @Override
        //            public void onAnimationUpdate(ValueAnimator animation) {
        //                if (head.getBottom() > 0) {
        //                    int value = (int) animation.getAnimatedValue();
        //                    Log.e(TAG, "hide getAnimatedValue = " + value + " head.getBottom() = " +
        //                               head.getBottom() + " head.getTop()" + head.getTop());
        //                    isAnimating = value != headHeight;
        //                    params.height = headHeight - value;
        //                    head.setLayoutParams(params);
        //                }
        //            }
        //        });
        //        valueAnimator.start();

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
                Log.e(TAG, "head hide , animation value = " + value + " i = " + i);
                nestedScrollView.scrollTo(0, scrollY + value);
                isAnimating = value != i;
            }
        });
        valueAnimator.start();
    }

}
