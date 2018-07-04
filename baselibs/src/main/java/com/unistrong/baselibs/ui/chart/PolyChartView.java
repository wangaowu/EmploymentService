package com.unistrong.baselibs.ui.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;

/**
 * 绘制折线图
 */
public class PolyChartView extends BaseChart {
    private static final int STROKE_WIDTH = 3;

    public PolyChartView(Context context) {
        super(context);
    }

    public PolyChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PolyChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!measureComplete()) return;
        //绘制折线
        resetPaint();
        drawPolyLines(canvas);
        //绘制拐点
        resetPaint();
        drawCircleRings(canvas);
    }

    private void drawCircleRings(Canvas canvas) {
        int outRadius = DensityUtils.dp2px(getContext(), STROKE_WIDTH + 2);
        int innerRadius = DensityUtils.dp2px(getContext(), STROKE_WIDTH);
        for (int i = 0; i < elementRectFs.size(); i++) {
            float centerX = elementRectFs.get(i).centerX();
            float top = elementRectFs.get(i).top;
            paint.setColor(getContext().getResources().getColor(R.color.global_orange));
            canvas.drawCircle(centerX, top, outRadius, paint);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(centerX, top, innerRadius, paint);
        }
    }

    private void drawPolyLines(Canvas canvas) {
        int width = DensityUtils.dp2px(getContext(), STROKE_WIDTH);

        paint.setColor(getContext().getResources().getColor(R.color.global_orange));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        canvas.drawPath(getPolyLinePath(), paint);
    }

    private Path getPolyLinePath() {
        Path path = new Path();
        for (int i = 0; i < elementRectFs.size(); i++) {
            float centerX = elementRectFs.get(i).centerX();
            float top = elementRectFs.get(i).top;
            if (i == 0) {
                path.moveTo(centerX, top);
            } else {
                path.lineTo(centerX, top);
            }
        }
        return path;
    }


}
