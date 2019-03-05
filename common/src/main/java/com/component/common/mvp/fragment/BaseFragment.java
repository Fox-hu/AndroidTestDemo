package com.component.common.mvp.fragment;


import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.component.common.mvp.IPresenter;
import com.component.common.mvp.IView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @author fox.hu
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView<T> {
    private static final String TAG = BaseFragment.class.getSimpleName();

    protected T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(attachLayoutId(), container, false);
        initData();
        initView(layout);
        return layout;
    }

    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }

    /**
     * 初始化数据，在初始化界面之前
     */
    protected abstract void initData();

    /**
     * 初始化界面，在初始化数据之后
     * @param layout 界面布局
     */
    protected abstract void initView(View layout);


    /**
     * 返回布局id
     * @return 布局id
     */
    protected abstract int attachLayoutId();
}
