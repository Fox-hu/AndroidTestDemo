package com.example.algorithms.webView;

import android.util.Log;

public interface DefaultInterface {
    void sort();

    default void test(){
        Log.e("TAG","test");
    }
}
