package com.rockies.ec.model;

public class TrafficRegularPriceInfoKey extends BaseModel {
    private String pid;

    private String departtime;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDeparttime() {
        return departtime;
    }

    public void setDeparttime(String departtime) {
        this.departtime = departtime == null ? null : departtime.trim();
    }
}