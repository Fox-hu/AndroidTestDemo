package app.mrobot.cn.mvprxjavaretrofit2.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import app.mrobot.cn.mvprxjavaretrofit2.R;
import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Movie;
import app.mrobot.cn.mvprxjavaretrofit2.presenter.MoviePresenter;

/**
 * Created by admin on 2018/1/8.
 */

public class MovieFragment extends Fragment implements MovieView,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = MovieFragment.class.getSimpleName();
    private MoviePresenter mMoviePresenter;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    public static MovieFragment get() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);

        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);

        movieAdapter = new MovieAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        mMoviePresenter = new MoviePresenter(this, movieAdapter);
    }

    @Override
    public void toast(String message, int requestCode) {

    }

    @Override
    public void showProgress(int progress, int requestCode) {
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress(int requestCode) {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void loadSuccess(Movie loadData, int requestCode) {

    }

    @Override
    public void loadError(int errorCode, int requestCode) {

    }

    @Override
    public void onRefresh() {
        movieAdapter.clearItem();
        mMoviePresenter.refreshData();
    }
}
