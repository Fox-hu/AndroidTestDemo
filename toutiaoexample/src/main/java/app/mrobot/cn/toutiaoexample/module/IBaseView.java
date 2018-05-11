package app.mrobot.cn.toutiaoexample.module;


import com.uber.autodispose.AutoDisposeConverter;

/**
 * Created by fox on 2018/2/24.
 */

public interface IBaseView<T> {
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
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     * @param <X>
     * @return
     */
    <X> AutoDisposeConverter<X> bindAutoDispose();
}
