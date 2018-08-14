package app.mrobot.cn.toutiaoexample.module.news.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.module.BaseActivity;

/**
 *
 * @author fox.hu
 * @date 2018/8/14
 */

public class NewsCommentActivity extends BaseActivity {
    private static final String TAG = NewsCommentActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,NewsCommentFragment.get()).commit();
    }
}
