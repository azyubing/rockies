package com.rockies.ec.vo;

import java.util.List;

import com.rockies.ec.model.LoginInfo;

public class LoginInfoVO {
	private LoginInfo loginInfo;
	private String roleId;
	private String roleName;
	private String roleNameEn;
	private List<PermissionInfoVO> permissionInfoVOList;
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleNameEn() {
		return roleNameEn;
	}
	public void setRoleNameEn(String roleNameEn) {
		this.roleNameEn = roleNameEn;
	}
	public List<PermissionInfoVO> getPermissionInfoVOList() {
		return permissionInfoVOList;
	}
	public void setPermissionInfoVOList(List<PermissionInfoVO> permissionInfoVOList) {
		this.permissionInfoVOList = permissionInfoVOList;
	}
	@Override
	public String toString() {
		return "LoginInfoVO [loginInfo=" + loginInfo + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", roleNameEn=" + roleNameEn
				+ ", permissionInfoVOList=" + permissionInfoVOList + "]";
	}
	
	
	
}
