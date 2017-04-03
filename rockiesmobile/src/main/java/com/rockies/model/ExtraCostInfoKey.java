package com.rockies.model;

public class ExtraCostInfoKey extends BaseModel {
    private String pid;

    private String extraNo;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getExtraNo() {
        return extraNo;
    }

    public void setExtraNo(String extraNo) {
        this.extraNo = extraNo == null ? null : extraNo.trim();
    }
}