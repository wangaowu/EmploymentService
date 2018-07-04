package com.unistrong.employmentservice.detail;

import com.unistrong.baselibs.ui.spanner.ItemTextView;
import com.unistrong.employmentservice.databinding.ActivityEmployeeDetailBinding;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.framwork.utils.DynamicDictUtils;
import com.unistrong.framwork.utils.VerifyGlide;

/**
 * 详细信息ViewModel
 */
public class EmployeeDetailViewModel {
    private ActivityEmployeeDetailBinding binding;

    private ItemTextView idcardView;
    private ItemTextView employmentModeView;
    private ItemTextView employmentChannelView;
    private ItemTextView employeeChangeTimeView;
    private ItemTextView workAddressView;
    private ItemTextView incomeView;
    private ItemTextView positionView;
    private ItemTextView relationNameView;
    private ItemTextView relationCompanyView;
    private ItemTextView employmentAreaView;

    public EmployeeDetailViewModel(ActivityEmployeeDetailBinding binding) {
        this.binding = binding;
    }

    public void initViews() {
        idcardView = new ItemTextView("身份证", "", binding.llContainer);
        employmentModeView = new ItemTextView("就业方式", "", binding.llContainer);
        employmentChannelView = new ItemTextView("就业渠道", "", binding.llContainer);
        employeeChangeTimeView = new ItemTextView("转移变更时间", "", binding.llContainer);

        employmentAreaView = new ItemTextView("就业区域", "", binding.llContainer);
        workAddressView = new ItemTextView("工作地点", "", binding.llContainer);
        incomeView = new ItemTextView("月收入", "", binding.llContainer);
        positionView = new ItemTextView("岗位", "", binding.llContainer);
        relationNameView = new ItemTextView("关联责任人", "", binding.llContainer);
        relationCompanyView = new ItemTextView("责任单位", "", binding.llContainer);

        idcardView.setHideDivider(true);
        employmentModeView.setHideDivider(true);
        employmentChannelView.setHideDivider(true);
        employeeChangeTimeView.setHideDivider(true);
        workAddressView.setHideDivider(true);
        incomeView.setHideDivider(true);
        positionView.setHideDivider(true);
        relationNameView.setHideDivider(true);
        relationCompanyView.setHideDivider(true);
        employmentAreaView.setHideDivider(true);
    }

    public void setViewData(ChangeListResp.ResultBean itemBean) {
        if (itemBean == null) return;
        idcardView.getRightView().setText(itemBean.getIdcard());
        employeeChangeTimeView.getRightView().setText(itemBean.getEmploymentChangeTime());
        workAddressView.getRightView().setText(itemBean.getEmploymentWorkPlace());
        incomeView.getRightView().setText(itemBean.getEmploymentMonthlyIncome());
        positionView.getRightView().setText(itemBean.getEmploymentPosition());
        relationNameView.getRightView().setText(itemBean.getResponsibilityUserName());
        relationCompanyView.getRightView().setText(itemBean.getResponsibilityUserOfficeName());
        //load image
        VerifyGlide.getInstance().load(itemBean.getImageUrl(), binding.ivIcon);
        binding.tvName.setText(itemBean.getName());
        //字典
        String mode = itemBean.getEmploymentMode();
        String channel = itemBean.getEmploymentChannel();
        String area = itemBean.getEmploymentArea();
        employmentModeView.getRightView().
                setText(DynamicDictUtils.getValue(mode, DynamicDictUtils.employeeModeList));
        employmentChannelView.getRightView().
                setText(DynamicDictUtils.getValue(channel, DynamicDictUtils.employeeChannelList));
        employmentAreaView.getRightView().
                setText(DynamicDictUtils.getValue(area, DynamicDictUtils.employeeAreaList));
    }
}
