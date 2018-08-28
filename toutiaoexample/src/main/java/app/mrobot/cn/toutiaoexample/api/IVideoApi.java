package app.mrobot.cn.toutiaoexample.api;

import app.mrobot.cn.toutiaoexample.bean.video.VideoContentBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by fox.hu on 2018/8/27.
 */

public interface IVideoApi {
    /**
     * 获取视频信息
     * Api 生成较复杂
     * http://ib.365yg.com/video/urls/v/1/toutiao/mp4/视频ID?r=17位随机数&s=加密结果
     */
    @GET
    Observable<VideoContentBean> getVideoContent(@Url String url);
}
