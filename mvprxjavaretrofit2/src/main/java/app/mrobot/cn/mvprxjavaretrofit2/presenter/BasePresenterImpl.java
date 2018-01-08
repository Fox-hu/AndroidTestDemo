package app.mrobot.cn.mvprxjavaretrofit2.presenter;

import app.mrobot.cn.mvprxjavaretrofit2.view.BaseView;

/**
 * Created by admin on 2018/1/8.
 */

public class BasePresenterImpl<T extends BaseView,V> implements BasePresenter<V> {
    BaseView baseView;

    public BasePresenterImpl(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void performRequest(int requestCode) {
        baseView.showProgress(0,requestCode);
    }

    @Override
    public void requestError(int errorCOde, int requestCode) {
        baseView.loadError(errorCOde,requestCode);
    }

    @Override
    public void requestSuccess(V callBack, int requestCode) {
        baseView.loadSuccess(callBack,requestCode);
    }

    @Override
    public void requestComplete(int requestCode) {
        baseView.hideProgress(requestCode);
    }
}
