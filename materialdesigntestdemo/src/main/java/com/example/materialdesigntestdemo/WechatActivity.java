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

    @Override
    protected void onResume() {
        super.onResume();
//        final Bitmap textImage = Utils.createTextImage(1000, 1000, 12, getString(R.string.text));
//        Utils.saveBitmapFile(textImage, "01.jpg");
    }
}
