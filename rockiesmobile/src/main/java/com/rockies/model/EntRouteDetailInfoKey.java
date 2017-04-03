package com.rockies.model;

public class EntRouteDetailInfoKey extends BaseModel {
    private String pid;

    private Integer pday;

    private String starttime;

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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }
}