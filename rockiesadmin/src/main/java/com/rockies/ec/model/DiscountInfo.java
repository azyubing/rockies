package com.rockies.ec.model;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountInfo extends DiscountInfoKey {
    private String discountType;

    private String caculateType;

    private Date startdate;

    private Date enddate;

    private Integer minCnt;

    private Integer maxCnt;

    private String delFlg;

    private BigDecimal discountRate;

    private BigDecimal discountApiece;

    private BigDecimal discountAmounts;

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType == null ? null : discountType.trim();
    }

    public String getCaculateType() {
        return caculateType;
    }

    public void setCaculateType(String caculateType) {
        this.caculateType = caculateType == null ? null : caculateType.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getMinCnt() {
        return minCnt;
    }

    public void setMinCnt(Integer minCnt) {
        this.minCnt = minCnt;
    }

    public Integer getMaxCnt() {
        return maxCnt;
    }

    public void setMaxCnt(Integer maxCnt) {
        this.maxCnt = maxCnt;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountApiece() {
        return discountApiece;
    }

    public void setDiscountApiece(BigDecimal discountApiece) {
        this.discountApiece = discountApiece;
    }

    public BigDecimal getDiscountAmounts() {
        return discountAmounts;
    }

    public void setDiscountAmounts(BigDecimal discountAmounts) {
        this.discountAmounts = discountAmounts;
    }


}