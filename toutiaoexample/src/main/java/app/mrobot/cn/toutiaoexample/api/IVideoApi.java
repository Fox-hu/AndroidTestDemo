package app.mrobot.cn.toutiaoexample.api;

import app.mrobot.cn.toutiaoexample.bean.video.VideoContentBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by fox.hu on 2018/8/27.
 */

public interface IVideoApi {

    @GET
    Observable<VideoContentBean> getVideoContent(@Url String url);
}
