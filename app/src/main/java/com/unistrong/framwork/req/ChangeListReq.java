package com.unistrong.framwork.req;

/**
 * 变更列表请求封装
 */
public class ChangeListReq {

    /**
     * createUserName : string
     * createUserOfficeName : string
     * currentPage : 0
     * employmentArea : string
     * employmentChangeTimeEnd : string
     * employmentChangeTimeStart : string
     * employmentChannel : string
     * employmentMode : string
     * employmentTransferTimeEnd : string
     * employmentTransferTimeStart : string
     * idcard : string
     * name : string
     * pageSize : 0
     */

    private String createUserName;
    private String createUserOfficeName;
    private int currentPage;
    private String employmentArea;
    private String employmentChangeTimeEnd;
    private String employmentChangeTimeStart;
    private String employmentChannel;
    private String employmentMode;
    private String employmentTransferTimeEnd;
    private String employmentTransferTimeStart;
    private String idcard;
    private String name;
    private int pageSize;

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserOfficeName() {
        return createUserOfficeName;
    }

    public void setCreateUserOfficeName(String createUserOfficeName) {
        this.createUserOfficeName = createUserOfficeName;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getEmploymentArea() {
        return employmentArea;
    }

    public void setEmploymentArea(String employmentArea) {
        this.employmentArea = employmentArea;
    }

    public String getEmploymentChangeTimeEnd() {
        return employmentChangeTimeEnd;
    }

    public void setEmploymentChangeTimeEnd(String employmentChangeTimeEnd) {
        this.employmentChangeTimeEnd = employmentChangeTimeEnd;
    }

    public String getEmploymentChangeTimeStart() {
        return employmentChangeTimeStart;
    }

    public void setEmploymentChangeTimeStart(String employmentChangeTimeStart) {
        this.employmentChangeTimeStart = employmentChangeTimeStart;
    }

    public String getEmploymentChannel() {
        return employmentChannel;
    }

    public void setEmploymentChannel(String employmentChannel) {
        this.employmentChannel = employmentChannel;
    }

    public String getEmploymentMode() {
        return employmentMode;
    }

    public void setEmploymentMode(String employmentMode) {
        this.employmentMode = employmentMode;
    }

    public String getEmploymentTransferTimeEnd() {
        return employmentTransferTimeEnd;
    }

    public void setEmploymentTransferTimeEnd(String employmentTransferTimeEnd) {
        this.employmentTransferTimeEnd = employmentTransferTimeEnd;
    }

    public String getEmploymentTransferTimeStart() {
        return employmentTransferTimeStart;
    }

    public void setEmploymentTransferTimeStart(String employmentTransferTimeStart) {
        this.employmentTransferTimeStart = employmentTransferTimeStart;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
