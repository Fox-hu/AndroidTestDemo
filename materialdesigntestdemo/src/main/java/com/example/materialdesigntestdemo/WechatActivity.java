package com.example.materialdesigntestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.component.common.utils.Utils;

public class WechatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        Utils.verifyStoragePermissions(this);
    }

}

