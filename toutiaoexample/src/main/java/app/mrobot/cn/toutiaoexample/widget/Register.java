package app.mrobot.cn.toutiaoexample.widget;

import android.support.annotation.NonNull;

import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import app.mrobot.cn.toutiaoexample.bean.LoadingEndBean;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.binder.LoadingEndViewBinder;
import app.mrobot.cn.toutiaoexample.binder.LoadingViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsArticleImgViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsArticleVideoViewBinder;
import app.mrobot.cn.toutiaoexample.binder.news.NewsTextViewBinder;
import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by fox on 2018/2/25.
 */

public class Register {
    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class).to(new NewsArticleImgViewBinder(),
                new NewsArticleVideoViewBinder(), new NewsTextViewBinder()).withClassLinker(
                new ClassLinker<MultiNewsArticleDataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<MultiNewsArticleDataBean, ?>> index(
                            int position, @NonNull MultiNewsArticleDataBean item) {
                        if (item.isHas_video()) {
                            return NewsArticleVideoViewBinder.class;
                        }
                        if (item.getImage_list() != null && !item.getImage_list().isEmpty()) {
                            return NewsArticleImgViewBinder.class;
                        }
                        return NewsTextViewBinder.class;
                    }
                });

        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
