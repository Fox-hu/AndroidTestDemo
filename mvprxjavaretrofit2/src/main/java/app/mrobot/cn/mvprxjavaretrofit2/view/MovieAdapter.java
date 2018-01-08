package app.mrobot.cn.mvprxjavaretrofit2.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.mrobot.cn.mvprxjavaretrofit2.R;
import app.mrobot.cn.mvprxjavaretrofit2.model.entity.Movie;

/**
 * Created by admin on 2018/1/8.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private Context mContext;

    public MovieAdapter(Context mContext) {
        this.movieList = new ArrayList<>();
        this.mContext = mContext;
    }

    public void addItem(Movie movie) {
        movieList.add(movie);
        notifyItemInserted(movieList.size() - 1);
    }

    public void clearItem() {
        movieList.clear();
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,
                false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.titleTv.setText(movie.getTitle());

        StringBuilder builder = new StringBuilder();
        for (String s : movie.getGenres()) {
            builder.append(s + "");
        }
        holder.movieTypeTv.setText(builder.toString());
        holder.movieYearTv.setText(movie.getYear());
        holder.ratingTv.setText(String.valueOf(movie.getRating().getAverage()));
        holder.ratingBar.setRating(movie.getRating().getAverage());

        Glide.with(mContext).load(movie.getImages().getSmall()).placeholder(R.drawable.cover).error(
                R.drawable.cover).into(holder.coverIv);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        RatingBar ratingBar;
        TextView ratingTv;
        TextView movieTypeTv;
        TextView movieYearTv;
        ImageView coverIv;

        public MovieViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            ratingTv = (TextView) itemView.findViewById(R.id.rating_text);
            movieTypeTv = (TextView) itemView.findViewById(R.id.movie_type_text);
            movieYearTv = (TextView) itemView.findViewById(R.id.year_text);
            coverIv = (ImageView) itemView.findViewById(R.id.cover);
        }
    }
}
