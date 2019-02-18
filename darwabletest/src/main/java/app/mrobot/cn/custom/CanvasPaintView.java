package app.mrobot.cn.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasPaintView extends View {
    private Paint paint;
    float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};

    public CanvasPaintView(Context context) {
        this(context, null);
    }

    public CanvasPaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        //画圆
        canvas.drawCircle(300, 300, 200, paint);

        //画点
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(50, 50, paint);

        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */, 8 /* 一共绘制 8 个数（4 个点）*/, paint);

        //画椭圆
        canvas.drawOval(600, 50, 700, 300, paint);

        //drawArc() 是使用一个椭圆来描述弧形的。
        // left, top, right, bottom 描述的是这个弧形所在的椭圆；
        // startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
        // sweepAngle 是弧形划过的角度；useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
        //画扇形
        canvas.drawArc(200, 600, 800, 1100, -110, 100, true, paint);

        //画弧形
        canvas.drawArc(200, 600, 800, 1100, 20, 140, false, paint);

        // 绘制不封口的弧形
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(200, 600, 800, 1100, 180, 60, false, paint);
    }
}
