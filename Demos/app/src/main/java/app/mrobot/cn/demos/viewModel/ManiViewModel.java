package app.mrobot.cn.demos.viewModel;

import android.databinding.ObservableField;
import android.view.View;

import app.mrobot.cn.demos.model.entity.Movie;
import app.mrobot.cn.demos.view.CompletedListener;
import app.mrobot.cn.demos.view.MovieAdapter;
import rx.Subscriber;

/**
 * Created by admin on 2017/11/8.
 */

public class ManiViewModel {
    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorLayoutVisibility;
    public ObservableField<String> exception;
    private Subscriber<Movie> subscriber;
    private MovieAdapter movieAdapter;
    private CompletedListener completedListener;


    public ManiViewModel(CompletedListener completedListener, MovieAdapter movieAdapter) {
        this.completedListener = completedListener;
        this.movieAdapter = movieAdapter;
        initData();
        getMovies();
    }

    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorLayoutVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }

    public void getMovies() {
        subscriber = new Subscriber<Movie>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Movie movie) {

            }
        };
    }
}
