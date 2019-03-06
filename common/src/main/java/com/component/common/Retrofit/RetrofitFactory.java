package com.component.common.Retrofit;

import android.content.Context;
import android.text.TextUtils;

import com.component.common.utils.NetWorkUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final String TAG = RetrofitFactory.class.getSimpleName();
    private static Context sContext;
    private static Retrofit sRetrofit;
    private static String sBaseUrl;

    private RetrofitFactory() {

    }

    public static Retrofit get(Context context, String baseUrl) {
        if (context == null) {
            throw new NullPointerException("context null");
        }
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("baseUrl null");
        }
        sContext = context;
        sBaseUrl = baseUrl;
        return Holder.INSTANCE;
    }

    private static final Interceptor cacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkConnected(sContext)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response response = chain.proceed(request);
            if (NetWorkUtils.isNetworkConnected(sContext)) {
                String cacheControl = request.cacheControl().toString();
                return response.newBuilder().header("Cache-Control", cacheControl).removeHeader(
                        "Pragma").build();
            } else {
                // 无网络时 设置超时为1周
                int maxStale = 60 * 60 * 24 * 7;
                return response.newBuilder().header("Cache-Control",
                        "public, only-if-cached, max-stale=" + maxStale).removeHeader("Pragma")
                        .build();
            }
        }
    };


    private static class Holder {
        private static final Retrofit INSTANCE = getRetrofit();

        public static Retrofit getRetrofit() {
            if (sRetrofit == null) {
                // 指定缓存路径,缓存大小 50Mb
                Cache cache = new Cache(new File(sContext.getCacheDir(), "HttpCache"),
                        1024 * 1024 * 50);

                // Cookie 持久化
                ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(sContext));

                OkHttpClient.Builder builder = new OkHttpClient.Builder().cookieJar(cookieJar)
                        .cache(cache).addInterceptor(cacheControlInterceptor).connectTimeout(10,
                                TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15,
                                TimeUnit.SECONDS).retryOnConnectionFailure(true);

                sRetrofit = new Retrofit.Builder().baseUrl(sBaseUrl).client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
                                RxJava2CallAdapterFactory.create()).build();
            }
            return sRetrofit;
        }
    }
}
