package app.mrobot.cn.toutiaoexample.module.news;

import java.util.List;

import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.IBaseListView;
import app.mrobot.cn.toutiaoexample.module.IBasePresenter;

/**
 * Created by fox on 2018/2/25.
 */

public interface INewsArticle {

    interface View extends IBaseListView<Presenter>{
        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter{
        /**
         * 请求数据
         * @param category
         */
        void doLoadData(String... category);

        /**
         * 再次请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> list);

        /**
         * 加载完毕
         */
        void doShowMore();
    }
}
