package com.rockies.model;

import java.util.Date;

/**
 * 常用联系人
 *
 */
public class Customer extends BaseModel {
	private int id;
	private String customerName;//客户姓名
	private String realName;//真实姓名
	private String email;//邮箱
	private String tel;//手机电话
	private Date birthday;//出生日期
	private String idCard;//身份证
	private String passportNumber;//护照号
	private int countryId;//护照国家id
	private int provinceId;//护照省份id
	private int cityId;//护照城市id
	private int isUse;//是否常用联系人 0：是 1：否
	private int userId;//用户id
	private String passportImg;//护照图片
	private String countryName;//护照国家
	private String provinceName;//护照省份
	private String cityName;//护照城市
	private String address;//联系人地址
	private String cardType;//证件类型
	private String cardNumber;//证件号码
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	public String getPassportImg() {
		return passportImg;
	}
	public void setPassportImg(String passportImg) {
		this.passportImg = passportImg;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName
				+ ", realName=" + realName + ", email=" + email + ", tel="
				+ tel + ", birthday=" + birthday + ", idCard=" + idCard
				+ ", passportNumber=" + passportNumber + ", countryId="
				+ countryId + ", provinceId=" + provinceId + ", cityId="
				+ cityId + ", isUse=" + isUse + ", userId=" + userId
				+ ", passportImg=" + passportImg + ", countryName="
				+ countryName + ", provinceName=" + provinceName
				+ ", cityName=" + cityName + ", address=" + address
				+ ", cardType=" + cardType + ", cardNumber=" + cardNumber + "]";
	}
	
	
}
