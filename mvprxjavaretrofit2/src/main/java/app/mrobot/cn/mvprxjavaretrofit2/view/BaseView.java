package app.mrobot.cn.mvprxjavaretrofit2.view;

/**
 * Created by admin on 2018/1/8.
 */

public interface BaseView<T>{
    /**
     * 显示toast
     * @param message message to show
     * @param requestCode which one to show toast;
     */
    void toast(String message,int requestCode);

    /**
     * 显示进度
     * @param progress 显示请求进度
     * @param requestCode 显示请求标识
     */
    void showProgress(int progress,int requestCode);

    /**
     * 隐藏进度
     * @param requestCode
     */
    void hideProgress(int requestCode);

    /**
     * 显示加载成功
     * @param loadData
     * @param requestCode
     */
    void loadSuccess(T loadData,int requestCode);

    /**
     * 显示加载失败
     * @param errorCode 错误标识
     * @param requestCode 请求标识
     */
    void loadError(int errorCode,int requestCode);
}
