package com.example.materialdesigntestdemo;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fox.hu on 2019/1/25.
 */

public class Utils {
    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * view是否在页面上可见
     * @param view
     * @return
     */
    public static boolean isVisible(View view) {
        Rect rect = new Rect();
        return view.getGlobalVisibleRect(rect);
    }

    /**
     * viewgroup设置margin
     * @param
     */
    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
    /**
     * viewgroup设置margin
     * @param
     */
    public static void setTopMargins (View v, int t) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(0, t, 0, 0);
            v.requestLayout();
        }
    }
}
