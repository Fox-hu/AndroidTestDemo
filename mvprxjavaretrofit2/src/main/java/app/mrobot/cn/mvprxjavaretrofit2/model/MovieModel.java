package app.mrobot.cn.mvprxjavaretrofit2.model;

import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Movie;
import app.mrobot.cn.mvprxjavaretrofit2.retrofi2.DoubanRequest;
import rx.Subscriber;

/**
 * Created by admin on 2018/1/8.
 */

public class MovieModel extends BaseModel {

    public MovieModel() {
        super(new DoubanRequest());
    }

    public void getMovie(Subscriber<Movie> subscriber, int start, int count) {
        ((DoubanRequest) mBaseUrlRequest).getMovies(subscriber, start, count);
    }
}
