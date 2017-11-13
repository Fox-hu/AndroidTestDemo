package app.mrobot.cn.demos.viewModel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import app.mrobot.cn.demos.model.entity.Movie;

/**
 * Created by admin on 2017/11/8.
 */

public class MovieViewModel extends BaseObservable {
    private Movie movie;

    public MovieViewModel(Movie movie) {
        this.movie = movie;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public float getRating() {
        return movie.getRaging().getAverage();
    }

    public String getRatingText() {
        return String.valueOf(movie.getRaging().getAverage());
    }

    public String getYear() {
        return movie.getYear();
    }

    public String getMovieType() {
        StringBuilder builder = new StringBuilder();
        for (String s : movie.getGenres()) {
            builder.append(s + "");
        }
        return builder.toString();
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView,String url)
    {

    }
}
