package app.mrobot.cn.toutiaoexample.binder.news;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.NewsCommentBean;
import app.mrobot.cn.toutiaoexample.module.BaseActivity;
import app.mrobot.cn.toutiaoexample.utils.ImageLoader;
import app.mrobot.cn.toutiaoexample.widget.BottomSheetDialogFixed;
import app.mrobot.cn.toutiaoexample.widget.IntentAction;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by fox.hu on 2018/8/14.
 */

public class NewsCommentViewBinder extends ItemViewBinder<NewsCommentBean.DataBean.CommentBean,NewsCommentViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_comments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,
            @NonNull NewsCommentBean.DataBean.CommentBean item) {
        final Context context = holder.itemView.getContext();
        try {
            String iv_avatar = item.getUser_profile_image_url();
            String tv_username = item.getUser_name();
            String tv_text = item.getText();
            int tv_likes = item.getDigg_count();

            ImageLoader.loadCenterCrop(context, iv_avatar, holder.iv_avatar, R.color.viewBackground);
            holder.tv_username.setText(tv_username);
            holder.tv_text.setText(tv_text);
            holder.tv_likes.setText(tv_likes + "赞");
            holder.itemView.setOnClickListener(v -> {
                final String content = item.getText();
                final BottomSheetDialogFixed dialog = new BottomSheetDialogFixed(context);
                dialog.setOwnerActivity((BaseActivity) context);
                View view = ((BaseActivity) context).getLayoutInflater().inflate(R.layout.item_comment_action_sheet, null);
                view.findViewById(R.id.layout_copy_text).setOnClickListener(view12 -> {
                    ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("text", content);
                    copy.setPrimaryClip(clipData);
                    Snackbar.make(holder.itemView, R.string.copied_to_clipboard, Snackbar.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
                view.findViewById(R.id.layout_share_text).setOnClickListener(view1 -> {
                    IntentAction.send(context, content);
                    dialog.dismiss();
                });
                dialog.setContentView(view);
                dialog.show();
            });
        } catch (Exception e) {

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_avatar;
        private TextView tv_username;
        private TextView tv_text;
        private TextView tv_likes;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_avatar = itemView.findViewById(R.id.iv_avatar);
            this.tv_username = itemView.findViewById(R.id.tv_username);
            this.tv_text = itemView.findViewById(R.id.tv_text);
            this.tv_likes = itemView.findViewById(R.id.tv_likes);
        }
    }
}
