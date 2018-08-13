package app.mrobot.cn.toutiaoexample.module.news.content;

import android.text.TextUtils;

import app.mrobot.cn.toutiaoexample.api.INewsApi;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.bean.news.NewsContentBean;
import app.mrobot.cn.toutiaoexample.utils.RetrofitFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author fox.hu
 * @date 2018/8/13
 */

public class NewsContentPresenter implements INewsContent.Presenter {
    private static final String TAG = NewsContentPresenter.class.getSimpleName();

    private INewsContent.View view;
    private String html;
    private long groupId;
    private long itemId;


    public NewsContentPresenter(INewsContent.View view) {
        this.view = view;
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doLoadData(MultiNewsArticleDataBean dataBean) {
        groupId = dataBean.getGroup_id();
        itemId = dataBean.getItem_id();
        final String url = dataBean.getDisplay_url();

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String api = url.replace("www.", "").replace("toutiao", "m.toutiao").replace(
                        "group/", "i") + "info/";
                emitter.onNext(api);
            }
        }).subscribeOn(Schedulers.io()).switchMap(
                new Function<String, ObservableSource<NewsContentBean>>() {
                    @Override
                    public ObservableSource<NewsContentBean> apply(String s) throws Exception {
                        return RetrofitFactory.getRetrofit().create(INewsApi.class).getNewsContent(
                                s);
                    }
                }).map(new Function<NewsContentBean, String>() {
            @Override
            public String apply(NewsContentBean newsContentBean) throws Exception {
                String title = newsContentBean.getData().getTitle();
                String content = newsContentBean.getData().getContent();
                if (!TextUtils.isEmpty(content)) {
                    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/toutiao_light.css\" type=\"text/css\">";
                    css = css.replace("toutiao_light", "toutiao_dark");
                    html = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" +
                           "    <meta charset=\"UTF-8\">" + css + "<body>\n" +
                           "<article class=\"article-container\">\n" +
                           "    <div class=\"article__content article-content\">" +
                           "<h1 class=\"article-title\">" + title + "</h1>" + content +
                           "    </div>\n" + "</article>\n" + "</body>\n" + "</html>";

                    return html;
                } else {
                    return null;
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).as(view.bindAutoDispose()).subscribe(
                new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        view.onLoadWebView(s, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        doShowNetError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
