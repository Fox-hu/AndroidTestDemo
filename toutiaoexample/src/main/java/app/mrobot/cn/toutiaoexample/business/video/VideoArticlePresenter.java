package app.mrobot.cn.toutiaoexample.business.video;

import java.util.List;

import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.news.INewsArticle;

/**
 *
 * @author fox.hu
 * @date 2018/6/11
 */

public class VideoArticlePresenter implements INewsArticle.Presenter {
    private static final String TAG = VideoArticlePresenter.class.getSimpleName();
    private INewsArticle.View mView;


    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }

    @Override
    public void doLoadData(String... category) {

    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doSetAdapter(List<MultiNewsArticleDataBean> list) {

    }

    @Override
    public void doShowNoMore() {

    }
}
