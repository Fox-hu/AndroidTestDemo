package app.mrobot.cn.toutiaoexample.module.video;

import app.mrobot.cn.toutiaoexample.module.news.comment.INewsComment;

/**
 * Created by fox.hu on 2018/8/27.
 */

public interface IVideoContent {
    interface View extends INewsComment.View {

        void onSetVideoPlay(String url);
    }

    interface Presenter extends INewsComment.Presenter {

        void doLoadVideoData(String videoId);
    }
}
