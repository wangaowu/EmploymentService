package com.unistrong.employmentservice.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.ActivityEmployeeDetailBinding;
import com.unistrong.employmentservice.history.EmployeeHistoryActivity;
import com.unistrong.framwork.resp.ChangeListResp;

/**
 * 详细资料
 */
public class EmployeeDetailActivity extends BaseActivity {
    public static final String INTENT_KEY = "itemBean";
    public static final String SHOW_TYPE = "showType";
    public static final int TYPE_INIT_DETAIL = 1;
    public static final int TYPE_HISTORY_DETAIL = 2;

    private ActivityEmployeeDetailBinding binding;
    private EmployeeDetailViewModel viewModel;
    private ChangeListResp.ResultBean itemBean;
    private int showType;

    @Override
    protected void initMvp() {
        initIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_detail);
        viewModel = new EmployeeDetailViewModel(binding);
        initViews();
        viewModel.setViewData(itemBean);
    }

    private void initIntent() {
        itemBean = (ChangeListResp.ResultBean) getIntent().getSerializableExtra(INTENT_KEY);
        showType = getIntent().getIntExtra(SHOW_TYPE, TYPE_INIT_DETAIL);
    }

    private void initViews() {
        binding.layoutTitle.tvTitle.setText("详细资料");
        binding.tvHistory.setOnClickListener(v -> clickHistory());
        viewModel.initViews();
        binding.tvHistory.setVisibility(showType == TYPE_INIT_DETAIL ? View.VISIBLE : View.GONE);
    }

    //点击历史
    private void clickHistory() {
        Intent intent = new Intent(this, EmployeeHistoryActivity.class);
        intent.putExtra(INTENT_KEY, itemBean);
        startActivity(intent);
    }
}
