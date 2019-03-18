package com.example.materialdesigntestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.component.common.utils.Utils;
import com.example.materialdesigntestdemo.view.GroupInfo;
import com.example.materialdesigntestdemo.view.PinnedItemDecoration;

public class PinnedTitleActivity extends Activity implements PinnedItemDecoration.GroupInfoCallback {

    private static final String TAG = PinnedTitleActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned_title);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_content);
        Utils.setRv(recyclerView, this);
        recyclerView.addItemDecoration(new PinnedItemDecoration(this, this));
    }

    @Override
    public GroupInfo getGroupInfo(int position) {
        int groupId = position / 2;
        int index = position % 2;
        GroupInfo groupInfo = new GroupInfo(groupId, groupId + "");
        groupInfo.setGroupLength(2);
        groupInfo.setPosition(index);
        return groupInfo;
    }
}
