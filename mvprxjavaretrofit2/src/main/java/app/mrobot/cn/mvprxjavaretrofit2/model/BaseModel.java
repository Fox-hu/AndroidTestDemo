package app.mrobot.cn.mvprxjavaretrofit2.model;

import app.mrobot.cn.mvprxjavaretrofit2.retrofi2.BaseUrlRequest;

/**
 * Created by admin on 2018/1/8.
 */

public abstract class BaseModel{

    BaseUrlRequest mBaseUrlRequest;

    BaseModel(BaseUrlRequest baseUrlRequest) {
        mBaseUrlRequest = baseUrlRequest;
    }

}
