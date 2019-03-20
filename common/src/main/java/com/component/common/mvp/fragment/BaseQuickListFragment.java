package com.component.common.mvp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.component.common.R;
import com.component.common.mvp.fragment.impl.BaseListImpl;
import com.component.common.rxjava.RxBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * @author fox.hu
 */
public abstract class BaseQuickListFragment<T extends BaseListImpl.Presenter<E>, E> extends
        LazyLoadFragment<T> implements BaseListImpl.ListView<T, E>,
        SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener {
    private static final String TAG = BaseQuickListFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected BaseQuickAdapter adapter;
    protected Observable<Integer> observable;
    protected View errorLayout;
    private int mNextRequestPage = 1;

    @Override
    protected void initView(View layout) {
        recyclerView = layout.findViewById(R.id.content_rv);
        recyclerView.setHasFixedSize(true);

        refreshLayout = layout.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        errorLayout = getErrorLayout();
        errorLayout.setOnClickListener(this);

        initAdapter();
    }



    private void initAdapter() {
        adapter = getAdapter();
        adapter.setLoadMoreView(getCustomLoadMoreView());
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.doLoadMoreData();
            }
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    protected abstract View getErrorLayout();

    protected abstract LoadMoreView getCustomLoadMoreView();

    protected abstract BaseQuickAdapter getAdapter();

    @Override
    protected int attachLayoutId() {
        return R.layout.common_fragment_quick_list;
    }

    @Override
    public void onClick(View v) {
        if(v == errorLayout){
            presenter.doRefresh();
        }
    }

    @Override
    public void onShowNoMore() {
        adapter.loadMoreComplete();
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

    protected abstract int getPageSize();

    @Override
    public void onSetAdapter(final List<E> list) {
        boolean isRefresh = mNextRequestPage == 1;
        mNextRequestPage++;
        final int size = list == null ? 0 : list.size();

        if (isRefresh) {
            adapter.setNewData(list);
        } else {
            if (size > 0) {
                adapter.addData(list);
            }
        }

        if (size < getPageSize()) {
            adapter.loadMoreEnd(isRefresh);
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onShowLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
        adapter.setEnableLoadMore(false);
    }

    @Override
    public void onHideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void onShowNetError() {
        adapter.setEmptyView(errorLayout);
    }

    @Override
    public void onRefresh() {
        final int firstCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView
                .getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        if (firstCompletelyVisibleItemPosition == 0) {
            presenter.doRefresh();
        }
    }

    @Override
    public void onDestroy() {
        RxBus.get().unRegister(TAG, observable);
        super.onDestroy();
    }
}
