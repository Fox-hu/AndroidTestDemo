package com.component.common.mvp;

import java.util.List;

public interface IBaseListView extends IView {

    /**
     * 设置适配器
     * @param list
     */
    void setAdapter(List<?> list);

    void showNoMore();
}
