package app.mrobot.cn.toutiaoexample.business.video;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import app.mrobot.cn.toutiaoexample.module.BaseListFragment;
import app.mrobot.cn.toutiaoexample.module.news.article.INewsArticle;
import app.mrobot.cn.toutiaoexample.module.news.article.NewsArticlePresenter;
import app.mrobot.cn.toutiaoexample.utils.DiffCallback;
import app.mrobot.cn.toutiaoexample.utils.OnLoadMoreListener;
import app.mrobot.cn.toutiaoexample.widget.Register;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author fox.hu
 * @date 2018/6/11
 */

public class VideoArticleFragment extends BaseListFragment<INewsArticle.Presenter> implements
        INewsArticle.View {
    private static final String TAG = VideoArticleFragment.class.getSimpleName();
    private String mCategoryId;

    public static VideoArticleFragment get(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        VideoArticleFragment videoArticleFragment = new VideoArticleFragment();
        videoArticleFragment.setArguments(bundle);
        return videoArticleFragment;
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(mOldItems, newItems, mAdapter);
        mOldItems.clear();
        mOldItems.addAll(newItems);
        canLoadMore = true;
        mRecyclerView.stopScroll();
    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (presenter == null) {
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
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MultiTypeAdapter(mOldItems);
        Register.registerVideoArticleItem(mAdapter);
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
    protected void fetchData() {
        super.fetchData();
        onLoadData();
    }
}
