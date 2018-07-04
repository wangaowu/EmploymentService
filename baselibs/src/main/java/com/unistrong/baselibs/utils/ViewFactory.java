package com.unistrong.baselibs.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 工厂类
 */
public class ViewFactory {
    private static final String TAG = "ViewFactory";

    /**
     * 创建空白view
     *
     * @param text    展示文字
     * @param height  高度/dp
     * @param context 上下文
     * @return
     */
    public static View createEmptyView(String text, int height, Context context) {
        TextView textView = new TextView(context);
        textView.setTextColor(Color.LTGRAY);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.WHITE);
        textView.setText(text);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, DensityUtils.dp2px(context, height));
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /**
     * @param self
     * @param emptyView
     * @param isEmpty
     */
    public static void switchViews(View self, View emptyView, boolean isEmpty) {
        try {
            ViewGroup parent = (ViewGroup) self.getParent();
            if (isEmpty) {
                parent.removeView(self);
                parent.addView(emptyView);
            } else {
                parent.removeView(emptyView);
                parent.addView(self);
            }
        } catch (Exception e) {
            Log.e(TAG, "switchViews: " + e.toString());
        }
    }


}
