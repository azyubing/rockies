package com.rockies.ec.model;

import java.util.Date;

public class VillaDetailPriceInfoKey extends BaseModel {
    private String pid;

    private Date starttime;

    private String villaNm;
    
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