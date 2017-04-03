package com.rockies.ec.model;

public class LoginBean extends BaseModel {
	private String lid;
	private String loginName;
	private String pw;
	private String nickName;
	private Integer l_status;
	private String loginImage;
	private String roleCode;
	private Long lr_id;
	private String role_id;
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	public Integer getL_status() {
		return l_status;
	}
	public void setL_status(Integer l_status) {
		this.l_status = l_status;
	}

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLoginImage() {
		return loginImage;
	}
	public void setLoginImage(String loginImage) {
		this.loginImage = loginImage;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Long getLr_id() {
		return lr_id;
	}
	public void setLr_id(Long lr_id) {
		this.lr_id = lr_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

}
