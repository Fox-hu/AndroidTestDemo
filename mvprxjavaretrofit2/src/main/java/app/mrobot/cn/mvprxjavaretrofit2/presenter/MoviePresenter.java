package app.mrobot.cn.mvprxjavaretrofit2.presenter;

import app.mrobot.cn.mvprxjavaretrofit2.model.MovieModel;
import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Movie;
import app.mrobot.cn.mvprxjavaretrofit2.view.MovieAdapter;
import app.mrobot.cn.mvprxjavaretrofit2.view.MovieView;
import rx.Subscriber;

/**
 * Created by admin on 2018/1/8.
 */

public class MoviePresenter extends BasePresenterImpl<MovieView, Movie> {
    private static final String TAG = MoviePresenter.class.getSimpleName();
    private Subscriber<Movie> mSubscriber;
    private MovieModel mMovieModel;
    private MovieAdapter mMovieAdapter;

    public MoviePresenter(MovieView baseView, MovieAdapter movieAdapter) {
        super(baseView);
        mMovieModel = new MovieModel();
        mMovieAdapter = movieAdapter;
    }

    public void refreshData() {
        mSubscriber = new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                MoviePresenter.this.requestComplete(0);
            }

            @Override
            public void onError(Throwable e) {
                MoviePresenter.this.requestError(0, 0);
            }

            @Override
            public void onNext(Movie movie) {
                mMovieAdapter.addItem(movie);
            }
        };
        mMovieModel.getMovie(mSubscriber, 0, 20);
    }
}
