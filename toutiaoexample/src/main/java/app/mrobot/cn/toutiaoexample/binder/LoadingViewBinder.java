package app.mrobot.cn.toutiaoexample.binder;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by fox on 2018/2/27.
 */

public class LoadingViewBinder extends ItemViewBinder<LoadingBean, LoadingViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected LoadingViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull LoadingViewBinder.ViewHolder holder,
            @NonNull LoadingBean item) {
        int defaultColor = InitApp.sContext.getResources().getColor(R.color.colorPrimary);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrap = DrawableCompat.wrap(holder.progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrap, defaultColor);
            holder.progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrap));
        } else {
            holder.progressBar.getIndeterminateDrawable().setColorFilter(defaultColor,
                    PorterDuff.Mode.SRC_IN);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            this.progressBar = itemView.findViewById(R.id.progress_footer);
        }
    }
}
