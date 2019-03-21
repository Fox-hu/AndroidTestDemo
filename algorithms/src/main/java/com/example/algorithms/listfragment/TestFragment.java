package com.example.algorithms.listfragment;

import android.support.annotation.NonNull;

import com.component.common.mvp.fragment.BaseListFragment;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

public class TestFragment extends BaseListFragment<TestListPresenter, MultiNewsArticleDataBean> {

    @Override
    protected void registerMultiType(MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class).to(new NewsArticleImgViewBinder(),
                new NewsArticleVideoViewBinder(), new NewsArticleTextViewBinder()).withClassLinker(
                new ClassLinker<MultiNewsArticleDataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<MultiNewsArticleDataBean, ?>> index(
                            int position, @NonNull MultiNewsArticleDataBean item) {
                        if (item.isHas_video()) {
                            return NewsArticleVideoViewBinder.class;
                        }
                        if (null != item.getImage_list() && item.getImage_list().size() > 0) {
                            return NewsArticleImgViewBinder.class;
                        }
                        return NewsArticleTextViewBinder.class;
                    }
                });
    }

    @Override
    protected void initData() {

    }

    @Override
    public TestListPresenter initPresenter() {
        return new TestListPresenter(this);
    }
}
