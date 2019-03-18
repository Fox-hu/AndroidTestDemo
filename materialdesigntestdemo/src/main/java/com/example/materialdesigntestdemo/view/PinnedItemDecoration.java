package com.example.materialdesigntestdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.example.materialdesigntestdemo.R;

/**
 * @author fox.hu
 */
public class PinnedItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Paint mTextPaint;
    private GroupInfoCallback callback;

    private int headerHeight;
    private int dividerHeight;
    private int textSize;

    private float mTextOffsetX;

    private Paint.FontMetrics mFontMetrics;

    public interface GroupInfoCallback {

        GroupInfo getGroupInfo(int position);
    }

    public PinnedItemDecoration(Context context, GroupInfoCallback callback) {
        this.callback = callback;
        dividerHeight = context.getResources().getDimensionPixelOffset(
                R.dimen.header_divider_height);
        headerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        textSize = context.getResources().getDimensionPixelSize(R.dimen.header_textsize);
        headerHeight = (int) Math.max(headerHeight,textSize);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(textSize);
        mFontMetrics = mTextPaint.getFontMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (callback != null) {
            final GroupInfo groupInfo = callback.getGroupInfo(childAdapterPosition);
            if (groupInfo != null && groupInfo.isFirstViewInGroup()) {
                outRect.top = headerHeight;
            } else {
                outRect.top = dividerHeight;
            }
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);

            if (callback != null) {
                GroupInfo groupInfo = callback.getGroupInfo(index);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();


                if (i == 0) {
                    int top = parent.getPaddingTop();
                    if (groupInfo.isLastViewInGroup()) {
                        int suggestTop = child.getBottom() - headerHeight;
                        if (suggestTop < top) {
                            top = suggestTop;
                        }
                    }
                    int bottom = top + headerHeight;

                    drawHeaderRect(c, groupInfo, left, top, right, bottom);

                } else {

                    if (groupInfo.isFirstViewInGroup()) {
                        int top = child.getTop() - headerHeight;
                        int bottom = child.getTop();
                        drawHeaderRect(c, groupInfo, left, top, right, bottom);
                    }
                }
            }
        }
    }

    private void drawHeaderRect(Canvas c, GroupInfo groupinfo, int left, int top, int right,
            int bottom) {
        //绘制Header
        c.drawRect(left, top, right, bottom, mPaint);

        float titleX = left + mTextOffsetX;
        float titleY = bottom - mFontMetrics.descent;
        //绘制Title
        c.drawText(groupinfo.getTitle(), titleX, titleY, mTextPaint);
    }


}
