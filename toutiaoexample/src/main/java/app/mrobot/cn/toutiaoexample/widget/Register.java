package app.mrobot.cn.toutiaoexample.widget;

import android.support.annotation.NonNull;

import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by fox on 2018/2/25.
 */

public class Register {
    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(MultiNewsArticleDataBean.class).to(new )
    }
}
