package com.component.common.mvp.fragment.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fox.hu
 */
public abstract class BaseListPresenterImpl<E> implements BaseListImpl.Presenter<E> {
    private static final String TAG = BaseListPresenterImpl.class.getSimpleName();
    protected BaseListImpl.ListView view;
    protected List<E> dataList = new ArrayList<>();

    public BaseListPresenterImpl(BaseListImpl.ListView view) {
        this.view = view;
    }

    @Override
    public void doSetAdapter(List<E> list) {
        dataList.addAll(list);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }

    @Override
    public void doRefresh() {
        if (dataList.size() != 0) {
            dataList.clear();
        }
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }
}
