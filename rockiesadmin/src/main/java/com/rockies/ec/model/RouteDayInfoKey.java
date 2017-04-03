package com.rockies.ec.model;

public class RouteDayInfoKey extends BaseModel {
    private String pid;

    private Integer pday;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getPday() {
        return pday;
    }

    public void setPday(Integer pday) {
        this.pday = pday;
    }
}