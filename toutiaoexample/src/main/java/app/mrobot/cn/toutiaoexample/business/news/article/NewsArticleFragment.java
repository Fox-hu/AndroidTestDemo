package app.mrobot.cn.toutiaoexample.business.news.article;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import app.mrobot.cn.toutiaoexample.module.BaseListFragment;
import app.mrobot.cn.toutiaoexample.module.news.INewsArticle;
import app.mrobot.cn.toutiaoexample.module.news.NewsArticlePresenter;
import app.mrobot.cn.toutiaoexample.utils.DiffCallback;
import app.mrobot.cn.toutiaoexample.utils.OnLoadMoreListener;
import app.mrobot.cn.toutiaoexample.widget.Register;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by fox on 2018/1/28.
 */

public class NewsArticleFragment extends BaseListFragment<INewsArticle.Presenter> implements
        INewsArticle.View {
    private static final String TAG = NewsArticleFragment.class.getSimpleName();
    private String mCategoryId;

    public static NewsArticleFragment get(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG,categoryId);
        NewsArticleFragment newsArticleFragment = new NewsArticleFragment();
        newsArticleFragment.setArguments(bundle);
        return newsArticleFragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MultiTypeAdapter(mOldItems);
        Register.registerNewsArticleItem(mAdapter);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items items = new Items(list);
        items.add(new LoadingBean());
        DiffCallback.create(mOldItems, items, mAdapter);
        mOldItems.clear();
        mOldItems.addAll(items);
        canLoadMore = true;
        /**
         * https://medium.com/@hanru.yeh/recyclerview-and-appbarlayout-behavior-changed-in-v26-0-x-d9eb4de78fc0
         * support libraries v26 增加了 RV 惯性滑动，当 root layout 使用了 AppBarLayout Behavior 就会自动生效
         * 因此需要手动停止滑动
         */
        mRecyclerView.stopScroll();
    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(mCategoryId);
    }

    @Override
    protected void initData() {
        mCategoryId = getArguments().getString(TAG);
    }

    @Override
    protected void fetchData() {
        super.fetchData();
        onLoadData();
    }

}
