package app.mrobot.cn.toutiaoexample.module;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by fox on 2018/2/24.
 */

public abstract class LazyLoadFragment<T extends IBasePresenter> extends BaseFragment<T> {
    private static final String TAG = LazyLoadFragment.class.getSimpleName();

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData();
        }
    }

    protected void prepareFetchData() {
        if (isViewInitiated && isVisibleToUser && (!isDataInitiated)) {
            fetchData();
            isDataInitiated = true;
        }
    }

    /**
     * 懒加载fragment 在fragment可见时才进行数据的加载
     */
    protected abstract void fetchData();
}
