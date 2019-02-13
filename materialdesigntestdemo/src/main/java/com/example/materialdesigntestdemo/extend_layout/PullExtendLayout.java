package com.example.materialdesigntestdemo.extend_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

public class PullExtendLayout extends LinearLayout {
    private static final String TAG = PullExtendLayout.class.getSimpleName();
    /**
     * 移动点的保护范围值
     */
    private int mTouchSlop;

    private int headerHeight;

    private int headerListHeight;

    private float lastMotionY = -1;

    /**
     * 阻尼系数
     */
    private float offsetRadio = 1.0f;

    private boolean isHandleTouchEvent = false;

    private SmoothScrollRunnable smoothScrollRunnable;

    private ExtendLayout headerLayout;

    private View mRefreshableView;

    /**
     * 表示是否消费了touch事件，如果是，则不调用父类的onTouchEvent方法
     */
    private boolean mIsHandledTouchEvent = false;

    public PullExtendLayout(Context context) {
        this(context, null);
    }


    public PullExtendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public PullExtendLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 2) {
            if (getChildAt(0) instanceof ExtendLayout) {
                headerLayout = (ExtendLayout) getChildAt(0);
                mRefreshableView = getChildAt(1);
            } else {
                mRefreshableView = getChildAt(0);
            }
        }
        if (mRefreshableView == null) {
            throw new IllegalStateException("布局异常，一定要有内容布局");
        }
        init(getContext());
    }

    private void init(Context context) {
        mTouchSlop = (int) (ViewConfiguration.get(context).getScaledTouchSlop() * 1.5);
        ViewGroup.LayoutParams layoutParams = mRefreshableView.getLayoutParams();
        layoutParams.height = 10;
        mRefreshableView.setLayoutParams(layoutParams);
        getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        refreshLoadingViewSize();
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    private void refreshLoadingViewSize() {
        headerHeight = null != headerLayout ? headerLayout.getContentSize() : 0;
        headerListHeight = null != headerLayout ? headerLayout.getListSize() : 0;

        int headerHeight = (null != headerLayout) ? headerLayout.getMeasuredHeight() : 0;

        int paddingLeft = getPaddingLeft();
        int paddingTop = -headerHeight;
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refreshLoadingViewSize();
        refreshRefreshableViewSize(w, h);
        post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
            }
        });
    }

    private void refreshRefreshableViewSize(int w, int h) {
        ViewGroup.LayoutParams layoutParams = mRefreshableView.getLayoutParams();
        if (layoutParams.height != h) {
            layoutParams.height = h;
            mRefreshableView.requestLayout();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if (action != MotionEvent.ACTION_DOWN && mIsHandledTouchEvent) {
            Log.e(TAG, " onInterceptTouchEvent not down");
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastMotionY = ev.getY();
                mIsHandledTouchEvent = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getY() - lastMotionY;
                final float absDiff = Math.abs(deltaY);
                if (absDiff > mTouchSlop) {
                    lastMotionY = ev.getY();
                    mIsHandledTouchEvent = (Math.abs(getScrollY()) > 0 || deltaY > 0.5f ||
                                            deltaY < -0.5f);
                    if (mIsHandledTouchEvent) {
                        mRefreshableView.onTouchEvent(ev);
                    }
                }
                break;
            default:
                break;
        }
        Log.e(TAG, " onInterceptTouchEvent " + mIsHandledTouchEvent);
        return mIsHandledTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastMotionY = event.getY();
                mIsHandledTouchEvent = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = event.getY() - lastMotionY;
                lastMotionY = event.getY();
                if (isReadyForPullDown(deltaY)) {
                    pullHeaderLayout(deltaY / offsetRadio);
                    handled = true;
                } else if (isReadyForPullUp(deltaY)) {
                    handled = true;
                } else {
                    mIsHandledTouchEvent = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mIsHandledTouchEvent) {
                    mIsHandledTouchEvent = false;
                    if (isReadyForPullDown(0)) {
                        resetHeaderLayout();
                    }
                }
                break;

            default:
                break;
        }
        Log.e(TAG, " onTouchEvent , action = " + event.getAction() + " handled = " + handled);
        return handled;
    }

    private boolean isReadyForPullDown(float deltaY) {
        return getScrollY() < 0 || (getScrollY() == 0 && deltaY > 0);
    }

    /**
     * 判断刷新的View是否滑动到底
     *
     * @return true表示已经滑动到底部，否则false
     */
    protected boolean isReadyForPullUp(float deltaY) {
        return getScrollY() > 0 || (getScrollY() == 0 && deltaY < 0);
    }

    private void pullHeaderLayout(float delta) {
        Log.e(TAG, " pullHeaderLayout , delta = " + delta + " getScrollY = " + getScrollY());
        // 向上滑动，并且当前scrollY为0时，不滑动
        int oldScrollY = getScrollY();
        if (delta < 0 && (oldScrollY - delta) >= 0) {
            Log.e(TAG, " 我也不知道这个是啥意思");
            scrollTo(0, 0);
            if (null != headerLayout && 0 != headerHeight) {
                headerLayout.setState(IExtendLayout.State.RESET);
                headerLayout.onPull(0);
            }
            return;
        }
        scrollBy(0, -(int) delta);
        final int absScrollY = Math.abs(getScrollY());
        if (headerLayout != null && headerHeight != 0) {
            if (absScrollY >= headerListHeight) {
                headerLayout.setState(IExtendLayout.State.ARRIVED_LIST_HEIGHT);
                offsetRadio = 2.0f;
            } else {
                offsetRadio = 1.0f;
            }
            headerLayout.onPull(absScrollY);
        }
    }

    private void resetHeaderLayout() {
        final int absScrollY = Math.abs(getScrollY());
        if (absScrollY < headerHeight) {
            smoothScrollTo(0);
        } else if (absScrollY >= headerHeight) {
            smoothScrollTo(-headerListHeight);
        }
    }

    private void smoothScrollTo(int newScrollValue) {
        if (null != smoothScrollRunnable) {
            smoothScrollRunnable.stop();
        }

        final int scrollY = getScrollY();
        //如果未滑动到位 继续滑动
        boolean post = (scrollY != newScrollValue);
        if (post) {
            smoothScrollRunnable = new SmoothScrollRunnable(scrollY, newScrollValue, 200);
            post(smoothScrollRunnable);
        }
    }

    final class SmoothScrollRunnable implements Runnable {
        /**
         * 动画效果
         */
        private final Interpolator mInterpolator;
        /**
         * 结束Y
         */
        private final int mScrollToY;
        /**
         * 开始Y
         */
        private final int mScrollFromY;
        /**
         * 滑动时间
         */
        private final long mDuration;
        /**
         * 是否继续运行
         */
        private boolean mContinueRunning = true;
        /**
         * 开始时刻
         */
        private long mStartTime = -1;
        /**
         * 当前Y
         */
        private int mCurrentY = -1;

        /**
         * 构造方法
         *
         * @param fromY    开始Y
         * @param toY      结束Y
         * @param duration 动画时间
         */
        public SmoothScrollRunnable(int fromY, int toY, long duration) {
            mScrollFromY = fromY;
            mScrollToY = toY;
            mDuration = duration;
            mInterpolator = new DecelerateInterpolator();
        }

        @Override
        public void run() {
            if (mDuration <= 0) {
                scrollTo(0, mScrollToY);
                return;
            }

            if (mStartTime == -1) {
                mStartTime = System.currentTimeMillis();
            } else {

                final long oneSecond = 1000;
                long normalizedTime =
                        (oneSecond * (System.currentTimeMillis() - mStartTime)) / mDuration;
                normalizedTime = Math.max(Math.min(normalizedTime, oneSecond), 0);

                final int deltaY = Math.round((mScrollFromY - mScrollToY) * mInterpolator
                        .getInterpolation(normalizedTime / (float) oneSecond));
                mCurrentY = mScrollFromY - deltaY;
                scrollTo(0, mCurrentY);

                if (null != headerLayout && 0 != headerHeight) {
                    headerLayout.onPull(Math.abs(mCurrentY));
                    if (mCurrentY == 0) {
                        headerLayout.setState(IExtendLayout.State.RESET);
                    }
                    if (Math.abs(mCurrentY) == headerListHeight) {
                        headerLayout.setState(IExtendLayout.State.ARRIVED_LIST_HEIGHT);
                    }
                }
            }

            if (mContinueRunning && mScrollToY != mCurrentY) {
                PullExtendLayout.this.postDelayed(this, 16);
            }
        }

        /**
         * 停止滑动
         */
        public void stop() {
            mContinueRunning = false;
            removeCallbacks(this);
        }
    }
}
