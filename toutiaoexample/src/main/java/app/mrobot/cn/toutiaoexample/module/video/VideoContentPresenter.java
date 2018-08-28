package app.mrobot.cn.toutiaoexample.module.video;


import android.util.Base64;

import java.util.Random;
import java.util.zip.CRC32;

import app.mrobot.cn.toutiaoexample.api.IVideoApi;
import app.mrobot.cn.toutiaoexample.bean.video.VideoContentBean;
import app.mrobot.cn.toutiaoexample.module.news.comment.NewsCommentPresenter;
import app.mrobot.cn.toutiaoexample.utils.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author fox.hu
 * @date 2018/8/27
 */

public class VideoContentPresenter extends NewsCommentPresenter implements IVideoContent.Presenter {
    private IVideoContent.View view;

    public VideoContentPresenter(IVideoContent.View view) {
        super(view);
        this.view = view;
    }

    private String getVideoContentApi(String video) {
        String VIDEO_HOST = "http://ib.365yg.com";
        String VIDEO_URL = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
        String random = getRandom();
        String s = String.format(VIDEO_URL, video, random);

        CRC32 crc32 = new CRC32();
        crc32.update(s.getBytes());
        String crcStr = crc32.getValue() + "";
        String url = VIDEO_HOST + s + "&s=" + crcStr;
        return url;
    }

    private String getRandom() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    @Override
    public void doLoadVideoData(String videoId) {
        String url = getVideoContentApi(videoId);
        RetrofitFactory.getRetrofit().create(IVideoApi.class).getVideoContent(url).subscribeOn(
                Schedulers.io()).map(videoContentBean -> {
            VideoContentBean.DataBean.VideoListBean videoList = videoContentBean.getData()
                    .getVideo_list();
            if (videoList.getVideo_3() != null) {
                String mainUrl = videoList.getVideo_3().getMain_url();
                String url1 = new String(Base64.decode(mainUrl.getBytes(), Base64.DEFAULT));
                return url1;
            }
            if (videoList.getVideo_2() != null) {
                String mainUrl = videoList.getVideo_2().getMain_url();
                String url1 = new String(Base64.decode(mainUrl.getBytes(), Base64.DEFAULT));
                return url1;
            }
            if (videoList.getVideo_1() != null) {
                String mainUrl = videoList.getVideo_1().getMain_url();
                String url1 = new String(Base64.decode(mainUrl.getBytes(), Base64.DEFAULT));
                return url1;
            }
            return null;
        }).observeOn(AndroidSchedulers.mainThread()).as(view.bindAutoDispose()).subscribe(s -> {
            view.onSetVideoPlay(s);
            view.onHideLoading();
        }, throwable -> {
            view.onShowNetError();
            view.onHideLoading();
        });
    }
}
