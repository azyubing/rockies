package com.rockies.model;

import java.util.Date;

public class VillaDetailPriceInfoKey extends BaseModel {
    private String pid;

    private Date starttime;

    private String villaNm;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getVillaNm() {
        return villaNm;
    }

    public void setVillaNm(String villaNm) {
        this.villaNm = villaNm == null ? null : villaNm.trim();
    }
}