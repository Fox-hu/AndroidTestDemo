package app.mrobot.cn.drawabletest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import app.mrobot.cn.R;

public class IvbgActivity extends Activity implements View.OnClickListener,
        ViewSwitcher.ViewFactory {
    private static final String TAG = IvbgActivity.class.getSimpleName();
    ImageSwitcher iv;
    int[] res = new int[]{R.mipmap.login_bg01,
                          R.mipmap.login_bg02,
                          R.mipmap.login_bg03,
                          R.mipmap.login_bg04};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_iv_bg);
        iv = findViewById(R.id.iv);
        iv.setFactory(this);
        findViewById(R.id.ll_login).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    iv.setInAnimation(IvbgActivity.this, R.anim.anim_fade_in);
                    iv.setOutAnimation(IvbgActivity.this, R.anim.anim_fade_out);
                    iv.setImageResource(res[i % res.length]);
                    i++;
                    removeMessages(1);
                    Log.e(TAG, "sendEmptyMessageDelayed ");
                    handler.sendEmptyMessageDelayed(1, 3000);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    public View makeView() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(res[0]);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }
}
