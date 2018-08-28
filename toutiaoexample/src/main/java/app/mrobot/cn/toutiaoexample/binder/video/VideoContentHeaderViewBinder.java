package app.mrobot.cn.toutiaoexample.binder.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.utils.ImageLoader;
import app.mrobot.cn.toutiaoexample.widget.CircleImageView;
import app.mrobot.cn.toutiaoexample.widget.IntentAction;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author fox.hu
 * @date 2018/8/27
 */

public class VideoContentHeaderViewBinder extends ItemViewBinder<MultiNewsArticleDataBean, VideoContentHeaderViewBinder.ViewHolder> {
    private static final String TAG = VideoContentHeaderViewBinder.class.getSimpleName();
    private boolean isShow = true;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_video_content_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,
            @NonNull MultiNewsArticleDataBean item) {
        String media_avatar_url = item.getMedia_info().getAvatar_url();
        if (!TextUtils.isEmpty(media_avatar_url)) {
            ImageLoader.loadCenterCrop(holder.itemView.getContext(), media_avatar_url,
                    holder.iv_media_avatar_url, R.mipmap.error_image);
        }

        String title = item.getTitle();
        String abstractX = item.getAbstractX();
        String source = item.getSource();

        int video_duration = item.getVideo_duration();
        String min = String.valueOf(video_duration / 60);
        String second = String.valueOf(video_duration % 10);
        if (Integer.parseInt(second) < 10) {
            second = "0" + second;
        }
        String tv_video_time = min + ":" + second;
        String tv_comment_count = item.getComment_count() + "";
        final String media_id = item.getMedia_info().getMedia_id();

        holder.tv_title.setText(title);
        holder.tv_tv_video_duration_str.setText(
                "时长 " + tv_video_time + " | " + tv_comment_count + "评论");
        holder.tv_abstract.setText(abstractX);
        holder.tv_source.setText(source);

        holder.rl_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    holder.ll_desc.animate().setDuration(200).alpha(0).translationY(
                            -holder.ll_desc.getHeight()).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            holder.ll_desc.setVisibility(View.GONE);
                            holder.iv_menu.setImageResource(R.drawable.ic_menu_down_gray_24dp);
                        }
                    }).start();
                } else {
                    holder.ll_desc.animate().setDuration(200).alpha(1).translationY(0).setListener(
                            new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    holder.ll_desc.setVisibility(View.VISIBLE);
                                    holder.iv_menu.setImageResource(
                                            R.drawable.ic_menu_up_gray_24dp);
                                }
                            }).start();
                }
                isShow = !isShow;
            }
        });
        final String videoTitle = item.getTitle();
        final String shareUrl = item.getDisplay_url();
        holder.ll_share.setOnClickListener(
                v -> IntentAction.send(holder.itemView.getContext(), videoTitle + "\n" + shareUrl));

        holder.ll_dl.setOnClickListener(v -> {
            // TODO 下载视频
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_tv_video_duration_str;
        private TextView tv_abstract;
        private TextView tv_source;
        private CircleImageView iv_media_avatar_url;
        private ImageView iv_menu;
        private LinearLayout media_layout;
        private LinearLayout ll_desc;
        private RelativeLayout rl_title;
        private LinearLayout ll_share;
        private LinearLayout ll_dl;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_tv_video_duration_str = itemView.findViewById(R.id.tv_tv_video_duration_str);
            this.tv_abstract = itemView.findViewById(R.id.tv_abstract);
            this.tv_source = itemView.findViewById(R.id.tv_extra);
            this.iv_media_avatar_url = itemView.findViewById(R.id.iv_media_avatar_url);
            this.iv_menu = itemView.findViewById(R.id.iv_menu);
            this.media_layout = itemView.findViewById(R.id.media_layout);
            this.ll_desc = itemView.findViewById(R.id.ll_desc);
            this.rl_title = itemView.findViewById(R.id.rl_title);
            this.ll_share = itemView.findViewById(R.id.ll_share);
            this.ll_dl = itemView.findViewById(R.id.ll_dl);
        }
    }
}
