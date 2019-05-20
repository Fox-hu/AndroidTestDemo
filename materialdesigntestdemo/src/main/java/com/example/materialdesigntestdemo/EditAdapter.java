package com.example.materialdesigntestdemo;

import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class EditAdapter extends BaseQuickAdapter<String, EditAdapter.VH> {

    public EditAdapter(int resid, List<String> data) {
        super(resid, data);
    }

    //第三个edittext获取焦点
    @Override
    protected void convert(VH helper, String item) {
        if (mData.indexOf(item) == 3) {
            helper.editText2.requestFocus();
        }
    }

    public static class VH extends BaseViewHolder {
        public final EditText editText1;
        public final EditText editText2;

        public VH(View v) {
            super(v);
            editText1 = v.findViewById(R.id.et_content1);
            editText2 = v.findViewById(R.id.et_content2);
        }
    }
}
