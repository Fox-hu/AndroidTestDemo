package com.component.common.mvp;

import com.uber.autodispose.AutoDisposeConverter;

/**
 * 这个接口是所有view层的共同父类
 * 表示所有view可能呈现的页面情况
 * 显示加载动画
 * 隐藏加载动画
 * 网络错误
 * 同时 一般都是activity或者fragment实现此view rxjava的生命周期绑定方法也放在此处
 * @author fox.hu
 */
public interface IView<T> {

    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载动画
     */
    void onHideLoading();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置presenter
     *
     * @return  presenter
     */
    T initPresenter();

    /**
     * 绑定生命周期
     *
     * @param <X>
     * @return
     */
    <X> AutoDisposeConverter<X> bindAutoDispose();
}
