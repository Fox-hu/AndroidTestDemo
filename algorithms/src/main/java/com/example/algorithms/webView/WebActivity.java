/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.algorithms.webView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.algorithms.R;

public class WebActivity extends Activity implements View.OnClickListener {
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        wv = (WebView) findViewById(R.id.wv);
        //获取webSettings
        WebSettings settings = wv.getSettings();
        //让webView支持JS
        settings.setJavaScriptEnabled(true);

        wv.loadUrl("file:///android_asset/123.html");
        //第一个参数把自身传给js 第二个参数是this的一个名字
        //这个方法用于让H5调用android方法
        wv.addJavascriptInterface(this, "android");
    }

    @JavascriptInterface
    public void setMessage() {
        Toast.makeText(this, "职位信息", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void setMessage(String name) {
        Toast.makeText(this, "职位信息" + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //参数 “javascript:”  +  js方法名
                wv.loadUrl("javascript:message()");
                break;
            case R.id.btn2:
                //在android调用js有参的函数的时候参数要加单引号
                wv.loadUrl("javascript:message2('" + "android" + "')");
                break;
            case R.id.btn3:
                startActivityForResult(new Intent(this, ResultActivity.class), 0);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        wv.loadUrl("javascript:message2('" + data.getStringExtra("TAG") + "')");
    }
}
