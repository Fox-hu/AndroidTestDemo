package app.mrobot.cn.mvprxjavaretrofit2.retrofi2;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2018/1/8.
 */

public abstract class BaseUrlRequest<T> {
    private static final String TAG = BaseUrlRequest.class.getSimpleName();
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit mRetrofit;
    private OkHttpClient.Builder mBuilder;
    T mHttpRequest;

    BaseUrlRequest(String baseUrl, Class<T> httpServiceClass) {
        mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(baseUrl).build();
        mHttpRequest = mRetrofit.create(httpServiceClass);
    }
}
