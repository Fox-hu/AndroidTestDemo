package com.component.common.mvp;

/**
 * 这个接口是所有IPresenter的共同父类
 * presenter层表示所有需要主动的行为
 * 下拉刷新
 * 上拉加载
 * 显示网络错误
 * @author fox.hu
 */
public interface IPresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();

    /**
     * 初始请求请求数据
     */
    void doLoadData(String... params);

    /**
     * 上拉加载 可能与初始请求数据一样
     */
    void doLoadMoreData();

    /**
     * 加载完毕
     */
    void doShowNoMore();
}
