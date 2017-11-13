package app.mrobot.cn.demos.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.mrobot.cn.demos.R;
import app.mrobot.cn.demos.databinding.MovieFragmentBinding;
import app.mrobot.cn.demos.viewModel.ManiViewModel;

/**
 *
 * @author admin
 * @date 2017/11/7
 */

public class MovieFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = MovieFragment.class.getSimpleName();
    private ManiViewModel viewModel;
    private MovieFragmentBinding movieFragmentBinding;
    private MovieAdapter movieAdapter;

    public static MovieFragment get() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        movieFragmentBinding = MovieFragmentBinding.bind(view);
        initData();
        return view;
    }

    private void initData() {
        movieFragmentBinding.recycleView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        movieFragmentBinding.recycleView.setItemAnimator(new DefaultItemAnimator());
        movieAdapter = new MovieAdapter();
        movieFragmentBinding.recycleView.setAdapter(movieAdapter);
        movieFragmentBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary, R.color.colorPrimaryDark);
        movieFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {

    }
}
