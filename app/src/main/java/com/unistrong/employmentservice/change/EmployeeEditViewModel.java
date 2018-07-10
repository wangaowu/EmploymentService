package com.unistrong.employmentservice.change;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;

import com.unistrong.baselibs.ui.spanner.ItemCheckView;
import com.unistrong.baselibs.ui.spanner.ItemEditView;
import com.unistrong.baselibs.ui.spanner.ItemTextView;
import com.unistrong.baselibs.ui.spanner.ItemTimeView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.StringUtils;
import com.unistrong.employmentservice.databinding.ActivityEditEmployeeDetailBinding;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.framwork.utils.DynamicDictUtils;

import java.util.HashMap;

/**
 * 详细信息
 */
public class EmployeeEditViewModel {
    private ActivityEditEmployeeDetailBinding binding;

    private ItemEditView idcardView;
    private ItemCheckView employmentModeView;
    private ItemCheckView employmentChannelView;
    private ItemTimeView employeeChangeTimeView;
    private ItemEditView workAddressView;
    private ItemEditView incomeView;
    private ItemEditView positionView;
    private ItemCheckView responseNameView;
    private ItemCheckView employmentAreaView;
    private ItemTextView nameView;

    public EmployeeEditViewModel(ActivityEditEmployeeDetailBinding binding) {
        this.binding = binding;
    }

    public void initViews() {
        nameView = new ItemTextView("姓名", "", binding.llContainer);
        nameView.getRightView().setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        idcardView = new ItemEditView("身份证", "", binding.llContainer);
        employmentModeView = new ItemCheckView(
                "就业方式", binding.llContainer, "请选择就业方式");
        employmentChannelView = new ItemCheckView(
                "就业渠道", binding.llContainer, "请选择就业渠道");
        employeeChangeTimeView = new ItemTimeView(
                "转移变更时间", "-", binding.llContainer);
        employmentAreaView = new ItemCheckView(
                "就业区域", binding.llContainer, "请选择就业区域");
        workAddressView = new ItemEditView("工作地点", "", binding.llContainer);
        incomeView = new ItemEditView("月收入", "", binding.llContainer);
        positionView = new ItemEditView("岗位", "", binding.llContainer);
        responseNameView = new ItemCheckView(
                "关联责任人", binding.llContainer, "请选择责任人");
        employmentModeView.setElements(
                DynamicDictUtils.getValueArray(DynamicDictUtils.employeeModeList));
        employmentChannelView.setElements(
                DynamicDictUtils.getValueArray(DynamicDictUtils.employeeChannelList));
        employmentAreaView.setElements(
                DynamicDictUtils.getValueArray(DynamicDictUtils.employeeAreaList));
        responseNameView.setElements(DynamicDictUtils.getUserArrays());

        //限制输入类型
        idcardView.getRightView().setFocusable(false);
        idcardView.getRightView().setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        incomeView.getRightView().setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }

    public void setViewData(ChangeListResp.ResultBean itemBean) {
        if (itemBean == null) return;
        nameView.getRightView().setText(itemBean.getName());
        idcardView.getRightView().setText(itemBean.getIdcard());
        employeeChangeTimeView.getRightView().setText(itemBean.getEmploymentChangeTime());
        workAddressView.getRightView().setText(itemBean.getEmploymentWorkPlace());
        incomeView.getRightView().setText(itemBean.getEmploymentMonthlyIncome());
        positionView.getRightView().setText(itemBean.getEmploymentPosition());
        responseNameView.getRightView().setText(itemBean.getResponsibilityUserName());
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

    /**
     * 组装view请求参数
     */
    public HashMap composeParams(ChangeListResp.ResultBean srcBean) {
        HashMap<String, String> params = new HashMap<>();
        params.put("personId", srcBean.getPersonId());//c
        params.put("employmentArea", workAddressView.getRightText());//ec
        params.put("employmentChangeTime", employeeChangeTimeView.getRightText());//ec
        params.put("employmentId", srcBean.getEmploymentId());//e
        params.put("employmentMonthlyIncome", incomeView.getRightText());//ec
        params.put("employmentPosition", positionView.getRightText());//ec
        params.put("employmentWorkPlace", workAddressView.getRightText());//ec
        //字典
        String channel = employmentChannelView.getRightText();
        String mode = employmentModeView.getRightText();
        String area = employmentAreaView.getRightText();
        params.put("employmentChannel", DynamicDictUtils.getKey(
                channel, DynamicDictUtils.employeeChannelList));//ec
        params.put("employmentMode", DynamicDictUtils.getKey(
                mode, DynamicDictUtils.employeeModeList));//ec
        params.put("employmentArea", DynamicDictUtils.getKey(
                area, DynamicDictUtils.employeeAreaList));
        //责任人
        String responseId = DynamicDictUtils.getUserId(responseNameView.getRightText());
        params.put("responsibilityUserId", responseId);//c
        params.put("dataSource", "APP");//c
        return params;
    }

    public boolean isOk() {
        if (StringUtils.IdentifyCard.isCorrect(idcardView.getRightText())) {
            IToast.toast("请检查身份证！");
            return false;
        }
        if (TextUtils.isEmpty(employmentModeView.getRightText())
                || "-1".equals(DynamicDictUtils.getKey(
                employmentModeView.getRightText(), DynamicDictUtils.employeeModeList))) {
            IToast.toast("请检查就业方式！");
            return false;
        }
        if (TextUtils.isEmpty(employmentChannelView.getRightText())
                || "-1".equals(DynamicDictUtils.getKey(
                employmentChannelView.getRightText(), DynamicDictUtils.employeeChannelList))) {
            IToast.toast("请检查就业渠道！");
            return false;
        }
        if (TextUtils.isEmpty(employeeChangeTimeView.getRightText())) {
            IToast.toast("请检查转移变更时间！");
            return false;
        }
        if (TextUtils.isEmpty(employmentAreaView.getRightText())
                || "-1".equals(DynamicDictUtils.getKey(
                employmentAreaView.getRightText(), DynamicDictUtils.employeeAreaList))) {
            IToast.toast("请检查就业区域！");
            return false;
        }
        if (TextUtils.isEmpty(workAddressView.getRightText())) {
            IToast.toast("请检查工作地点！");
            return false;
        }
        if (TextUtils.isEmpty(incomeView.getRightText())) {
            IToast.toast("请检查就月收入！");
            return false;
        }
        if (TextUtils.isEmpty(positionView.getRightText())) {
            IToast.toast("请检查岗位！");
            return false;
        }
        if (TextUtils.isEmpty(responseNameView.getRightText())) {
            IToast.toast("请检查关联责任人！");
            return false;
        }
        return true;
    }
}
