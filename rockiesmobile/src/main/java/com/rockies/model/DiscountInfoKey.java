package com.rockies.model;

public class DiscountInfoKey extends BaseModel {
    private String pid;

    private String discountNo;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDiscountNo() {
        return discountNo;
    }

    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo == null ? null : discountNo.trim();
    }
}