package com.rockies.model;

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
	private String openid;
	private String unionid;
	private String weiname;
	private String countryName;//护照国家
	private String provinceName;//护照省份
	private String cityName;//护照城市
	private String address;//联系人地址
	private String cusName;//默认联系人姓名
	private String cusTel;//默认联系人电话
	private String cusEmail;//默认联系人邮箱
	
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	public String getWeiname() {
		return weiname;
	}
	public void setWeiname(String weiname) {
		this.weiname = weiname;
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
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusTel() {
		return cusTel;
	}
	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activecode == null) ? 0 : activecode.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + agree;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + cityId;
		result = prime * result
				+ ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + countryId;
		result = prime * result
				+ ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((ctdt == null) ? 0 : ctdt.hashCode());
		result = prime * result
				+ ((cusEmail == null) ? 0 : cusEmail.hashCode());
		result = prime * result + ((cusName == null) ? 0 : cusName.hashCode());
		result = prime * result + ((cusTel == null) ? 0 : cusTel.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		result = prime * result
				+ ((passportImg == null) ? 0 : passportImg.hashCode());
		result = prime * result
				+ ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + provinceId;
		result = prime * result
				+ ((provinceName == null) ? 0 : provinceName.hashCode());
		result = prime * result
				+ ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + status;
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((unionid == null) ? 0 : unionid.hashCode());
		result = prime * result + ((updt == null) ? 0 : updt.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((weiname == null) ? 0 : weiname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (activecode == null) {
			if (other.activecode != null)
				return false;
		} else if (!activecode.equals(other.activecode))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (agree != other.agree)
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (cityId != other.cityId)
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (countryId != other.countryId)
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (ctdt == null) {
			if (other.ctdt != null)
				return false;
		} else if (!ctdt.equals(other.ctdt))
			return false;
		if (cusEmail == null) {
			if (other.cusEmail != null)
				return false;
		} else if (!cusEmail.equals(other.cusEmail))
			return false;
		if (cusName == null) {
			if (other.cusName != null)
				return false;
		} else if (!cusName.equals(other.cusName))
			return false;
		if (cusTel == null) {
			if (other.cusTel != null)
				return false;
		} else if (!cusTel.equals(other.cusTel))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		if (passportImg == null) {
			if (other.passportImg != null)
				return false;
		} else if (!passportImg.equals(other.passportImg))
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (provinceId != other.provinceId)
			return false;
		if (provinceName == null) {
			if (other.provinceName != null)
				return false;
		} else if (!provinceName.equals(other.provinceName))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (status != other.status)
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (unionid == null) {
			if (other.unionid != null)
				return false;
		} else if (!unionid.equals(other.unionid))
			return false;
		if (updt == null) {
			if (other.updt != null)
				return false;
		} else if (!updt.equals(other.updt))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (weiname == null) {
			if (other.weiname != null)
				return false;
		} else if (!weiname.equals(other.weiname))
			return false;
		return true;
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
				+ provinceId + ", cityId=" + cityId + ", activecode="
				+ activecode + ", openid=" + openid + ", unionid=" + unionid
				+ ", weiname=" + weiname + ", countryName=" + countryName
				+ ", provinceName=" + provinceName + ", cityName=" + cityName
				+ ", address=" + address + "]";
	}
	
}
