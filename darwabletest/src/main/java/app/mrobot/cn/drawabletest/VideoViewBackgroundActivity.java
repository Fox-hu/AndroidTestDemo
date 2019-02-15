package app.mrobot.cn.drawabletest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import app.mrobot.cn.R;

public class VideoViewBackgroundActivity extends Activity {
    private VideoView myVideo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        myVideo = (VideoView) findViewById(R.id.myvideo);
        initView();
    }

    public void initView(){
        //播放路径
        myVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        myVideo.start();
        //循环播放
        myVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                myVideo.start();
            }
        });
    }
    @Override
    protected void onRestart() {
        //返回重新加载
        initView();
        super.onRestart();
    }


    @Override
    protected void onStop() {
        //防止锁屏或者弹出的时候，音乐在播放
        myVideo.stopPlayback();
        super.onStop();
    }
}
