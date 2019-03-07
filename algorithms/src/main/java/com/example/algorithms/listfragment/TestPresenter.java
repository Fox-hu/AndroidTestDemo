package com.example.algorithms.listfragment;

import android.text.TextUtils;

import com.component.common.Retrofit.RetrofitFactory;
import com.component.common.mvp.fragment.impl.BaseListImpl;
import com.component.common.mvp.fragment.impl.PresenterImpl;
import com.component.common.utils.TimeUtil;
import com.example.algorithms.InitApp;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TestPresenter extends PresenterImpl<MultiNewsArticleDataBean> {
    private Gson gson = new Gson();

    public TestPresenter(BaseListImpl.ListView view) {
        super(view);
    }

    @Override
    public void doLoadData(String... params) {
        final Retrofit retrofit = RetrofitFactory.get(InitApp.sContext, IMobileNewsApi.HOST);
        retrofit.create(IMobileNewsApi.class).getNewsArticle("news_hot",
                TimeUtil.getCurrentTimeStamp()).subscribeOn(Schedulers.io()).switchMap(
                new Function<MultiNewsArticleBean, ObservableSource<MultiNewsArticleDataBean>>() {
                    @Override
                    public ObservableSource<MultiNewsArticleDataBean> apply(
                            MultiNewsArticleBean multiNewsArticleBean) throws Exception {
                        List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
                        for (MultiNewsArticleBean.DataBean dataBean : multiNewsArticleBean
                                .getData()) {
                            dataList.add(gson.fromJson(dataBean.getContent(),
                                    MultiNewsArticleDataBean.class));
                        }
                        return Observable.fromIterable(dataList);
                    }
                }).filter(new Predicate<MultiNewsArticleDataBean>() {
            @Override
            public boolean test(MultiNewsArticleDataBean dataBean) throws Exception {
                if (TextUtils.isEmpty(dataBean.getSource())) {
                    return false;
                }
                try {
                    // 过滤头条问答新闻
                    if (dataBean.getSource().contains("头条问答") || dataBean.getTag().contains("ad") ||
                        dataBean.getSource().contains("悟空问答")) {
                        return false;
                    }
                    // 过滤头条问答新闻
                    if (dataBean.getRead_count() == 0 || TextUtils.isEmpty(
                            dataBean.getMedia_name())) {
                        String title = dataBean.getTitle();
                        if (title.lastIndexOf("？") == title.length() - 1) {
                            return false;
                        }
                    }
                } catch (NullPointerException e) {

                }
                // 过滤重复新闻(与上次刷新的数据比较)
                for (MultiNewsArticleDataBean bean : dataList) {
                    if (bean.getTitle().equals(dataBean.getTitle())) {
                        return false;
                    }
                }
                return true;
            }
        }).toList().map(
                new Function<List<MultiNewsArticleDataBean>, List<MultiNewsArticleDataBean>>() {
                    @Override
                    public List<MultiNewsArticleDataBean> apply(
                            List<MultiNewsArticleDataBean> list) throws Exception {
                        // 过滤重复新闻(与本次刷新的数据比较,因为使用了2个请求,数据会有重复)
                        for (int i = 0; i < list.size() - 1; i++) {
                            for (int j = list.size() - 1; j > i; j--) {
                                if (list.get(j).getTitle().equals(list.get(i).getTitle())) {
                                    list.remove(j);
                                }
                            }
                        }
                        return list;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Consumer<List<MultiNewsArticleDataBean>>() {
                    @Override
                    public void accept(List<MultiNewsArticleDataBean> list) throws Exception {
                        if (null != list && list.size() > 0) {
                            doSetAdapter(list);
                        } else {
                            doShowNoMore();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        doShowNetError();
                    }
                });
    }
}
