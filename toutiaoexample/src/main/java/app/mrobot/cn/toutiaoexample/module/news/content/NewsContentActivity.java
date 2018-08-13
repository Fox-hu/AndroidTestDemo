package app.mrobot.cn.toutiaoexample.module.news.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.BaseActivity;

/**
 * @author fox.hu
 * @date 2018/8/10
 */

public class NewsContentActivity extends BaseActivity {
    private static final String TAG = NewsContentActivity.class.getSimpleName();
    private static final String IMG = "img";

    public static void launch(MultiNewsArticleDataBean bean) {
        InitApp.sContext.startActivity(new Intent(InitApp.sContext, NewsContentActivity.class)
                .putExtra(TAG, bean).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                NewsContentFragment.get(intent.getParcelableExtra(TAG), intent.getStringExtra(IMG)))
                .commit();
    }
}
