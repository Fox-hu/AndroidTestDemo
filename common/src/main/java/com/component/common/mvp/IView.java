package com.component.common.mvp;

import com.uber.autodispose.AutoDisposeConverter;

public interface IView<T> {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示网络错误
     */
    void showNetError();

    /**
     * 设置presenter
     *
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     *
     * @param <X>
     * @return
     */
    <X> AutoDisposeConverter<X> bindAutoDispose();
}
