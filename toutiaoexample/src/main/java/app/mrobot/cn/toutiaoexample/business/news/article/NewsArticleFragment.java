package app.mrobot.cn.toutiaoexample.business.news.article;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.mrobot.cn.toutiaoexample.R;

/**
 * Created by fox on 2018/1/28.
 */

public class NewsArticleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    public static Fragment get() {
        return new NewsArticleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }

    @Override
    public void onRefresh() {

    }
}
