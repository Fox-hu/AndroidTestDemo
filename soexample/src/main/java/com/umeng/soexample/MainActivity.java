package com.umeng.soexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.umeng.soexample.thirdLogin.AuthLoginManager;
import com.umeng.soexample.thirdLogin.PlatForm;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private AuthAdapter shareAdapter;
    public List<PlatForm> platforms = Arrays.asList(PlatForm.QQ, PlatForm.SINA, PlatForm.WEIXIN,
            PlatForm.ALIPAY);
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        listView = (ListView) findViewById(R.id.list);
        shareAdapter = new AuthAdapter(this, platforms);
        listView.setAdapter(shareAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AuthLoginManager.getDefault().onActivityResultData(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
