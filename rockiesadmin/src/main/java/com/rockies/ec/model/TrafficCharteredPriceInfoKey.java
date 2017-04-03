package com.rockies.ec.model;

public class TrafficCharteredPriceInfoKey extends BaseModel {
    private String pid;

    private String validStarttime;

    private String carbrand;
    
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

    public String getValidStarttime() {
        return validStarttime;
    }

    public void setValidStarttime(String validStarttime) {
        this.validStarttime = validStarttime == null ? null : validStarttime.trim();
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand == null ? null : carbrand.trim();
    }
}