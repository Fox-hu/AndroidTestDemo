package app.mrobot.cn.toutiaoexample.module.news;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.mrobot.cn.toutiaoexample.api.IMobileNewsApi;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleBean;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.utils.RetrofitFactory;
import app.mrobot.cn.toutiaoexample.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fox on 2018/2/27.
 */

public class NewsArticlePresenter implements INewsArticle.Presenter {
    private static final String TAG = NewsArticlePresenter.class.getSimpleName();

    private INewsArticle.View view;
    private String category;
    private String time;
    private Gson gson = new Gson();
    private Random random = new Random();
    private List<MultiNewsArticleDataBean> dataList = new ArrayList<>();

    public NewsArticlePresenter(INewsArticle.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doRefresh() {
        if (!dataList.isEmpty()) {
            dataList.clear();
            time = TimeUtil.getCurrentTimeStamp();
        }
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doLoadData(String... category) {
        if (this.category == null) {
            this.category = category[0];
        }
        if (dataList.size() > 150) {
            dataList.clear();
        }

        getRandom().subscribeOn(Schedulers.io()).switchMap(
                new Function<MultiNewsArticleBean, Observable<MultiNewsArticleDataBean>>() {
                    @Override
                    public Observable<MultiNewsArticleDataBean> apply(
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
            public boolean test(@NonNull MultiNewsArticleDataBean dataBean) throws Exception {
                time = dataBean.getBehot_time();
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
                    e.printStackTrace();
                }
                for (MultiNewsArticleDataBean multiNewsArticleDataBean : dataList) {
                    if (multiNewsArticleDataBean.getTitle().equals(dataBean.getTitle())) {
                        return false;
                    }
                }
                return true;
            }
        }).toList().map(
                new Function<List<MultiNewsArticleDataBean>, List<MultiNewsArticleDataBean>>() {
                    @Override
                    public List<MultiNewsArticleDataBean> apply(
                            @NonNull List<MultiNewsArticleDataBean> list) throws Exception {
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
                }).observeOn(
                AndroidSchedulers.mainThread()).as(view.<List<MultiNewsArticleDataBean>>bindAutoDispose()).subscribe(
                new Consumer<List<MultiNewsArticleDataBean>>() {
                    @Override
                    public void accept(List<MultiNewsArticleDataBean> list) throws Exception {
                        if (list != null && !list.isEmpty()) {
                            doSetAdapter(list);
                        } else {
                            doShowNoMore();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        doShowNetError();
                        throwable.printStackTrace();
                    }
                });
    }

    private Observable<MultiNewsArticleBean> getRandom() {
        int i = random.nextInt(10);
        if (i % 2 == 0) {
            Observable<MultiNewsArticleBean> newsArticle = RetrofitFactory.getRetrofit().create(
                    IMobileNewsApi.class).getNewsArticle(this.category, this.time);
            return newsArticle;
        } else {
            Observable<MultiNewsArticleBean> newsArticle2 = RetrofitFactory.getRetrofit().create(
                    IMobileNewsApi.class).getNewsArticle2(this.category, this.time);
            return newsArticle2;
        }
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<MultiNewsArticleDataBean> list) {
        dataList.addAll(list);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }
}
