package com.unistrong.baselibs.ui.spanner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 右边选择时间样式
 */
public class ItemTimeView extends BaseItemView implements View.OnClickListener {
    private String PREFIX;
    private final Activity activity;

    private final View itemView;
    private final TextView tvLeft;
    private final TextView tvRight;
    private DatePickerDialog datePickerDialog;

//    private DateSelectDialog selTimeDialog;

    /**
     * @param left
     * @param timePrefix
     * @param container
     */
    public ItemTimeView(String left, String timePrefix, ViewGroup container) {
        this.PREFIX = timePrefix;
        this.activity = (Activity) container.getContext();
        int height = DensityUtils.dp2px(activity, ITEM_HEIGHT);

        itemView = View.inflate(activity, R.layout.common_text_item_with_flag_layout, null);
        container.addView(itemView, new LinearLayout.LayoutParams(-1, height));

        tvLeft = itemView.findViewById(R.id.tv_flag);
        tvRight = itemView.findViewById(R.id.tv_content);

        tvRight.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        tvLeft.setText(left);
        initRight();
    }

    @Override
    public View getLeftView() {
        return tvLeft;
    }

    @Override
    public String getLeftText() {
        return tvLeft.getText().toString();
    }

    @Override
    public TextView getRightView() {
        return tvRight;
    }

    @Override
    public String getRightText() {
        return tvRight.getText().toString();
    }

    @Override
    public String getRightKey() {
        return tvRight.getText().toString();
    }

    @Override
    public void setLeftContent(int id) {
        tvLeft.setText(id);
    }

    @Override
    public void initRight() {
        tvRight.setOnClickListener(this);
    }

    private String getCurrentYMD() {
        String format = new SimpleDateFormat("yyyy" + PREFIX + "MM" + PREFIX + "dd").format(new Date());
        return format;
    }

    @Override
    public void onClick(View v) {
        //点击右边
        showSelTimeDialog();
    }

    // 日期对话框
    private void showSelTimeDialog() {
        if (datePickerDialog == null) {
            String[] ymds = getCurrentYMD().split(PREFIX);
            int year = Integer.parseInt(ymds[0]);
            int monthOfYear = Integer.parseInt(ymds[1]) - 1;
            int dayOfMonth = Integer.parseInt(ymds[2]);
            datePickerDialog = new DatePickerDialog(
                    tvRight.getContext(),
                    DatePickerDialog.THEME_HOLO_LIGHT,
                    (view, year1, monthOfYear1, dayOfMonth1) -> {
                        tvRight.setText(year1 + PREFIX + ( monthOfYear1 + 1) + PREFIX + dayOfMonth1);
                        datePickerDialog.dismiss();
                    }, year, monthOfYear, dayOfMonth);
        }
        datePickerDialog.show();
    }
}
