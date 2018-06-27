package com.unistrong.framwork.resp;

import java.util.List;

/***
 * 图表数据
 */
public class ChartResp {

    /**
     * code : 0
     * msg : string
     * result : {"changeStatisticsList":[{"currentDayCount":"string","day":"string"}],"employmentStatisticsList":[{"currentDayCount":"string","day":"string"}],"personStatisticsList":[{"currentDayCount":"string","day":"string"}]}
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
        private List<ChangeStatisticsListBean> changeStatisticsList;
        private List<EmploymentStatisticsListBean> employmentStatisticsList;
        private List<PersonStatisticsListBean> personStatisticsList;

        public List<ChangeStatisticsListBean> getChangeStatisticsList() {
            return changeStatisticsList;
        }

        public void setChangeStatisticsList(List<ChangeStatisticsListBean> changeStatisticsList) {
            this.changeStatisticsList = changeStatisticsList;
        }

        public List<EmploymentStatisticsListBean> getEmploymentStatisticsList() {
            return employmentStatisticsList;
        }

        public void setEmploymentStatisticsList(List<EmploymentStatisticsListBean> employmentStatisticsList) {
            this.employmentStatisticsList = employmentStatisticsList;
        }

        public List<PersonStatisticsListBean> getPersonStatisticsList() {
            return personStatisticsList;
        }

        public void setPersonStatisticsList(List<PersonStatisticsListBean> personStatisticsList) {
            this.personStatisticsList = personStatisticsList;
        }

        public static class ChangeStatisticsListBean {
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

        public static class EmploymentStatisticsListBean {
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

        public static class PersonStatisticsListBean {
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
}
