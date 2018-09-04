package com.umeng.soexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.umeng.soexample.auth.AuthManager;

import java.util.Arrays;
import java.util.List;

public class AuthActivity extends AppCompatActivity {
    private ListView listView;
    private AuthAdapter authAdapter;
    public List<PlatForm> platforms = Arrays.asList(PlatForm.QQ, PlatForm.SINA, PlatForm.WEIXIN);
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dialog = new ProgressDialog(this);
        listView = (ListView) findViewById(R.id.list);
        authAdapter = new AuthAdapter(this, platforms);
        listView.setAdapter(authAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AuthManager.getDefault().onActivityResultData(requestCode, resultCode, data);
    }
}
