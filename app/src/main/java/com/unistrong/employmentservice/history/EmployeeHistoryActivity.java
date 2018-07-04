package com.unistrong.employmentservice.history;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.TextView;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.NumberUtils;
import com.unistrong.baselibs.utils.ViewFactory;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.ActivityEmployeeHistoryBinding;
import com.unistrong.employmentservice.detail.EmployeeDetailActivity;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.framwork.utils.VerifyGlide;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 人员历史
 */
public class EmployeeHistoryActivity extends BaseActivity {
    private ChangeListResp.ResultBean itemBean;
    private ActivityEmployeeHistoryBinding binding;

    @Override
    protected void initMvp() {
        initIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_history);
        initViews();
        initData();
    }

    private void initData() {
        if (itemBean == null) return;
        VerifyGlide.getInstance().load(itemBean.getImageUrl(), binding.ivIcon);
        binding.tvIdcard.setText("身份证号 " + itemBean.getIdcard());
        binding.tvName.setText(itemBean.getName());
        requestHistoryData();
    }

    private void requestHistoryData() {
        HashMap<String, String> param = new HashMap<>();
        param.put("currentPage", "1");
        param.put("pageSize", "100");
        param.put("personId", itemBean.getPersonId());
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_HISTORY, param,
                new ResponseBody<ChangeListResp>(ChangeListResp.class) {
                    @Override
                    public void onFailure(String message) {
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(ChangeListResp resp) {
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        setHistoryViewsData(resp.getResult());
                    }
                });
    }

    private void setHistoryViewsData(List<ChangeListResp.ResultBean> list) {
        binding.llContainer.removeAllViews();
        if (list == null || list.isEmpty()) {
            View emptyView = ViewFactory.createEmptyView("暂无变更历史", 100, this);
            binding.llContainer.addView(emptyView);
            return;
        }
        for (ChangeListResp.ResultBean bean : list) {
            View itemView = getItemView(bean);
            binding.llContainer.addView(itemView);
        }
    }

    private View getItemView(ChangeListResp.ResultBean bean) {
        View inflate = View.inflate(this, R.layout.item_history_layout, null);
        TextView tvJob = inflate.findViewById(R.id.tv_job);
        TextView tvUpdateTime = inflate.findViewById(R.id.tv_change_time);
        TextView tvAddress = inflate.findViewById(R.id.tv_address);
        inflate.setOnClickListener(v -> startChangeDetailActivity(bean));
        //set_data
        tvJob.setText(bean.getEmploymentPosition() +
                getIncomeText(bean.getEmploymentMonthlyIncome()));
        tvUpdateTime.setText(bean.getEmploymentChangeTime());
        tvAddress.setText(bean.getHouseholdAddress());
        return inflate;
    }

    private void startChangeDetailActivity(ChangeListResp.ResultBean bean) {
        Intent intent = new Intent(this, EmployeeDetailActivity.class);
        intent.putExtra(EmployeeDetailActivity.INTENT_KEY, bean);
        intent.putExtra(EmployeeDetailActivity.SHOW_TYPE, EmployeeDetailActivity.TYPE_HISTORY_DETAIL);
        EmployeeHistoryActivity.this.startActivity(intent);
    }

    private String getIncomeText(String income) {
        try {
            Integer income_ = Integer.valueOf(income);
            return NumberUtils.keepPrecision(income_ * .001f, 2) + "k";
        } catch (Exception e) {
        }
        return "";
    }

    private void initViews() {
        binding.layoutTitle.tvTitle.setText("变更历史");
        setHistoryViewsData(null);
    }

    private void initIntent() {
        itemBean = (ChangeListResp.ResultBean) getIntent().
                getSerializableExtra(EmployeeDetailActivity.INTENT_KEY);
    }
}
