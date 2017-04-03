package com.rockies.model;

/**
 * 目的地
 *
 */
public class Destination extends BaseModel {
	private int id;
	private int continentId;//洲id
	private int countryId;//国家id
	private int sequence;//序号
	private String mapLng;//纬度
	public String getMapLng() {
		return mapLng;
	}
	public void setMapLng(String mapLng) {
		this.mapLng = mapLng;
	}
	public String getMapLat() {
		return mapLat;
	}
	public void setMapLat(String mapLat) {
		this.mapLat = mapLat;
	}
	private String mapLat;//经度
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContinentId() {
		return continentId;
	}
	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	@Override
	public String toString() {
		return "Destination [id=" + id + ", continentId=" + continentId
				+ ", countryId=" + countryId + ", sequence=" + sequence
				+ ", mapLng=" + mapLng + ", mapLat=" + mapLat + "]";
	}
	
}
