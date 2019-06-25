package app.mrobot.cn.rv_bg;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class ExtraDemoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ExtraDemoAdapter(@Nullable List<String> data) {
        super(com.component.common.R.layout.common_item_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(com.component.common.R.id.card_text, item);
    }
}
