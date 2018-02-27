package app.mrobot.cn.toutiaoexample.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fox on 2018/2/27.
 */

public interface INewsApi {
    String HOST = "http://toutiao.com/";

    /**
     * 获取新闻标题等信息
     * "http://toutiao.com/api/article/recent/?source=2&category=类型&as=A105177907376A5&cp=5797C7865AD54E1&_=时间&count=30";
     * <p>
     * 其他 API
     * "http://www.toutiao.com/api/article/feed/?category=类型&as=A115C8457F69B85&cp=585F294B8845EE1&_=时间&count=30";
     * "http://www.toutiao.com/api/pc/feed/?category=类型&utm_source=toutiao&widen=1&max_behot_time=时间&max_behot_time_tmp=时间&tadrequire=true&as=A1C598BB87BE7DA&cp=58B72ED7AD3A0E1";
     */

    @GET("api/article/recent/?source=2&as=A105177907376A5&cp=5797C7865AD54E1&count=30")
    Call<ResponseBody> getNewsArticle(@Query("category") String category,
            @Query("_") int max_behot_time);

    /**
     * 获取新闻标题等信息 部分请求参数不同上
     * "http://toutiao.com/api/article/recent/?source=2&category=类型&as=A105177907376A5&cp=5797C7865AD54E1&_=时间&count=30";
     */


}
