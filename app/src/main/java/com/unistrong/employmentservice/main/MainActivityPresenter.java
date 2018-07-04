package com.unistrong.employmentservice.main;

import android.content.Context;

import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class MainActivityPresenter {

    /**
     * 请求基本信息
     *
     * @param listener
     */
    public void requestSummary(ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_SUMMARY, map, listener);
    }

    /**
     * 请求变更列表
     *
     * @param name        姓名
     * @param idcard      身份证号
     * @param currentPage 当前页
     * @param listener
     */
    public void requestChangeList(String name, String idcard, Context context, int currentPage, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", String.valueOf(currentPage));
        map.put("pageSize", "15");
        map.put("name", name);
        map.put("idcard", idcard);
        map.put("userId", SPUtils.getString(context, Constant.SP_KEY.USER_ID));
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_CHANGE_LIST, map, listener);
    }

    /**
     * 请求表格数据
     *
     * @param listener
     */
    public void requestChartData(ResponseBody listener) {
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_CHART_DATA, new HashMap<>(),
                listener);
    }
}
