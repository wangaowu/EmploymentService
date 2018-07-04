package com.unistrong.framwork.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 变更列表响应
 */
public class ChangeListResp {

    /**
     * code : 0
     * msg : string
     * result : [{"createUserId":0,"createUserTime":"string","dataSource":"string","dataType":"string","economicSituation":"string","employmentArea":"string","employmentChangeTime":"string","employmentChannel":"string","employmentId":"string","employmentMode":"string","employmentMonthlyIncome":"string","employmentPosition":"string","employmentTransferTime":"string","employmentWorkPlace":"string","householdAddress":"string","idcard":"string","imageUrl":"string","name":"string","nation":"string","personId":"string","remark":"string","responsibilityUserName":"string","responsibilityUserOfficeName":"string","sex":"string","status":"string","updateUserId":0,"updateUserTime":"string"}]
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

    public static class ResultBean implements Serializable {
        /**
         * createUserId : 0
         * createUserTime : string
         * dataSource : string
         * dataType : string
         * economicSituation : string
         * employmentArea : string
         * employmentChangeTime : string
         * employmentChannel : string
         * employmentId : string
         * employmentMode : string
         * employmentMonthlyIncome : string
         * employmentPosition : string
         * employmentTransferTime : string
         * employmentWorkPlace : string
         * householdAddress : string
         * idcard : string
         * imageUrl : string
         * name : string
         * nation : string
         * personId : string
         * remark : string
         * responsibilityUserName : string
         * responsibilityUserOfficeName : string
         * sex : string
         * status : string
         * updateUserId : 0
         * updateUserTime : string
         */

        private int createUserId;
        private String createUserTime;
        private String dataSource;
        private String dataType;
        private String economicSituation;
        private String employmentArea;
        private String employmentChangeTime;
        private String employmentChannel;
        private String employmentId;
        private String employmentMode;
        private String employmentMonthlyIncome;
        private String employmentPosition;
        private String employmentTransferTime;
        private String employmentWorkPlace;
        private String householdAddress;
        private String idcard;
        private String imageUrl;
        private String name;
        private String nation;
        private String personId;
        private String remark;
        private String responsibilityUserName;
        private String responsibilityUserOfficeName;
        private String sex;
        private String status;
        private int updateUserId;
        private String updateUserTime;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateUserTime() {
            return createUserTime;
        }

        public void setCreateUserTime(String createUserTime) {
            this.createUserTime = createUserTime;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getEconomicSituation() {
            return economicSituation;
        }

        public void setEconomicSituation(String economicSituation) {
            this.economicSituation = economicSituation;
        }

        public String getEmploymentArea() {
            return employmentArea;
        }

        public void setEmploymentArea(String employmentArea) {
            this.employmentArea = employmentArea;
        }

        public String getEmploymentChangeTime() {
            return employmentChangeTime;
        }

        public void setEmploymentChangeTime(String employmentChangeTime) {
            this.employmentChangeTime = employmentChangeTime;
        }

        public String getEmploymentChannel() {
            return employmentChannel;
        }

        public void setEmploymentChannel(String employmentChannel) {
            this.employmentChannel = employmentChannel;
        }

        public String getEmploymentId() {
            return employmentId;
        }

        public void setEmploymentId(String employmentId) {
            this.employmentId = employmentId;
        }

        public String getEmploymentMode() {
            return employmentMode;
        }

        public void setEmploymentMode(String employmentMode) {
            this.employmentMode = employmentMode;
        }

        public String getEmploymentMonthlyIncome() {
            return employmentMonthlyIncome;
        }

        public void setEmploymentMonthlyIncome(String employmentMonthlyIncome) {
            this.employmentMonthlyIncome = employmentMonthlyIncome;
        }

        public String getEmploymentPosition() {
            return employmentPosition;
        }

        public void setEmploymentPosition(String employmentPosition) {
            this.employmentPosition = employmentPosition;
        }

        public String getEmploymentTransferTime() {
            return employmentTransferTime;
        }

        public void setEmploymentTransferTime(String employmentTransferTime) {
            this.employmentTransferTime = employmentTransferTime;
        }

        public String getEmploymentWorkPlace() {
            return employmentWorkPlace;
        }

        public void setEmploymentWorkPlace(String employmentWorkPlace) {
            this.employmentWorkPlace = employmentWorkPlace;
        }

        public String getHouseholdAddress() {
            return householdAddress;
        }

        public void setHouseholdAddress(String householdAddress) {
            this.householdAddress = householdAddress;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getResponsibilityUserName() {
            return responsibilityUserName;
        }

        public void setResponsibilityUserName(String responsibilityUserName) {
            this.responsibilityUserName = responsibilityUserName;
        }

        public String getResponsibilityUserOfficeName() {
            return responsibilityUserOfficeName;
        }

        public void setResponsibilityUserOfficeName(String responsibilityUserOfficeName) {
            this.responsibilityUserOfficeName = responsibilityUserOfficeName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUpdateUserTime() {
            return updateUserTime;
        }

        public void setUpdateUserTime(String updateUserTime) {
            this.updateUserTime = updateUserTime;
        }
    }
}
