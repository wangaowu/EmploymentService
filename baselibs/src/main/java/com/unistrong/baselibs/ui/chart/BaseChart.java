package com.unistrong.baselibs.ui.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.unistrong.baselibs.utils.DensityUtils;

/**
 * 绘制坐标轴及文字
 */
public class BaseChart extends BaseMeasure {

    protected Paint paint = new Paint();

    public BaseChart(Context context) {
        this(context, null);
    }

    public BaseChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        initPaint();
    }

    private void initPaint() {
        paint.setAntiAlias(true);
        paint.setColor(Color.DKGRAY);
    }

    protected void resetPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!measureComplete()) return;
        //绘制水平基线
        resetPaint();
        drawHorizontalBaseLine(canvas);
        //绘制垂直轴文字
        resetPaint();
        drawVerticalBaseFlag(canvas);
        //绘制水平轴文字
        resetPaint();
        int dynamicStep = drawHorizontalBaseFlag(canvas);
        //绘制数据的指示线
        resetPaint();
        drawDataIndicatorLines(canvas, dynamicStep);
    }

    private int drawHorizontalBaseFlag(Canvas canvas) {
        int textSize = DensityUtils.dp2px(getContext(), 14);
        int paddingText = DensityUtils.dp2px(getContext(), 5);
        float baseLine = elementRectFs.get(0).bottom + paddingText + textSize;

        paint.setTextSize(textSize);
        int dynamicStep = getDynamicStep(paint, "1970-00-00");
        paint.setColor(Color.LTGRAY);
        paint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < elementRectFs.size(); i += dynamicStep) {
            float centerX = elementRectFs.get(i).centerX();
            String text = bindDatas.get(i).flagX;
            canvas.drawText(text, centerX, baseLine, paint);
        }
        return dynamicStep;
    }

    private void drawVerticalBaseFlag(Canvas canvas) {
        int paddingText = DensityUtils.dp2px(getContext(), 5);
        int textSize = DensityUtils.dp2px(getContext(), 14);
        int yStepValue = (maxValue / (ANXIUS_Y_COUNT - 1));
        float yDistance = chartRectF.height() / (ANXIUS_Y_COUNT - 1);
        float x = elementRectFs.get(0).left - paddingText;

        paint.setColor(Color.LTGRAY);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(textSize);
        for (int i = 0; i < ANXIUS_Y_COUNT; i++) {
            int yValue = yStepValue * (ANXIUS_Y_COUNT - 1 - i);
            float baseLine = chartRectF.top + yDistance * i + +textSize / 4;
            canvas.drawText(getDynamicYFlag(yValue), x, baseLine, paint);
        }
    }

    private void drawHorizontalBaseLine(Canvas canvas) {
        float yDistance = chartRectF.height() / (ANXIUS_Y_COUNT - 1);
        float startX = elementRectFs.get(0).left;
        float endX = elementRectFs.get(elementRectFs.size() - 1).right;

        int textSize = DensityUtils.dp2px(getContext(), 14);
        paint.setColor(Color.LTGRAY);
        paint.setTextSize(textSize);
        for (int i = 0; i < ANXIUS_Y_COUNT; i++) {
            float startY = chartRectF.top + yDistance * i;
            canvas.drawLine(startX, startY, endX, startY, paint);
        }
    }

    private void drawDataIndicatorLines(Canvas canvas, int dynamicStep) {
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        int dashInterval = DensityUtils.dp2px(getContext(), 10);
        paint.setPathEffect(new DashPathEffect(new float[]{dashInterval, dashInterval}, 4f));
        for (int i = 0; i < elementRectFs.size(); i += dynamicStep) {
            RectF elementRect = elementRectFs.get(i);
            float centerX = elementRect.centerX();
            canvas.drawLine(centerX, elementRect.top, centerX, elementRect.bottom, paint);
        }
    }
}
