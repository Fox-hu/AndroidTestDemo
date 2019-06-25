package app.mrobot.cn.drawabletest;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import app.mrobot.cn.R;
import app.mrobot.cn.rv_bg.ScrollLinearLayoutManager;

public class ScrollGroup {
    private Context context;
    private RecyclerView recyclerView;
    private BaseQuickAdapter adapter;
    private int mNextRequestPage = 1;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ScrollGroup(RecyclerView recyclerView, BaseQuickAdapter adapter,
            SwipeRefreshLayout mSwipeRefreshLayout) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.context = recyclerView.getContext();
    }

    public void init() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
        recyclerView.setLayoutManager(new ScrollLinearLayoutManager(context));
        adapter = new AbnormalAdapter();
        adapter.setOnLoadMoreListener(() -> loadMore());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Toast.makeText(context, "点击了 position = " + position, Toast.LENGTH_LONG).show();
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                recyclerView.stopScroll();
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                recyclerView.smoothScrollToPosition(adapter.getData().size());
            }
            return false;
        });
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }


    private void refresh() {
        mNextRequestPage = 1;
        adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(true, data);
                adapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
                recyclerView.smoothScrollToPosition(data.size());
            }

            @Override
            public void fail(Exception e) {
                Toast.makeText(context, R.string.common_empty_network_error, Toast.LENGTH_LONG)
                        .show();
                adapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }).start();
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            adapter.setNewData(data);
        } else {
            if (size > 0) {
                adapter.addData(data);
            }
        }
        if (size < 6) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
            Toast.makeText(context, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            adapter.loadMoreComplete();
        }
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                boolean isRefresh = mNextRequestPage == 1;
                setData(isRefresh, data);
                recyclerView.smoothScrollToPosition(adapter.getData().size());
            }

            @Override
            public void fail(Exception e) {
                adapter.loadMoreFail();
                Toast.makeText(context, R.string.common_empty_network_error, Toast.LENGTH_LONG)
                        .show();
            }
        }).start();
    }
}
