package com.unistrong.framwork.utils;

import com.unistrong.framwork.resp.DictResp;
import com.unistrong.framwork.resp.UserListResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 字典<后续使用库></>
 */
public class DynamicDictUtils {
    public static List<DictResp.ResultBean> employeeModeList;
    public static List<DictResp.ResultBean> employeeChannelList;
    public static List<DictResp.ResultBean> employeeAreaList;
    public static List<UserListResp.ResultBean> userList; //责任人

    public static String getValue(String key, List<DictResp.ResultBean> dictList) {
        if (dictList != null && !dictList.isEmpty()) {
            for (DictResp.ResultBean bean : dictList) {
                if (bean.getDictCode().equals(key)) {
                    return bean.getDictName();
                }
            }
        }
        return "-";
    }

    public static String getKey(String value, List<DictResp.ResultBean> dictList) {
        if (dictList != null && !dictList.isEmpty()) {
            for (DictResp.ResultBean bean : dictList) {
                if (bean.getDictName().equals(value)) {
                    return bean.getDictCode();
                }
            }
        }
        return "-";
    }

    public static String[] getValueArray(List<DictResp.ResultBean> dictList) {
        String[] result = new String[0];
        if (dictList != null) {
            result = new String[dictList.size()];
            int index = 0;
            for (DictResp.ResultBean bean : dictList) {
                result[index] = bean.getDictName();
                index++;
            }
        }
        return result;
    }

    public static String getUserId(String userName) {
        if (userList != null && !userList.isEmpty()) {
            for (UserListResp.ResultBean bean : userList) {
                if (bean.getUserRealname().equals(userName)) {
                    return String.valueOf(bean.getUserId());
                }
            }
        }
        return "-1";
    }

    public static String[] getUserArrays() {
        String[] result = new String[0];
        if (userList != null) {
            result = new String[userList.size()];
            int index = 0;
            for (UserListResp.ResultBean bean : userList) {
                result[index] = bean.getUserRealname();
                index++;
            }
        }
        return result;
    }

    /**
     * 初始化动态字典
     */
    public static void init() {
        HashMap<String, String> params = new HashMap<>();
        params.put("dictNo", "E100");
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_DICT, params, new ResponseBody<DictResp>(DictResp.class) {
            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onSuccess(DictResp resp) {
                if (isFailure(resp.getCode())) {
                    return;
                }
                employeeModeList = resp.getResult();
            }
        });
        HashMap<String, String> params1 = new HashMap<>();
        params1.put("dictNo", "E200");
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_DICT, params1, new ResponseBody<DictResp>(DictResp.class) {
            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onSuccess(DictResp resp) {
                if (isFailure(resp.getCode())) {
                    return;
                }
                employeeChannelList = resp.getResult();
            }
        });
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("dictNo", "E300");
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_DICT, params2, new ResponseBody<DictResp>(DictResp.class) {
            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onSuccess(DictResp resp) {
                if (isFailure(resp.getCode())) {
                    return;
                }
                employeeAreaList = resp.getResult();
            }
        });
        //用户列表
        HashMap<String, String> params3 = new HashMap<>();
        params3.put("currentPage", "1");
        params3.put("pageSize", "100");
        params3.put("roleId", "5");//责任人
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_USER_LIST,
                params3, new ResponseBody<UserListResp>(UserListResp.class) {
                    @Override
                    public void onFailure(String message) {
                    }

                    @Override
                    public void onSuccess(UserListResp resp) {
                        if (isFailure(resp.getCode())) {
                            return;
                        }
                        userList = resp.getResult();
                    }
                });
    }
}
