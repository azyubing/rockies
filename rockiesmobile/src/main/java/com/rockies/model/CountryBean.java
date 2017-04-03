package com.rockies.model;

/**
 * 国家
 * @author hyh
 *
 */
public class CountryBean extends BaseModel {
	private int countryId;//国家id
	private String countryCode;//国家code
	private String countryName;//国家名称
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	@Override
	public String toString(){
		return "CountryBean[countryId="+countryId+",countryCode="+countryCode+",countryName="+countryName+"]";
	}
}
