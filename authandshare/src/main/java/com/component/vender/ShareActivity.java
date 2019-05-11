package com.component.vender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.component.vender.platform.PlatForm;
import com.component.vender.share.ShareManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fox.hu on 2018/9/3.
 */

public class ShareActivity extends Activity {
    private ListView listView;
    private com.component.vender.ShareAdapter shareAdapter;
    public List<PlatForm> platforms = Arrays.asList(PlatForm.QQ, PlatForm.SINA, PlatForm.WEIXIN);
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dialog = new ProgressDialog(this);
        listView = (ListView) findViewById(R.id.list);
        shareAdapter = new com.component.vender.ShareAdapter(this, platforms);
        listView.setAdapter(shareAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ShareManager.getDefault().onActivityResultData(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ShareManager.getDefault().onNewIntent(intent);
    }
}
