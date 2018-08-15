package app.mrobot.cn.toutiaoexample.module.news.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.module.BaseActivity;

/**
 * @author fox.hu
 * @date 2018/8/14
 */

public class NewsCommentActivity extends BaseActivity {
    private static final String TAG = NewsCommentActivity.class.getSimpleName();
    private static final String GROUP_ID = "groupId";
    private static final String ITEM_ID = "itemId";

    public static void launch(String groupId, String itemId) {
        InitApp.sContext.startActivity(new Intent(InitApp.sContext, NewsCommentActivity.class)
                .putExtra(GROUP_ID, groupId).putExtra(ITEM_ID, itemId));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, NewsCommentFragment
                .get(intent.getStringExtra(GROUP_ID), intent.getStringExtra(ITEM_ID))).commit();
    }
}
