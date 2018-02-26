package app.mrobot.cn.toutiaoexample.binder.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.utils.ImageLoader;
import app.mrobot.cn.toutiaoexample.utils.TimeUtil;
import app.mrobot.cn.toutiaoexample.widget.CircleImageView;
import app.mrobot.cn.toutiaoexample.widget.IntentAction;
import io.reactivex.functions.Consumer;
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
        View view = inflater.inflate(R.layout.item_news_article_img, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NewsArticleVideoViewBinder.ViewHolder holder,
            @NonNull final MultiNewsArticleDataBean item) {
        final Context context = holder.itemView.getContext();

        String imgUrl = "http://p3.pstatp.com/";
        List<MultiNewsArticleDataBean.ImageListBean> image_list = item.getImage_list();
        if (image_list != null && !image_list.isEmpty()) {
            String url = image_list.get(0).getUrl();
            ImageLoader.loadCenterCrop(context, url, holder.iv_image, R.color.viewBackground);
            if (!TextUtils.isEmpty(image_list.get(0).getUri())) {
                imgUrl += image_list.get(0).getUri().replace("list", "large");
            }
        }
        MultiNewsArticleDataBean.UserInfoBean user_info = item.getUser_info();
        if (user_info != null) {
            String avatar_url = user_info.getAvatar_url();
            if (!TextUtils.isEmpty(avatar_url)) {
                ImageLoader.loadCenterCrop(context, avatar_url, holder.iv_media,
                        R.color.viewBackground);
            }
        }

        String title = item.getTitle();
        String abstractX = item.getAbstractX();
        String source = item.getSource();
        String comment = item.getComment_count() + "评论";
        String datetime = item.getBehot_time() + "";
        if (!TextUtils.isEmpty(datetime)) {
            datetime = TimeUtil.getTimeStampAgo(datetime);
        }

        holder.tv_title.setText(title);
        holder.tv_abstract.setText(abstractX);
        holder.tv_extra.setText(source + " - " + comment + " - " + datetime);
        holder.iv_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.iv_dots, Gravity.END, 0,
                        R.style.MyPopupMenu);
                popupMenu.inflate(R.menu.menu_share);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.action_share) {
                            IntentAction.send(context,
                                    item.getTitle() + "\n" + item.getShare_url());
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

       final String finalImagUrl = imgUrl;
        RxView.clicks(holder.itemView).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
                //todo
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView iv_media;
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_abstract;
        private TextView tv_extra;
        private ImageView iv_dots;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_media = itemView.findViewById(R.id.iv_media);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_abstract = itemView.findViewById(R.id.tv_abstract);
            this.tv_extra = itemView.findViewById(R.id.tv_extra);
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
        }
    }
}
