package app.mrobot.cn.toutiaoexample.binder.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.video.VideoContentActivity;
import app.mrobot.cn.toutiaoexample.utils.ImageLoader;
import app.mrobot.cn.toutiaoexample.utils.TimeUtil;
import app.mrobot.cn.toutiaoexample.widget.CircleImageView;
import app.mrobot.cn.toutiaoexample.widget.IntentAction;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by fox on 2018/2/25.
 */

public class NewsArticleVideoViewBinder extends ItemViewBinder<MultiNewsArticleDataBean, NewsArticleVideoViewBinder.ViewHolder> {

    private static final String TAG = NewsArticleVideoViewBinder.class.getSimpleName();

    @NonNull
    @Override
    protected NewsArticleVideoViewBinder.ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_article_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NewsArticleVideoViewBinder.ViewHolder holder,
            @NonNull final MultiNewsArticleDataBean item) {
        final Context context = holder.itemView.getContext();

        MultiNewsArticleDataBean.VideoDetailInfoBean video_detail_info = item
                .getVideo_detail_info();
        if (video_detail_info != null) {
            if (video_detail_info.getDetail_video_large_image() != null) {
                String image = video_detail_info.getDetail_video_large_image().getUrl();
                if (!TextUtils.isEmpty(image)) {
                    ImageLoader.loadCenterCrop(context, image, holder.iv_video_image,
                            R.color.viewBackground, R.mipmap.error_image);
                }
            }
        } else {
            holder.iv_video_image.setImageResource(R.mipmap.error_image);
        }

        if (item.getUser_info() != null) {
            String avatar_url = item.getUser_info().getAvatar_url();
            if (!TextUtils.isEmpty(avatar_url)) {
                ImageLoader.loadCenterCrop(context, avatar_url, holder.iv_media,
                        R.color.viewBackground);
            }
        }

        String title = item.getTitle();
        String source = item.getSource();
        String commont_count = item.getComment_count() + "评论";
        String date_time = item.getBehot_time() + "";
        if(!TextUtils.isEmpty(date_time)){
            date_time = TimeUtil.getTimeStampAgo(date_time);
        }

        int video_duration = item.getVideo_duration();
        String min = String.valueOf(video_duration / 60);
        String second = String.valueOf(video_duration % 10);
        if(Integer.parseInt(second)<10){
            second = "0" + second;
        }
        String video_time = min + ":" + second;

        holder.tv_title.setText(title);
        holder.tv_extra.setText(source + " - " + commont_count + " - " + date_time);
        holder.tv_video_time.setText(date_time);
        holder.iv_dots.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.iv_dots, Gravity.END, 0,
                    R.style.MyPopupMenu);
            popupMenu.inflate(R.menu.menu_share);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.action_share){
                    IntentAction.send(context,item.getTitle() + "\n" + item.getShare_url());
                }
                return false;
            });
            popupMenu.show();
        });

        RxView.clicks(holder.itemView).throttleFirst(1, TimeUnit.SECONDS).subscribe(
                o -> VideoContentActivity.launch(item), throwable -> throwable.printStackTrace());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView iv_media;
        private ImageView iv_video_image;
        private TextView tv_title;
        private TextView tv_video_time;
        private TextView tv_extra;
        private ImageView iv_dots;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_media = itemView.findViewById(R.id.iv_media);
            this.iv_video_image = itemView.findViewById(R.id.iv_video_image);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_video_time = itemView.findViewById(R.id.tv_video_time);
            this.tv_extra = itemView.findViewById(R.id.tv_extra);
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
        }
    }
}
