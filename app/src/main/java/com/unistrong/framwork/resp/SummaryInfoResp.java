package com.unistrong.framwork.resp;

/**
 * 首页数量统计
 */
public class SummaryInfoResp {

    /**
     * code : 0
     * msg : string
     * result : {"currentChangedCount":0,"currentUnchangeCount":0}
     */

    private int code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * currentChangedCount : 0
         * currentUnchangeCount : 0
         */

        private int currentChangedCount;
        private int currentUnchangeCount;

        public int getCurrentChangedCount() {
            return currentChangedCount;
        }

        public void setCurrentChangedCount(int currentChangedCount) {
            this.currentChangedCount = currentChangedCount;
        }

        public int getCurrentUnchangeCount() {
            return currentUnchangeCount;
        }

        public void setCurrentUnchangeCount(int currentUnchangeCount) {
            this.currentUnchangeCount = currentUnchangeCount;
        }
    }
}
