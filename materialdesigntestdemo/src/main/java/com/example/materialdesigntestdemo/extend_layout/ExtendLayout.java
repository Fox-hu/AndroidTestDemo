package com.example.materialdesigntestdemo.extend_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class ExtendLayout extends FrameLayout implements IExtendLayout {

    private static final String TAG = ExtendLayout.class.getSimpleName();

    private State currentState = State.NONE;

    public ExtendLayout(Context context) {
        this(context, null);
    }


    public ExtendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ExtendLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View container = createLoadingView(context, attrs);
        if (null == container) {
            throw new NullPointerException("Loading view can not be null.");
        }
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        addView(container, layoutParams);
        bindView(container);
    }

    protected abstract void bindView(View container);

    protected abstract View createLoadingView(Context context, AttributeSet attrs);

    @Override
    public void setState(State state) {
        if (currentState != state) {
            currentState = state;
            notifyStateChange(state);
        }
    }

    protected abstract void notifyStateChange(State state);

    @Override
    public State getState() {
        return currentState;
    }
}
