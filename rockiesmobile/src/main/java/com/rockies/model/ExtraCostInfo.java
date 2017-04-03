package com.rockies.model;

import java.math.BigDecimal;
import java.util.Date;

public class ExtraCostInfo extends ExtraCostInfoKey {
    private String extraType;

    private Date startdate;

    private Date enddate;

    private String delFlg;

    private BigDecimal extraRate;

    private BigDecimal extraApiece;

    private BigDecimal extraAmounts;

    public String getExtraType() {
        return extraType;
    }

    public void setExtraType(String extraType) {
        this.extraType = extraType == null ? null : extraType.trim();
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

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    public BigDecimal getExtraRate() {
        return extraRate;
    }

    public void setExtraRate(BigDecimal extraRate) {
        this.extraRate = extraRate;
    }

    public BigDecimal getExtraApiece() {
        return extraApiece;
    }

    public void setExtraApiece(BigDecimal extraApiece) {
        this.extraApiece = extraApiece;
    }

    public BigDecimal getExtraAmounts() {
        return extraAmounts;
    }

    public void setExtraAmounts(BigDecimal extraAmounts) {
        this.extraAmounts = extraAmounts;
    }

}