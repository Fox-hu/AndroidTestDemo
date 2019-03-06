package com.component.common.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.component.common.mvp.IPresenter;

/**
 * @author fox.hu
 */
public abstract class LazyLoadFragment<T extends IPresenter> extends BaseFragment<T> {
    private static final String TAG = LazyLoadFragment.class.getSimpleName();

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareLoad();
        }
    }

    private void prepareLoad() {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated)) {
            fetchData();
            isDataInitiated = true;
        }
    }

    /**
     * 加载数据
     */
    protected abstract void fetchData();
}
