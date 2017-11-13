package app.mrobot.cn.demos.model.data;

import java.util.List;
import java.util.concurrent.TimeUnit;

import app.mrobot.cn.demos.model.entity.Movie;
import app.mrobot.cn.demos.model.entity.Response;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2017/11/10.
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private DouBanMovieService movieService;
    OkHttpClient.Builder builder;

    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper get() {
        return Singleton.INSTANCE;
    }

    private RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(
                GsonConverterFactory.create()).addCallAdapterFactory(
                RxJavaCallAdapterFactory.create()).baseUrl(DouBanMovieService.BASE_URL).build();
        movieService = retrofit.create(DouBanMovieService.class);
    }

    public void getMovies(Subscriber<Movie> subscriber, int start, int count) {
        movieService.getMovie(start, count).map(new Func1<Response<List<Movie>>, List<Movie>>() {

            @Override
            public List<Movie> call(Response<List<Movie>> listResponse) {
                return listResponse.getSubjects();
            }
        }).flatMap(new Func1<List<Movie>, Observable<Movie>>() {
            @Override
            public Observable<Movie> call(List<Movie> movies) {
                return Observable.from(movies);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                subscriber);
    }
}
