package com.rockies.ec.model;

import java.util.Date;

/**
 * 用户
 *
 */
public class User {
	private int id;//id
	private String username;//用户账号
	private String password;//登陆密码
	private String email;//用户邮箱
	private String tel;//用户电话
	private int status;//0：正常使用，1：删除，2：冻结
	private Date ctdt;//创建时间
	private Date updt;//修改时间
	private int agree;//同意服务条款 0：同意1：不同意
	private String photo;//我的头像
	private String realName;//真实姓名
	private Date birthday;//生日
	private String idCard;//身份证
	private String passportNumber;//护照号码
	private String passportImg;//护照扫描件
	private int countryId;//护照国家id
	private int provinceId;//护照省份id
	private int cityId;//护照城市id
	private String activecode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCtdt() {
		return ctdt;
	}
	public void setCtdt(Date ctdt) {
		this.ctdt = ctdt;
	}
	public Date getUpdt() {
		return updt;
	}
	public void setUpdt(Date updt) {
		this.updt = updt;
	}
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
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
	public String getPassportImg() {
		return passportImg;
	}
	public void setPassportImg(String passportImg) {
		this.passportImg = passportImg;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", tel=" + tel + ", status="
				+ status + ", ctdt=" + ctdt + ", updt=" + updt + ", agree="
				+ agree + ", photo=" + photo + ", realName=" + realName
				+ ", birthday=" + birthday + ", idCard=" + idCard
				+ ", passportNumber=" + passportNumber + ", passportImg="
				+ passportImg + ", countryId=" + countryId + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", activecode=" +  activecode + "]";
	}
	
}
