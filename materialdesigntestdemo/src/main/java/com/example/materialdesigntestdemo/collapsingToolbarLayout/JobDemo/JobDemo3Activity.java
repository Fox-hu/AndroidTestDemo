package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
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
    private volatile boolean isScrollStop = true;
    private int headHeight = Utils.dip2px(InitApp.sContext, 1000);
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
        nestedScrollView.setStopListener(new MyNestedScrollView2.StopListener() {
            @Override
            public void onScrollStop() {
                //stop时，OnScrollChangeListener仍在运行 并没有获取点击
                Log.e(TAG, "MyNestedScrollView2 onstop ");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScrollStop = true;
                    }
                }, 1000);
            }
        });

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
            Log.e(TAG, "NestedScrollView onScrollChange ," + " getMaxScrollAmount = " +
                       v.getMaxScrollAmount() + " scrollX = " + scrollX + " scrollY = " + scrollY +
                       " oldScrollX = " + oldScrollX + " oldScrollY = " + oldScrollY);

            if (scrollY - oldscrollY > 0 && !Utils.isVisible(rv) && !isAnimating) {
                isScrollStop = false;
                Log.e(TAG, "ScrollEvent bringToMid");
                bringToMid();
                return;
            }

            if (scrollY - oldscrollY > 0 && isShow && !isAnimating && isScrollStop) {
                isScrollStop = false;
                isShow = false;
                Log.e(TAG, "ScrollEvent onscrollup");
                hideHead();
            } else if (scrollY - oldscrollY < 0 && !isShow && !isAnimating && isScrollStop) {
                isScrollStop = false;
                isShow = true;
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
        isScrollStop = false;
        if (isAnimating) {
            return;
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, 2000);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                isAnimating = value != 2000;
                nestedScrollView.scrollTo(0, value);
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
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, 1500);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                isAnimating = value != 1500;
                nestedScrollView.scrollTo(0, 2000 + value);
            }
        });
        valueAnimator.start();
    }

}
