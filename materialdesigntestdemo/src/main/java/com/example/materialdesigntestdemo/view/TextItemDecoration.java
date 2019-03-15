package com.example.materialdesigntestdemo.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author fox.hu
 */
public class TextItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Paint textPaint;

    public TextItemDecoration() {
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.YELLOW);
        this.textPaint = new Paint();
        this.textPaint.setColor(Color.BLACK);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 50, 0, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //        final int childCount = parent.getChildCount();
        //        for (int i = 0; i < childCount; i++) {
        //            //底部实验
        //            final View child = parent.getChildAt(i);
        //
        //            int index = parent.getChildAdapterPosition(child);
        //
        //            if (index % 2 == 0) {
        //                int left = 0;
        //                int top = child.getTop() - 50;
        //                int right = child.getRight();
        //                int bottom = child.getTop();
        //                c.drawRect(left, top, right, bottom, mPaint);
        //            }
        //        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);

            if (i == 1 && child.getTop() < 100) {
                int left = 0;
                int top = child.getTop() - 100;
                int right = child.getRight();
                int bottom = child.getTop() - 50;
                c.drawRect(left, top, right, bottom, mPaint);
                c.drawText("视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left,
                        top + 50, textPaint);
            } else {
                int left = 0;
                int top = 0;
                int right = child.getRight();
                int bottom = 50;
                c.drawRect(left, top, right, bottom, mPaint);
                if (i == 0) {
                    c.drawText("视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left,
                            top + 50, textPaint);
                } else {
                    c.drawText("视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left,
                            top + 50, textPaint);
                }
            }

            if (i != 0) {
                int left = 0;
                int top = child.getTop() - 50;
                int right = child.getRight();
                int bottom = child.getTop();
                c.drawRect(left, top, right, bottom, mPaint);
                c.drawText("视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left,
                        top + 50, textPaint);
            }
        }
    }

}
