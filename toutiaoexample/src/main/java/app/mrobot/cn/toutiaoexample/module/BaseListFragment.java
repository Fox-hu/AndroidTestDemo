package app.mrobot.cn.toutiaoexample.module;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.LoadingEndBean;
import app.mrobot.cn.toutiaoexample.utils.RxBus;
import io.reactivex.Observable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by fox on 2018/2/24.
 */

public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements
        IBaseListView<T>,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = BaseListFragment.class.getSimpleName();
    private static final int INIT_POSITION = 0;
    private static final int DEFAULT_SCROLL_POSITION = 5;

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected MultiTypeAdapter mAdapter;
    protected Items mOldItems = new Items();
    protected boolean canLoadMore;
    protected Observable<Integer> mObservable;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = view.findViewById(R.id.content_rv);
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onShowLoading() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void onHideLoading() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void onShowNetError() {
        Toast.makeText(getActivity(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        getActivity().runOnUiThread(() -> {
            mAdapter.setItems(new Items());
            mAdapter.notifyDataSetChanged();
            canLoadMore = false;
        });
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(() -> {
            if (mOldItems.size() > 0) {
                Items newItems = new Items(mOldItems);
                newItems.remove(newItems.size() - 1);
                newItems.add(new LoadingEndBean());
                mAdapter.setItems(newItems);
                mAdapter.notifyDataSetChanged();
            } else if (mOldItems.size() == 0) {
                mOldItems.add(new LoadingEndBean());
                mAdapter.setItems(mOldItems);
                mAdapter.notifyDataSetChanged();
            }
            canLoadMore = false;
        });
    }

    @Override
    public void onRefresh() {
        int firstVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == INIT_POSITION) {
            presenter.doRefresh();
            return;
        }
        mRecyclerView.scrollToPosition(DEFAULT_SCROLL_POSITION);
        mRecyclerView.smoothScrollToPosition(INIT_POSITION);
    }

    @Override
    protected void fetchData() {
        mObservable = RxBus.get().register(TAG);
        mObservable.subscribe(integer -> mAdapter.notifyDataSetChanged());
    }

    @Override
    public void onDestroy() {
        RxBus.get().unregister(TAG, mObservable);
        super.onDestroy();
    }
}
