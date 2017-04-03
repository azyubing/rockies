package com.rockies.ec.model;

import java.math.BigDecimal;

public class TrafficCharteredPriceInfo extends TrafficCharteredPriceInfoKey {
    private String validEndtime;

    private Integer maxpeople;

    private String luggage;

    private BigDecimal price;

    private BigDecimal prepay;

    private Integer hourCnt;

    private String carImg;

    private String delFlg;

    private Integer orderNum;

    public String getValidEndtime() {
        return validEndtime;
    }

    public void setValidEndtime(String validEndtime) {
        this.validEndtime = validEndtime == null ? null : validEndtime.trim();
    }

    public Integer getMaxpeople() {
        return maxpeople;
    }

    public void setMaxpeople(Integer maxpeople) {
        this.maxpeople = maxpeople;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage == null ? null : luggage.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public Integer getHourCnt() {
        return hourCnt;
    }

    public void setHourCnt(Integer hourCnt) {
        this.hourCnt = hourCnt;
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg == null ? null : carImg.trim();
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
    
}