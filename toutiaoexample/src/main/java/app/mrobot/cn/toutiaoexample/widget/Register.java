package app.mrobot.cn.toutiaoexample.widget;

import android.support.annotation.NonNull;

import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import app.mrobot.cn.toutiaoexample.bean.LoadingEndBean;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.bean.news.NewsCommentBean;
import app.mrobot.cn.toutiaoexample.binder.LoadingEndViewBinder;
import app.mrobot.cn.toutiaoexample.binder.LoadingViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsArticleImgViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsArticleVideoViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsCommentViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsTextViewBinder;
import app.mrobot.cn.toutiaoexample.binder.video.VideoContentHeaderViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author fox
 * @date 2018/2/25
 */

public class Register {
    /**
     * @param adapter
     */
    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class).to(new NewsArticleImgViewBinder(),
                new NewsArticleVideoViewBinder(), new NewsTextViewBinder()).withClassLinker(
                (position, item) -> {
                    if (item.isHas_video()) {
                        return NewsArticleVideoViewBinder.class;
                    }
                    if (item.getImage_list() != null && !item.getImage_list().isEmpty()) {
                        return NewsArticleImgViewBinder.class;
                    }
                    return NewsTextViewBinder.class;
                });

        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    /**
     * @param adapter
     */
    public static void registerVideoArticleItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class, new NewsArticleVideoViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerNewsCommentItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(NewsCommentBean.DataBean.CommentBean.class, new NewsCommentViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerVideoContentItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class, new VideoContentHeaderViewBinder());
        adapter.register(NewsCommentBean.DataBean.CommentBean.class, new NewsCommentViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
