package app.mrobot.cn.drawabletest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import app.mrobot.cn.rv_bg.ScollLinearLayoutManager;
import app.mrobot.cn.rv_bg.SplashAdapter;

public class RvbgActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rv_bg);

        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);
        mRecyclerView.setAdapter(new SplashAdapter(RvbgActivity.this));
        mRecyclerView.setLayoutManager(new ScollLinearLayoutManager(RvbgActivity.this));
        //smoothScrollToPosition滚动到某个位置（有滚动效果）
        mRecyclerView.smoothScrollToPosition(Integer.MAX_VALUE / 2);

        findViewById(R.id.ll_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
