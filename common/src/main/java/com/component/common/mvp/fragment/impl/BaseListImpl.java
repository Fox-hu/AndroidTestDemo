package com.component.common.mvp.fragment.impl;

import com.component.common.mvp.IListView;
import com.component.common.mvp.IPresenter;

import java.util.List;

public interface BaseListImpl {
    interface ListView<T extends Presenter, E> extends IListView<T, E> {

    }

    interface Presenter<E> extends IPresenter {
        void doSetAdapter(List<E> list);
    }
}
