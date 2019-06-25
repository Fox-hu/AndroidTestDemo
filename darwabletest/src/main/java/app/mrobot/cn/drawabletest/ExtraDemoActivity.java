package app.mrobot.cn.drawabletest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import app.mrobot.cn.R;

public class ExtraDemoActivity extends Activity implements View.OnClickListener {
    private static final String TAG = ExtraDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_extra_demo);
        SwipeRefreshLayout abnormalUserRefreshLayout = findViewById(R.id.abnormal_user_refresh);
        RecyclerView abnormalUserRv = findViewById(R.id.abnormal_user_rv);
        AbnormalAdapter adapter = new AbnormalAdapter();
        ScrollGroup abNormalGroup = new ScrollGroup(abnormalUserRv, adapter,
                abnormalUserRefreshLayout);
        abNormalGroup.init();

        SwipeRefreshLayout allUserRefreshLayout = findViewById(R.id.all_user_refresh);
        RecyclerView allUserRv = findViewById(R.id.all_user_rv);
        AbnormalAdapter adapter1 = new AbnormalAdapter();
        ScrollGroup allUserGroup = new ScrollGroup(allUserRv, adapter1,
                allUserRefreshLayout);
        allUserGroup.init();
    }


    @Override
    public void onClick(View v) {

    }
}
