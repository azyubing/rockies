package com.rockies.webservice.model;

import java.util.Date;

public class User {

	private long id;// id
	private String userName;// 用户账号
	private String password;// 登陆密码
	private String email;// 用户邮箱
	private String mobilePhone;// 移动电话号码
	private String telephone;// 固定电话
	private int status;// 0：正常使用，1：删除，2：冻结
	private int agree;// 同意服务条款 0：同意1：不同意
	private String photo;// 我的头像
	private String realName;// 真实姓名
	private Date birthday;// 生日
	private String idCard;// 身份证
	private String passportNumber;// 护照号码
	private String passportImagine;// 护照扫描件
	private int countryId;// 护照国家id
	private int provinceId;// 护照省份id
	private int cityId;// 护照城市id
	private String activeCode;
	private Date createDate;// 创建时间
	private Date updateDate;// 修改时间

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getPassportImagine() {
		return passportImagine;
	}

	public void setPassportImagine(String passportImagine) {
		this.passportImagine = passportImagine;
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

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + userName + ", password=" + password + ", email=" + email
				+ ", mobilePhone=" + mobilePhone + ", status=" + status + ", ctdt=" + createDate + ", updt="
				+ updateDate + ", agree=" + agree + ", photo=" + photo + ", realName=" + realName + ", birthday="
				+ birthday + ", idCard=" + idCard + ", passportNumber=" + passportNumber + ", passportImagine="
				+ passportImagine + ", countryId=" + countryId + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", activecode=" + activeCode + "]";
	}
}
