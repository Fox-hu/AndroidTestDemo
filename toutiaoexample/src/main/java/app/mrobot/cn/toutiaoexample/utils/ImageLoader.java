package app.mrobot.cn.toutiaoexample.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by fox on 2018/2/25.
 */

public class ImageLoader {

    public static void loadCenterCrop(Context context, String url, ImageView view,
            int defaultResId) {
        Glide.with(context).load(url).crossFade().centerCrop().into(view);
    }

    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId,
            int errorResId) {
        Glide.with(context).load(url).crossFade().centerCrop().error(errorResId).into(view);
    }
}
