package app.mrobot.cn.demos.model.data;

import java.util.List;

import app.mrobot.cn.demos.model.entity.Movie;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2017/11/10.
 */

public interface DouBanMovieService {
    String BASE_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Observable<Response<List<Movie>>> getMovie(@Query("start") int start,
            @Query("count") int count);
}
