package app.mrobot.cn.toutiaoexample.api;


import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleBean;
import app.mrobot.cn.toutiaoexample.bean.news.NewsCommentBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fox on 2018/2/27.
 */

public interface IMobileNewsApi {
    String HOST = "http://is.snssdk.com/";

    /**
     * 获取个性化新闻
     * 深圳 http://is.snssdk.com/api/news/feed/v58/?iid=5034850950&device_id=6096495334&category=news_society
     * 深圳 http://lf.snssdk.com/api/news/feed/v58/?iid=12507202490&device_id=37487219424&category=news_society
     * 天津 http://ib.snssdk.com/api/news/feed/v58/?
     * 北京 http://iu.snssdk.com/api/news/feed/v58/?
     *
     * @param iid      用户ID
     * @param deviceId 设备ID
     * @param category 新闻/图片/视频栏目
     */

    @GET("http://is.snssdk.com/api/news/feed/v58/")
    Call<ResponseBody> getNewsArticle(@Query("iid") String iid, @Query("device_id") String deviceId,
            @Query("category") String category);

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticle(@Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticle2(@Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    @GET("http://is.snssdk.com/article/v53/tab_comments/")
    Observable<NewsCommentBean> getNewsComment(@Query("group_id") String groupId,
            @Query("offset") int offset);
}
