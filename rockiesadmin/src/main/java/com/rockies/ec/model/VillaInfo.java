package com.rockies.ec.model;


public class VillaInfo extends ProductInfo {
    
    private String description;

    private String service;

    private String area;

    private Integer minroomcnt;

    private Integer maxroomcnt;

    private String geography;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getMinroomcnt() {
        return minroomcnt;
    }

    public void setMinroomcnt(Integer minroomcnt) {
        this.minroomcnt = minroomcnt;
    }

    public Integer getMaxroomcnt() {
        return maxroomcnt;
    }

    public void setMaxroomcnt(Integer maxroomcnt) {
        this.maxroomcnt = maxroomcnt;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography == null ? null : geography.trim();
    }

}