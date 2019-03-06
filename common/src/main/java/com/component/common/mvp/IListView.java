package com.component.common.mvp;

import java.util.List;

/**
 * @author fox.hu
 */
public interface IListView<T extends IPresenter,E> extends IView<T> {

    /**
     * 设置适配器
     *
     * @param list
     */
    void onSetAdapter(List<E> list);

    void onShowNoMore();
}
