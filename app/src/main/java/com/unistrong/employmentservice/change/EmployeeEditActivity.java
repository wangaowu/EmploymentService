package com.unistrong.employmentservice.change;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.unistrong.baselibs.style.Activity_;
import com.unistrong.baselibs.style.AndroidBug5497Workaround;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.ActivityEditEmployeeDetailBinding;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.framwork.resp.StateResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

/**
 * 变更页面
 */
public class EmployeeEditActivity extends BaseActivity {
    public static final String INTENT_KEY = "itemBean";
    public static final String SHOW_TYPE = "showType";

    public static final int TYPE_EDIT = 1;
    public static final int TYPE_CHANGE = 2;

    private ActivityEditEmployeeDetailBinding binding;
    private EmployeeEditViewModel viewModel;
    private ChangeListResp.ResultBean itemBean;
    private EmployeePresenter presenter;
    private int showType;

    @Override
    protected void initMvp() {
        initIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_employee_detail);
        AndroidBug5497Workaround.assistActivity(findViewById(android.R.id.content));
        viewModel = new EmployeeEditViewModel(binding);
        presenter = new EmployeePresenter();
        initViews();
        initData();
    }

    private void initData() {
        viewModel.setViewData(itemBean);
    }

    private void initIntent() {
        itemBean = (ChangeListResp.ResultBean) getIntent().getSerializableExtra(INTENT_KEY);
        showType = getIntent().getIntExtra(SHOW_TYPE, TYPE_EDIT);
    }

    private void initViews() {
        binding.layoutTitle.tvTitle.setText(showType == TYPE_EDIT ?
                "编辑就业信息" : "变更就业信息");
        binding.layoutTitle.tvRight.setVisibility(View.VISIBLE);
        binding.layoutTitle.tvRight.setText("保存");
        binding.layoutTitle.tvRight.setOnClickListener(v -> clickSave());
        viewModel.initViews();
    }

    //通知辖区管理列表更新
    private void notifyManageListChanged() {
        Activity_ activity_ = getActivityByTag(MainActivity.TAG);
        if (activity_ instanceof MainActivity) {
            ((MainActivity) activity_).refreshManageFragmentList();
        }
    }

    //点击保存
    private void clickSave() {
        if (!viewModel.isOk()) {
            //表单校验失败
            return;
        }
        switch (showType) {
            case TYPE_EDIT://编辑
                HashMap params = viewModel.composeParams(itemBean);
                createLoadingDialog(false);
                presenter.saveEdit(params, new ResponseBody<StateResp>(StateResp.class) {
                    @Override
                    public void onFailure(String message) {
                        closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(StateResp resp) {
                        closeLoadingDialog();
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        notifyManageListChanged();
                        IToast.toast("编辑成功");
                        finish();
                    }
                });
                break;
            case TYPE_CHANGE://变更
                HashMap params_ = viewModel.composeParams(itemBean);
                createLoadingDialog(false);
                presenter.saveChange(params_, new ResponseBody<StateResp>(StateResp.class) {
                    @Override
                    public void onFailure(String message) {
                        closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(StateResp resp) {
                        closeLoadingDialog();
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        notifyManageListChanged();
                        IToast.toast("变更成功");
                        finish();
                    }
                });
                break;
        }
    }
}
