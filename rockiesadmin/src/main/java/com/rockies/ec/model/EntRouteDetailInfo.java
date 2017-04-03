package com.rockies.ec.model;


public class EntRouteDetailInfo extends EntRouteDetailInfoKey {
    private String endtime;

    private String title;

    private String titleEn;

    private String itype;

    private String description;

    private String delFlg;
    
    private Integer orderNum;
    
    private String newStarttime;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn == null ? null : titleEn.trim();
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype == null ? null : itype.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

	public String getNewStarttime() {
		return newStarttime;
	}

	public void setNewStarttime(String newStarttime) {
		this.newStarttime = newStarttime;
	}

}