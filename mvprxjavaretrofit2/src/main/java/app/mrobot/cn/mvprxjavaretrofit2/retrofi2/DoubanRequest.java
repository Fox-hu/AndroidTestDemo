package app.mrobot.cn.mvprxjavaretrofit2.retrofi2;

import java.util.List;

import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Movie;
import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/1/8.
 */

public class DoubanRequest extends BaseUrlRequest<DoubanService> {

    public DoubanRequest() {
        super(DoubanService.BASE_URL, DoubanService.class);
    }

    public void getMovies(Subscriber<Movie> subscriber, int start, int count) {
        mHttpRequest.getMovie(start, count).map(new Func1<Response<List<Movie>>, List<Movie>>() {
            @Override
            public List<Movie> call(Response<List<Movie>> listResponse) {
                return listResponse.getSubjects();
            }
        }).flatMap(new Func1<List<Movie>, Observable<Movie>>() {
            @Override
            public Observable<Movie> call(List<Movie> movies) {
                return Observable.from(movies);
            }
        }).subscribeOn(Schedulers.io()).subscribe(
                subscriber);
    }
}
