package com.example.algorithms.listfragment;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.component.common.mvp.fragment.BaseQuickListFragment;
import com.component.common.mvp.loadmore.CustomLoadMoreView;
import com.example.algorithms.R;

public class TestQuickFragment extends BaseQuickListFragment<TestPresenter, MultiNewsArticleDataBean> {

    @Override
    protected void initData() {

    }

    @Override
    public TestPresenter initPresenter() {
        return new TestPresenter(this);
    }

    @Override
    protected View getErrorLayout() {
        View errorView = getLayoutInflater().inflate(R.layout.common_error_layout,
                (ViewGroup) recyclerView.getParent(), false);
        return errorView;
    }

    @Override
    protected LoadMoreView getCustomLoadMoreView() {
        return new CustomLoadMoreView();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new TestQuickAdapter(R.layout.common_item_card,null);
    }

    @Override
    protected int getPageSize() {
        return 6;
    }
}
