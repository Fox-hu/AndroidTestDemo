package app.mrobot.cn.mvprxjavaretrofit2.retrofi2;

import java.util.List;

import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Movie;
import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2018/1/8.
 */

public interface DoubanService {
    String BASE_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Observable<Response<List<Movie>>> getMovie(@Query("start") int start,
            @Query("count") int count);


}
