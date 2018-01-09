package app.mrobot.cn.mvprxjavaretrofit2.presenter;

import app.mrobot.cn.mvprxjavaretrofit2.view.BaseView;

/**
 * Created by admin on 2018/1/8.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter {
    BaseView baseView;

    public BasePresenterImpl(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void performRequest() {
        baseView.showProgress();
    }

    @Override
    public void requestError(String errorMsg) {
        baseView.loadError(errorMsg);
    }

    @Override
    public void requestComplete() {
        baseView.loadSuccess();
    }
}
