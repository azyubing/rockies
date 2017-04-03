package com.rockies.model;

public class ProductTemplate {
    private Integer id;

    private String ptype;
    
    private String ptypeCode;

    private String tempName;

    private String tempContent;

    public String getPtypeCode() {
        return ptypeCode;
    }

    public void setPtypeCode(String ptypeCode) {
        this.ptypeCode = ptypeCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype == null ? null : ptype.trim();
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName == null ? null : tempName.trim();
    }

    public String getTempContent() {
        return tempContent;
    }

    public void setTempContent(String tempContent) {
        this.tempContent = tempContent == null ? null : tempContent.trim();
    }
}