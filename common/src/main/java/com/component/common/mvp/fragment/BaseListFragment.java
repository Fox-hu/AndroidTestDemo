package com.component.common.mvp.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.component.common.R;
import com.component.common.mvp.IBaseListView;
import com.component.common.mvp.fragment.bean.LoadingEndBean;
import com.component.common.rxjava.RxBus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


/**
 * @author fox.hu
 */
public abstract class BaseListFragment extends LazyLoadFragment implements IBaseListView,
        OnRefreshListener,
        View.OnClickListener {
    private static final String TAG = BaseListFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected SmartRefreshLayout refreshLayout;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    protected Observable<Integer> observable;
    protected LinearLayout errorLayout;

    @Override
    protected void initView(View layout) {
        recyclerView = layout.findViewById(R.id.content_rv);
        recyclerView.setHasFixedSize(true);

        refreshLayout = layout.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        errorLayout = layout.findViewById(R.id.ll_error);
        errorLayout.setOnClickListener(this);
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.common_fragment_list;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.ll_error) {
            showLoading();
            errorLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public void showNoMore() {
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    oldItems.add(new LoadingEndBean());
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    protected void fetchData() {
        observable = RxBus.get().register(TAG);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh();
            }
        });
    }

    @Override
    public void hideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    public void showNetError() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        final int firstCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView
                .getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        if (firstCompletelyVisibleItemPosition == 0) {
            presenter.doRefresh();
            return;
        }
        recyclerView.scrollToPosition(5);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroy() {
        RxBus.get().unRegister(TAG, observable);
        super.onDestroy();
    }
}
