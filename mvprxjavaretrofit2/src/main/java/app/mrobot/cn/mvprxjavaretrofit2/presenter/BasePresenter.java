package app.mrobot.cn.mvprxjavaretrofit2.presenter;

/**
 * Created by admin on 2018/1/8.
 */

public interface BasePresenter{
    /**
     * 请求之前
     */
    void performRequest();

    /**
     * 请求错误
     * @param errorMsg
     */
    void requestError(String errorMsg);

    /**
     * 请求成功后的回调
     */
    void requestComplete();
}
