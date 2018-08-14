package app.mrobot.cn.toutiaoexample.module.news.comment;

import app.mrobot.cn.toutiaoexample.module.IBasePresenter;
import app.mrobot.cn.toutiaoexample.module.IBaseView;

/**
 *
 * @author fox.hu
 * @date 2018/8/14
 */

public interface INewsComment {
    interface View extends IBaseView<Presenter>{
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
    }
}
