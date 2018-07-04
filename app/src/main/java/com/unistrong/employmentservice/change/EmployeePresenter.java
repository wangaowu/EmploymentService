package com.unistrong.employmentservice.change;

import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class EmployeePresenter {

    /**
     * 保存编辑
     *
     * @param params
     * @param listener
     */
    public void saveEdit(HashMap params, ResponseBody listener) {
        HttpRequestImpl.getInstance().requestPost(Constant.Action.UPDATE_CHANGE_INFO, params, listener);
    }

    /**
     * 保存变更
     *
     * @param params
     * @param listener
     */
    public void saveChange(HashMap params, ResponseBody listener) {
        HttpRequestImpl.getInstance().requestPost(Constant.Action.ADD_CHANGE_INFO, params, listener);
    }
}
