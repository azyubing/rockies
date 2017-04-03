package com.rockies.model;

import java.math.BigDecimal;
import java.util.Date;

public class VillaDetailPriceInfo extends VillaDetailPriceInfoKey {
    private Integer roomCnt;

    private Date enddate;

    private Integer peopleCnt;

    private BigDecimal roomPrice;

    private BigDecimal prepay;

    private Integer mindays;

    private String showPage;

    private String extrabedflg;

    private Integer extraCnt;

    private BigDecimal addPrice;

    private String description;

    private String delFlg;

    private Integer orderNum;
    private int id;
    private int maxCnt;

    public Integer getRoomCnt() {
        return roomCnt;
    }

    public void setRoomCnt(Integer roomCnt) {
        this.roomCnt = roomCnt;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(Integer peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public Integer getMindays() {
        return mindays;
    }

    public void setMindays(Integer mindays) {
        this.mindays = mindays;
    }

    public String getShowPage() {
        return showPage;
    }

    public void setShowPage(String showPage) {
        this.showPage = showPage == null ? null : showPage.trim();
    }

    public String getExtrabedflg() {
        return extrabedflg;
    }

    public void setExtrabedflg(String extrabedflg) {
        this.extrabedflg = extrabedflg == null ? null : extrabedflg.trim();
    }

    public Integer getExtraCnt() {
        return extraCnt;
    }

    public void setExtraCnt(Integer extraCnt) {
        this.extraCnt = extraCnt;
    }

    public BigDecimal getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(BigDecimal addPrice) {
        this.addPrice = addPrice;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxCnt() {
		return maxCnt;
	}

	public void setMaxCnt(int maxCnt) {
		this.maxCnt = maxCnt;
	}

}