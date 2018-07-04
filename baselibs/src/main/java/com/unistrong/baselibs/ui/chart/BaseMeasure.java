package com.unistrong.baselibs.ui.chart;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.unistrong.baselibs.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义view计算基类
 */
public class BaseMeasure extends View {
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 100;
    private static final int PADDING_LEFT = 40;
    private static final int PADDING_RIGHT = 20;
    private static final int PADDING_VERTICAL = 20;
    protected static final int ANXIUS_Y_COUNT = 6; //Y轴坐标的个数

    private int viewWidth;
    private int viewHeight;
    private int paddingLeft;
    private int paddingRight;
    private int paddingVertical;
    protected List<RectF> elementRectFs = new ArrayList<>();
    protected RectF chartRectF;

    protected List<BindData> bindDatas;
    protected int maxValue;

    public BaseMeasure(Context context) {
        super(context);
    }

    public BaseMeasure(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseMeasure(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(float maxValue, List<BindData> datas) {
        if (datas == null) return;
        this.maxValue = invalidateMax(maxValue);
        for (int i = 0; i < datas.size(); i++) {
            BindData bindData = datas.get(i);
            bindData.yRatio = bindData.valueY / this.maxValue;
        }
        this.bindDatas = datas;
        scaleElementsRect();
        postInvalidate();
    }

    protected boolean measureComplete() {
        return chartRectF != null && bindDatas != null && !bindDatas.isEmpty();
    }

    /**
     * 取整最大值
     */
    private int invalidateMax(float maxValue) {
        if (maxValue < 10) return 10;
        int step = (ANXIUS_Y_COUNT - 1) * 10;
        return (((int) maxValue) / step + 1) * step;
    }

    private void scaleElementsRect() {
        //没有数据or测量未完成
        if (!measureComplete()) return;
        elementRectFs.clear();
        int xDistance = (viewWidth - paddingLeft - paddingRight) / bindDatas.size();
        int index = 0;
        for (BindData bindData : bindDatas) {
            float left = paddingLeft + index * xDistance;
            float top = (1 - bindData.yRatio) * (chartRectF.height()) + chartRectF.top;
            float bottom = chartRectF.bottom;
            RectF elementRect = new RectF(left, top, left + xDistance, bottom);
            elementRectFs.add(elementRect);
            index++;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getSize(widthMeasureSpec, DensityUtils.dp2px(getContext(), MIN_WIDTH));
        viewHeight = getSize(heightMeasureSpec, DensityUtils.dp2px(getContext(), MIN_HEIGHT));
        initBounds();
        scaleElementsRect();
    }

    private void initBounds() {
        paddingLeft = DensityUtils.dp2px(getContext(), PADDING_LEFT) + getPaddingLeft();
        paddingRight = DensityUtils.dp2px(getContext(), PADDING_RIGHT) + getPaddingRight();
        paddingVertical = DensityUtils.dp2px(getContext(), PADDING_VERTICAL);
        chartRectF = new RectF(paddingLeft, paddingVertical, viewWidth - paddingRight, viewHeight - paddingVertical);
    }

    private int getSize(int sizeMeasureSpec, int atMost) {
        int size = 0;
        switch (MeasureSpec.getMode(sizeMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                size = MeasureSpec.getSize(sizeMeasureSpec);
                break;
            case MeasureSpec.UNSPECIFIED:
                size = atMost;
                DensityUtils.dp2px(getContext(), 30);
                break;
        }
        return size;
    }

    protected int getDynamicStep(Paint paint, String measureText) {
        float textWidth = paint.measureText(measureText);
        int horPadding = DensityUtils.dp2px(getContext(), 8);
        textWidth = textWidth + 2 * horPadding;
        float count;
        if (textWidth > PADDING_LEFT) {
            //文字宽度>左间距,用控件尺寸计算
            count = viewWidth / textWidth;
        } else {
            count = chartRectF.width() / textWidth;
        }
        return (int) (elementRectFs.size() / count) + 1;
    }


    public static class BindData {
        /**
         * 传入构造
         *
         * @param valueY 竖轴的值
         * @param flagX  横轴的文字
         */
        public BindData(float valueY, String flagX) {
            this.valueY = valueY;
            this.flagX = flagX;
        }

        public float x;
        public float yRatio;
        public float valueY;
        public String flagX;
        public String flagY;
        public Object data;
    }
}
