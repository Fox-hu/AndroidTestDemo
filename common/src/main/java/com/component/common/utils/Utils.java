package com.component.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Created by fox.hu on 2019/1/25.
 */

public class Utils {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE",
                                                   "android.permission.WRITE_EXTERNAL_STORAGE"};

    private static final String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};

    public static void setRv(RecyclerView rv, Context context) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(new NormalAdapter(Arrays.asList(Utils.data)));
    }

    public static void setNoScrollRv(RecyclerView rv, Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(new NormalAdapter(Arrays.asList(Utils.data)));
    }


    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * dp转px
     */
    public static int dip2px(float dpValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * view是否在页面上可见
     *
     * @param view
     * @return
     */
    public static boolean isVisible(View view) {
        Rect rect = new Rect();
        return view.getGlobalVisibleRect(rect);
    }

    /**
     * viewgroup设置margin
     *
     * @param
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * viewgroup设置margin
     *
     * @param
     */
    public static void setTopMargins(View v, int t) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(0, t, 0, 0);
            v.requestLayout();
        }
    }

    public static void saveBitmapFile(Bitmap bitmap, String name) {
        File file = new File(Environment.getExternalStorageDirectory(), name);//将要保存图片的路径

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap createTextImage(int width, int height, int txtSize, String innerTxt) {
        //若使背景为透明，必须设置为Bitmap.Config.ARGB_4444
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bm);

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(txtSize);

        //计算得出文字的绘制起始x、y坐标
        int posX = width / 2 - txtSize * innerTxt.length() / 2;
        int posY = height / 2 - txtSize / 2;

        canvas.drawText(innerTxt, posX, posY, paint);

        return bm;
    }
}
