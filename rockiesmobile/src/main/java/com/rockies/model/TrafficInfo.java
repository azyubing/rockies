package com.rockies.model;


public class TrafficInfo extends ProductInfo {

    private String startplace;

    private String endplace;

    private String traffic_type;

    private String carlvl;

    private String cartype;

    private Integer peoplecnt;

    private String description;

    private String map_address_start;

    private String map_start_lng;

    private String map_start_lat;

    private String map_address_end;

    private String map_end_lng;

    private String map_end_lat;
    
    private String traff_kind;

    public String getStartplace() {
        return startplace;
    }

    public void setStartplace(String startplace) {
        this.startplace = startplace == null ? null : startplace.trim();
    }

    public String getEndplace() {
        return endplace;
    }

    public void setEndplace(String endplace) {
        this.endplace = endplace == null ? null : endplace.trim();
    }

    public String getCarlvl() {
        return carlvl;
    }

    public void setCarlvl(String carlvl) {
        this.carlvl = carlvl == null ? null : carlvl.trim();
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype == null ? null : cartype.trim();
    }

    public Integer getPeoplecnt() {
        return peoplecnt;
    }

    public void setPeoplecnt(Integer peoplecnt) {
        this.peoplecnt = peoplecnt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTraffic_type() {
        return traffic_type;
    }

    public void setTraffic_type(String traffic_type) {
        this.traffic_type = traffic_type;
    }

    public String getMap_address_start() {
        return map_address_start;
    }

    public void setMap_address_start(String map_address_start) {
        this.map_address_start = map_address_start;
    }

    public String getMap_start_lng() {
        return map_start_lng;
    }

    public void setMap_start_lng(String map_start_lng) {
        this.map_start_lng = map_start_lng;
    }

    public String getMap_start_lat() {
        return map_start_lat;
    }

    public void setMap_start_lat(String map_start_lat) {
        this.map_start_lat = map_start_lat;
    }

    public String getMap_address_end() {
        return map_address_end;
    }

    public void setMap_address_end(String map_address_end) {
        this.map_address_end = map_address_end;
    }

    public String getMap_end_lng() {
        return map_end_lng;
    }

    public void setMap_end_lng(String map_end_lng) {
        this.map_end_lng = map_end_lng;
    }

    public String getMap_end_lat() {
        return map_end_lat;
    }

    public void setMap_end_lat(String map_end_lat) {
        this.map_end_lat = map_end_lat;
    }

	public String getTraff_kind() {
		return traff_kind;
	}

	public void setTraff_kind(String traff_kind) {
		this.traff_kind = traff_kind;
	}

}