package app.mrobot.cn.toutiaoexample.utils;

import android.support.annotation.NonNull;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.api.INewsApi;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fox on 2018/2/27.
 */

public class RetrofitFactory {
    private static volatile Retrofit sRetrofit;

    private static final Interceptor cacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetWorkConnected(InitApp.sContext)) {
                request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetWorkConnected(InitApp.sContext)) {
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder().header("Cache-Control",
                        "public, only-if-cached, max-stale=" + maxStale).removeHeader("Pragma")
                        .build();
            }
        }
    };

    @NonNull
    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (sRetrofit == null) {
                    Cache httpCache = new Cache(
                            new File(InitApp.sContext.getCacheDir(), "HttpCache"),
                            1024 * 1024 * 50);

                    PersistentCookieJar persistentCookieJar = new PersistentCookieJar(
                            new SetCookieCache(), new SharedPrefsCookiePersistor(InitApp.sContext));

                    OkHttpClient.Builder builder = new OkHttpClient.Builder().cookieJar(
                            persistentCookieJar).cache(httpCache).addInterceptor(
                            cacheControlInterceptor).connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true);

                    sRetrofit = new Retrofit.Builder().baseUrl(INewsApi.HOST).client(
                            builder.build()).addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
                }
            }
        }

        return sRetrofit;
    }

}
