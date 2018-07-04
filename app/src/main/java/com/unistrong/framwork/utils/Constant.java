package com.unistrong.framwork.utils;

/**
 * 应用常量类
 */
public class Constant {
    public static final String UPLOAD_URL = "http://jwux.top:38080/upload/UploadServlet";
    public static final String PICTURE_HOST = "http://jwux.top:38028";

    /**
     * 接口名
     */
    public static class Action {
        public static final String LOGIN = "/user/login";//登陆
        public static final String QUERY_SUMMARY = "/statistics/getEmploymentChangeCount";//首页统计
        public static final String QUERY_CHANGE_LIST = "/employment/queryChangeList";//转移变更列表
        public static final String QUERY_CHART_DATA = "/statistics/getRecentlyEmploymentChangeNum";//首页图表
        public static final String ADD_CHANGE_INFO = "/employment/change";//转移变更
        public static final String UPDATE_CHANGE_INFO = "/employment/changeInfoUpdate";//转移信息编辑
        public static final String QUERY_HISTORY = "/employment/changeInfoQuery";//本人历史信息
        public static final String QUERY_DICT = "/dic/getDictionaryList";//动态字典
        public static final String QUERY_USER_LIST = "/user/getUserList";//用户列表
        public static final String GET_VERSION = "/appVersion/getLatestVersion";//版本信息
    }

    public static class SP_KEY {
        public static final String USER_ACCOUNT = "user_account";
        public static final String USER_PWD = "user_password";
        public static final String TOKEN = "token";
        public static final String USER_ID = "user_id";

        //user
        public static final String USER_NAME = "user_name";
        public static final String USER_OFFICE = "user_office";

        //address info
        public static final String LAT = "latitude";
        public static final String LNG = "longitude";
        public static final String PROVINCE = "province";
        public static final String CITY = "city";
        public static final String DISTRICT = "district";
    }

}
