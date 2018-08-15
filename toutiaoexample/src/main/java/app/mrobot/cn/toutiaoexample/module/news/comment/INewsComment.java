package app.mrobot.cn.toutiaoexample.module.news.comment;

import java.util.List;

import app.mrobot.cn.toutiaoexample.bean.news.NewsCommentBean;
import app.mrobot.cn.toutiaoexample.module.IBaseListView;
import app.mrobot.cn.toutiaoexample.module.IBasePresenter;

/**
 *
 * @author fox.hu
 * @date 2018/8/14
 */

public interface INewsComment {
    interface View extends IBaseListView<Presenter> {
        /**
         * 请求数据
         */
       void onLoadData();
    }

    interface Presenter extends IBasePresenter{
        /**
         * 请求数据
         */
        void doLoadData(String... groupId_ItemId);

        /**
         * 下拉加载更多
         */
        void doLoadMore();

        /**
         * 设置适配器数据列表
         */
        void doSetAdapter(List<NewsCommentBean.DataBean.CommentBean> list);

        /**
         * 无更多列表时显示
         */
        void doShowNoMore();
    }
}
