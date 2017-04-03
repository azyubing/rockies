package com.rockies.ec.model;

public class City extends BaseModel {
	/**
	 * 
	 */
	private int id;
	private String cityName;//城市名称
	private String cityNameEn;//城市名称英文
	private String cityCode;//城市代码
	private int parentId;
	private int isHot;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCityNameEn() {
		return cityNameEn;
	}
	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}
	public int getIsHot() {
        return isHot;
    }
    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }
    
    @Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", cityNameEn="
				+ cityNameEn + ", cityCode=" + cityCode + ", parentId="
				+ parentId + ", isHot="+ isHot +"]";
	}
}
