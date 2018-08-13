package app.mrobot.cn.toutiaoexample.module;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;


/**
 * Created by fox on 2018/2/24.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView<T> {
    protected T presenter;

    /**
     * 绑定布局文件
     *
     * @return　布局文件id
     */
    protected abstract int attachLayoutId();

    /**
     * 初始化界面
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    protected void initToolbar(Toolbar toolbar, boolean homeAsUpEnable, String title) {
        ((BaseActivity) getActivity()).initToolbar(toolbar, homeAsUpEnable, title);
    }

    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }
}
