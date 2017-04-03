package com.rockies.model;

/**
 * 后台用户
 * @author Administrator
 *
 */
public class LoginInfo extends BaseModel {
	private int lid;//登录表id
	private String loginName;//登录名
	private String password;//登录密码
	private String nickName;//昵称
	private String email;//邮箱
	private String telephoneno;//用户电话
	private int lstatus;//状态 0 异常 1正常 2停用 3删除 default = 1
	private int isSuppliers;//是否是供应商
	private int supplierId;//供应商id
	public int getIsSuppliers() {
		return isSuppliers;
	}
	public void setIsSuppliers(int isSuppliers) {
		this.isSuppliers = isSuppliers;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephoneno() {
		return telephoneno;
	}
	public void setTelephoneno(String telephoneno) {
		this.telephoneno = telephoneno;
	}
	public int getLstatus() {
		return lstatus;
	}
	public void setLstatus(int lstatus) {
		this.lstatus = lstatus;
	}
	@Override
	public String toString() {
		return "LoginInfo [lid=" + lid + ", loginName=" + loginName
				+ ", password=" + password + ", nickName=" + nickName
				+ ", email=" + email + ", telephoneno=" + telephoneno
				+ ", lstatus=" + lstatus + ", isSuppliers=" + isSuppliers
				+ ", supplierId=" + supplierId + "]";
	}
	
}
