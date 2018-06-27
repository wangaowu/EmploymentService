package com.unistrong.framwork.resp;

/**
 * 首页人员采集、就业登记、转移变更数量统计
 */
public class SummaryInfoResp {

    /**
     * code : 0
     * msg : string
     * result : {"allChangeNum":0,"allPersonNum":0,"allRegisterNum":0,"currentDayChangeNum":0,"currentDayPersonNum":0,"currentDayRegisterNum":0}
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
         * allChangeNum : 0
         * allPersonNum : 0
         * allRegisterNum : 0
         * currentDayChangeNum : 0
         * currentDayPersonNum : 0
         * currentDayRegisterNum : 0
         */

        private int allChangeNum;
        private int allPersonNum;
        private int allRegisterNum;
        private int currentDayChangeNum;
        private int currentDayPersonNum;
        private int currentDayRegisterNum;

        public int getAllChangeNum() {
            return allChangeNum;
        }

        public void setAllChangeNum(int allChangeNum) {
            this.allChangeNum = allChangeNum;
        }

        public int getAllPersonNum() {
            return allPersonNum;
        }

        public void setAllPersonNum(int allPersonNum) {
            this.allPersonNum = allPersonNum;
        }

        public int getAllRegisterNum() {
            return allRegisterNum;
        }

        public void setAllRegisterNum(int allRegisterNum) {
            this.allRegisterNum = allRegisterNum;
        }

        public int getCurrentDayChangeNum() {
            return currentDayChangeNum;
        }

        public void setCurrentDayChangeNum(int currentDayChangeNum) {
            this.currentDayChangeNum = currentDayChangeNum;
        }

        public int getCurrentDayPersonNum() {
            return currentDayPersonNum;
        }

        public void setCurrentDayPersonNum(int currentDayPersonNum) {
            this.currentDayPersonNum = currentDayPersonNum;
        }

        public int getCurrentDayRegisterNum() {
            return currentDayRegisterNum;
        }

        public void setCurrentDayRegisterNum(int currentDayRegisterNum) {
            this.currentDayRegisterNum = currentDayRegisterNum;
        }
    }
}
