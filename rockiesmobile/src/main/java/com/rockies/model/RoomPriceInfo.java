package com.rockies.model;

import java.math.BigDecimal;
import java.util.Date;

public class RoomPriceInfo extends RoomPriceInfoKey {
    private Date enddate;

    private String priceGroup;

    private BigDecimal roomPrice;

    private Integer peopleCnt;

    private Integer maxCnt;

    private BigDecimal prepay;

    private String description;

    private Integer orderNum;
    private int id;

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup == null ? null : priceGroup.trim();
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(Integer peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public Integer getMaxCnt() {
        return maxCnt;
    }

    public void setMaxCnt(Integer maxCnt) {
        this.maxCnt = maxCnt;
    }

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

}