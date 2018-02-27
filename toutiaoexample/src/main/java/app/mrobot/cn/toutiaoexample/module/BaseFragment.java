package app.mrobot.cn.toutiaoexample.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by fox on 2018/2/24.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView<T>{
    protected T presenter;

    /**
     * 绑定布局文件
     * @return　布局文件id
     */
    protected abstract int attachLayoutId();

    /**
     * 初始化界面
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
        View view = inflater.inflate(attachLayoutId(),container,false);
        initData();
        initView(view);
        return view;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
