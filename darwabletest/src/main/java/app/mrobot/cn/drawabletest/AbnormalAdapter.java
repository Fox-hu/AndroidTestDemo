package app.mrobot.cn.drawabletest;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import app.mrobot.cn.R;

public class AbnormalAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public AbnormalAdapter() {
        super(R.layout.layout_abnormal_user_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {

    }
}
