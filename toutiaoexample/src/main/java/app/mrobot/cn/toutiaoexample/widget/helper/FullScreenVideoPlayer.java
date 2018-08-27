package app.mrobot.cn.toutiaoexample.widget.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import app.mrobot.cn.toutiaoexample.R;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * @author fox.hu
 * @date 2018/8/27
 */

public class FullScreenVideoPlayer extends JZVideoPlayerStandard {
    private static final String TAG = FullScreenVideoPlayer.class.getSimpleName();

    public void setonClickFullScreenListener(onClickFullScreenListener listener) {
        this.listener = listener;
    }

    private onClickFullScreenListener listener;


    public FullScreenVideoPlayer(Context context) {
        super(context);
    }

    public FullScreenVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.fullscreen) {
            if (listener != null) {
                listener.onClickFullScreen();
            }
        }
    }

    public interface onClickFullScreenListener {
        void onClickFullScreen();
    }
}
