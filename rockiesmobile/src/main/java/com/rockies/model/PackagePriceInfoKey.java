package com.rockies.model;

import java.util.Date;

public class PackagePriceInfoKey extends BaseModel {
    private String pid;

    private Date starttime;

    private String priceItem;

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

    public String getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(String priceItem) {
        this.priceItem = priceItem == null ? null : priceItem.trim();
    }
}