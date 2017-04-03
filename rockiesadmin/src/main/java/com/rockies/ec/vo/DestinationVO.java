package com.rockies.ec.vo;

/**
 * 目的地VO
 * @author hyh
 *
 */
public class DestinationVO {
	private int id;
	private int continentId;//洲id
	private String continentName;//洲名称
	private int countryId;//国家id
	private String countryName;//国家名称
	private int sequence;//序号
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
	public String getContinentName() {
		return continentName;
	}
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "DestinationVO [id=" + id + ", continentId=" + continentId
				+ ", continentName=" + continentName + ", countryId="
				+ countryId + ", countryName=" + countryName + ", sequence="
				+ sequence + "]";
	}
	
}
