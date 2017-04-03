package com.rockies.model;


public class HotelInfo extends ProductInfo {
    
    private String description;
    
    private String hotel_infra;

    private String service;

    private Integer staylvl;

    private String area;

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

    public Integer getStaylvl() {
        return staylvl;
    }

    public void setStaylvl(Integer staylvl) {
        this.staylvl = staylvl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

	public String getHotel_infra() {
		return hotel_infra;
	}

	public void setHotel_infra(String hotel_infra) {
		this.hotel_infra = hotel_infra;
	}
    
    

}