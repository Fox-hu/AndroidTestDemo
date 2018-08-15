package app.mrobot.cn.toutiaoexample.module.news.comment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import app.mrobot.cn.toutiaoexample.module.BaseListFragment;
import app.mrobot.cn.toutiaoexample.utils.DiffCallback;
import app.mrobot.cn.toutiaoexample.utils.OnLoadMoreListener;
import app.mrobot.cn.toutiaoexample.widget.Register;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author fox.hu
 * @date 2018/8/14
 */

public class NewsCommentFragment extends BaseListFragment<INewsComment.Presenter> implements
        INewsComment.View {
    private static final String TAG = NewsCommentFragment.class.getSimpleName();
    private static final String GROUP_ID = "groupId";
    private static final String ITEM_ID = "itemId";

    private String groupId;
    private String itemId;

    public static NewsCommentFragment get(String groupId, String itemId) {
        NewsCommentFragment instance = new NewsCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GROUP_ID, groupId);
        bundle.putString(ITEM_ID, itemId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list_toolbar;
    }

    @Override
    public void onSetAdapter(List list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(mOldItems, newItems, mAdapter);
        mOldItems.clear();
        mOldItems.addAll(newItems);
        canLoadMore = true;
        mRecyclerView.stopScroll();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbar(toolbar, true, getString(R.string.title_comment));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mAdapter = new MultiTypeAdapter(mOldItems);
        Register.registerNewsCommentItem(mAdapter);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMore();
                }
            }
        });
        setHasOptionsMenu(true);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        groupId = bundle.getString(GROUP_ID);
        itemId = bundle.getString(itemId);
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(groupId, itemId);
    }

    @Override
    public void setPresenter(INewsComment.Presenter presenter) {
        if (presenter == null) {
            this.presenter = new NewsCommentPresenter(this);
        }
    }

    @Override
    protected void fetchData() {

    }
}
