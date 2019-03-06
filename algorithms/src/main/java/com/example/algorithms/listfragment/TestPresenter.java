package com.example.algorithms.listfragment;

import com.component.common.Retrofit.RetrofitFactory;
import com.component.common.mvp.fragment.impl.BaseListImpl;
import com.component.common.mvp.fragment.impl.PresenterImpl;
import com.example.algorithms.InitApp;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestPresenter extends PresenterImpl<Movie> {

    public TestPresenter(BaseListImpl.ListView view) {
        super(view);
    }

    @Override
    public void doLoadData(String... params) {
        final Retrofit retrofit = RetrofitFactory.get(InitApp.sContext, DoubanService.BASE_URL);
        retrofit.create(DoubanService.class).getMovie(0, 20).map(
                new Func1<Response<List<Movie>>, List<Movie>>() {
                    @Override
                    public List<Movie> call(Response<List<Movie>> listResponse) {
                        return listResponse.getSubjects();
                    }
                }).flatMap(new Func1<List<Movie>, Observable<Movie>>() {
            @Override
            public Observable<Movie> call(List<Movie> movies) {
                return Observable.from(movies);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                view.onShowNetError();
            }

            @Override
            public void onNext(Movie movie) {

            }
        });
    }
}
