package com.unistrong.baselibs.ui.chart;

import android.graphics.Canvas;

/**
 * 图表绘制抽象
 */
public interface IChart {

    void drawHorizontalBaseLine(Canvas canvas);

    void drawVerticalBaseFlag(Canvas canvas);

    void drawHorizontalBaseFlag(Canvas canvas);

    void drawPolyLines(Canvas canvas);

    void drawCircleRings(Canvas canvas);
}
