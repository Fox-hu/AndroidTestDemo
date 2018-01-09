package app.mrobot.cn.mvprxjavaretrofit2.view;

/**
 * Created by admin on 2018/1/8.
 */

public interface BaseView{
    /**
     * 显示toast
     * @param message message to show
     * @param requestCode which one to show toast;
     */
    void toast(String message,int requestCode);

    /**
     * 显示进度
     */
    void showProgress();


    /**
     * 显示加载成功
     */
    void loadSuccess();

    /**
     * 显示加载失败
     * @param errorMsg 错误信息
     */
    void loadError(String errorMsg);
}
