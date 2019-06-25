package app.mrobot.cn.drawabletest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import app.mrobot.cn.R;
import app.mrobot.cn.drawabletest.util.Utils;
import app.mrobot.cn.rv_bg.ScollLinearLayoutManager;
interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}

class Request extends Thread {
    private static final int PAGE_SIZE = 6;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {Thread.sleep(500);} catch (InterruptedException e) {}

        if (mPage == 2 && mFirstError) {
            mFirstError = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.fail(new RuntimeException("fail"));
                }
            });
        } else {
            int size = PAGE_SIZE;
            if (mPage == 1) {
                if (mFirstPageNoMore) {
                    size = 1;
                }
                mFirstPageNoMore = !mFirstPageNoMore;
                if (!mFirstError) {
                    mFirstError = true;
                }
            } else if (mPage == 4) {
                size = 1;
            }

            final int dataSize = size;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.success(DataServer.getSampleData(dataSize));
                }
            });
        }
    }
}


public class ExtraDemoActivity extends Activity implements View.OnClickListener {
    private static final String TAG = ExtraDemoActivity.class.getSimpleName();
    private RecyclerView recyclerView1;
    private PullToRefreshAdapter adapter;
    private int mNextRequestPage = 1;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_extra_demo);

        mSwipeRefreshLayout = findViewById(R.id.refresh_layout);
        initRefreshLayout();
        recyclerView1 = findViewById(R.id.rv_1);

        recyclerView1.setLayoutManager(new ScollLinearLayoutManager(this));
        adapter = new PullToRefreshAdapter();
        adapter.setOnLoadMoreListener(() -> loadMore());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Toast.makeText(ExtraDemoActivity.this, "点击了 position = " + position, Toast.LENGTH_LONG).show();
        });
        recyclerView1.setAdapter(adapter);
        recyclerView1.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                recyclerView1.stopScroll();
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                recyclerView1.smoothScrollToPosition(adapter.getData().size());
            }
            return false;
        });
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
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
                recyclerView1.smoothScrollToPosition(data.size());
            }

            @Override
            public void fail(Exception e) {
                Toast.makeText(ExtraDemoActivity.this, R.string.common_empty_network_error, Toast.LENGTH_LONG).show();
                adapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }).start();
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                /**
                 * fix https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/2400
                 */
                boolean isRefresh =mNextRequestPage ==1;
                setData(isRefresh, data);
                recyclerView1.smoothScrollToPosition(adapter.getData().size());
            }

            @Override
            public void fail(Exception e) {
                adapter.loadMoreFail();
                Toast.makeText(ExtraDemoActivity.this, R.string.common_empty_network_error, Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
