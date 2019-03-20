package com.example.algorithms.listfragment;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.algorithms.R;

import java.util.List;

public class TestQuickAdapter extends BaseQuickAdapter<MultiNewsArticleDataBean, BaseViewHolder> {

    public TestQuickAdapter(int layoutResId, @Nullable List<MultiNewsArticleDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiNewsArticleDataBean item) {
        helper.setText(R.id.card_text,item.getTitle());
    }
}
