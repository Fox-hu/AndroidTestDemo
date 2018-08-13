package app.mrobot.cn.toutiaoexample.module.news.content;

import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.IBasePresenter;
import app.mrobot.cn.toutiaoexample.module.IBaseView;

/**
 * Created by fox.hu on 2018/8/13.
 */

public interface INewsContent {
    interface View extends IBaseView<Presenter> {

        /**
         * @param url 网络请求url
         * @param flag
         */
        void onLoadWebView(String url, boolean flag);
    }

    interface Presenter extends IBasePresenter {

        /**
         * @param dataBean 新闻详情数据源
         */
        void doLoadData(MultiNewsArticleDataBean dataBean);
    }
}
