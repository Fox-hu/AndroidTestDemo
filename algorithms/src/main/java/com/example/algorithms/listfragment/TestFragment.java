package com.example.algorithms.listfragment;

import com.component.common.mvp.fragment.BaseListFragment;

import me.drakeet.multitype.MultiTypeAdapter;

public class TestFragment extends BaseListFragment<TestPresenter, Movie> {

    @Override
    protected void registerMultiType(MultiTypeAdapter adapter) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public TestPresenter initPresenter() {
        return new TestPresenter(this);
    }
}
