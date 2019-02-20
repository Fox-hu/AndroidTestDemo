package app.mrobot.cn.drawabletest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import app.mrobot.cn.R;

public class LoweButton extends android.support.v7.widget.AppCompatButton {
    /**
     * 图片比例. 比例=宽/高
     */
    private float mRatio;

    public LoweButton(Context context) {
        this(context, null);
    }

    public LoweButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoweButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     * @param attrs   属性
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoweButton);
        mRatio = typedArray.getFloat(R.styleable.LoweButton_ratio, 0);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 宽模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 宽大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 高大小
        int heightSize;
        // 只有宽的值是精确的才对高做精确的比例校对
        if (widthMode == MeasureSpec.EXACTLY && mRatio > 0) {
            heightSize = (int) (widthSize / mRatio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
