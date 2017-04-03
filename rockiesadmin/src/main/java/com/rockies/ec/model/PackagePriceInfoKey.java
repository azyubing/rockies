package com.rockies.ec.model;

import java.util.Date;

public class PackagePriceInfoKey extends BaseModel {
    private String pid;

    private Date starttime;

    private String priceItem;
    
    private int id;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}