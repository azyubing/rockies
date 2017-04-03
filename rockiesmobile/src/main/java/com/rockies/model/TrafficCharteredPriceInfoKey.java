package com.rockies.model;

public class TrafficCharteredPriceInfoKey extends BaseModel {
    private String pid;

    private String validStarttime;

    private String carbrand;

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