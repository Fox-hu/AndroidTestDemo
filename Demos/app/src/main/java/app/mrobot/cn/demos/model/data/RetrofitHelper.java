package app.mrobot.cn.demos.model.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by admin on 2017/11/10.
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private DouBanMovieService movieService;
    OkHttpClient.Builder builder;

    private static class Singleton{
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }
    
}
