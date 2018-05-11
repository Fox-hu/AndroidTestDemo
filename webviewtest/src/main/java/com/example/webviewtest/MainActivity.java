package com.example.webviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webviewtest.threadtest.Parent;

public class MainActivity extends Activity {
    private Parent parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

//        webView.loadUrl("http://10.100.2.72/3g/api/get_service_page.php?accountid=49975168&key=12038df60a55ff784b81f434f51839fe5ad6d844&partner=a11842aceb47afc4680e983815660402&serviceid=1&version=830&nightmode=");
        webView.loadUrl("https://www.baidu.com/");

        parent = new Parent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        parent.start();
    }
}
