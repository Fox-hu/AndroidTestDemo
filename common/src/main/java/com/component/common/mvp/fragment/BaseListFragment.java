package com.component.common.mvp.fragment;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.component.common.R;
import com.component.common.mvp.fragment.bean.LoadingBean;
import com.component.common.mvp.fragment.bean.LoadingEndBean;
import com.component.common.mvp.fragment.bean.LoadingEndViewBinder;
import com.component.common.mvp.fragment.bean.LoadingViewBinder;
import com.component.common.mvp.fragment.impl.BaseListImpl;
import com.component.common.recycleview.DiffCallback;
import com.component.common.recycleview.OnLoadMoreListener;
import com.component.common.rxjava.RxBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


/**
 * @author fox.hu
 */
public abstract class BaseListFragment<T extends BaseListImpl.Presenter<E>, E> extends
        LazyLoadFragment<T> implements BaseListImpl.ListView<T, E>,
        SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener {
    private static final String TAG = BaseListFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    protected Observable<Integer> observable;
    protected LinearLayout errorLayout;

    //标记是否能够加载更多
    protected boolean canLoadMore = false;

    @Override
    protected void initView(View layout) {
        recyclerView = layout.findViewById(R.id.content_rv);
        recyclerView.setHasFixedSize(true);

        refreshLayout = layout.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        errorLayout = layout.findViewById(R.id.ll_error);
        errorLayout.setOnClickListener(this);

        adapter = new MultiTypeAdapter(oldItems);

        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
        registerMultiType(adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoadMore() {
                if (canLoadMore) {
                    presenter.doLoadMoreData();
                    canLoadMore = false;
                }
            }
        });
    }

    /**
     * 注册各种type的item
     */
    protected abstract void registerMultiType(MultiTypeAdapter adapter);

    @Override
    protected int attachLayoutId() {
        return R.layout.common_fragment_list;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.ll_error) {
            onShowLoading();
            errorLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public void onShowNoMore() {
        final Activity activity = getActivity();
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
        canLoadMore = false;
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
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onSetAdapter(final List<E> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        /**
         * https://medium.com/@hanru.yeh/recyclerview-and-appbarlayout-behavior-changed-in-v26-0-x-d9eb4de78fc0
         * support libraries v26 增加了 RV 惯性滑动，当 root layout 使用了 AppBarLayout Behavior 就会自动生效
         * 因此需要手动停止滑动
         */
        recyclerView.stopScroll();
    }

    @Override
    public void onShowLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onHideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onShowNetError() {
        errorLayout.setVisibility(View.VISIBLE);
        canLoadMore = false;
    }

    @Override
    public void onRefresh() {
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
