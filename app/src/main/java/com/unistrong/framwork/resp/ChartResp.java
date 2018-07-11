package com.unistrong.framwork.resp;

import java.util.ArrayList;
import java.util.List;

/***
 * 图表数据
 */
public class ChartResp {

    /**
     * code : 0
     * msg : string
     * result : [{"currentDayCount":"string","day":"string"}]
     * total : 0
     */

    private int code;
    private String msg;
    private int total;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * currentDayCount : string
         * day : string
         */

        private String currentDayCount;
        private String day;

        public String getCurrentDayCount() {
            return currentDayCount;
        }

        public void setCurrentDayCount(String currentDayCount) {
            this.currentDayCount = currentDayCount;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }
    }
}
