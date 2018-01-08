package app.mrobot.cn.mvprxjavaretrofit2.presenter;

/**
 * Created by admin on 2018/1/8.
 */

public interface BasePresenter<T> {
    /**
     * 请求之前
     * @param requestCode
     */
    void performRequest(int requestCode);

    /**
     * 请求错误
     * @param errorCOde
     * @param requestCode
     */
    void requestError(int errorCOde,int requestCode);

    /**
     * 请求成功回调
     * @param callBack
     * @param requestCode
     */
    void requestSuccess(T callBack,int requestCode);

    /**
     * @param requestCode
     */
    void requestComplete(int requestCode);
}
