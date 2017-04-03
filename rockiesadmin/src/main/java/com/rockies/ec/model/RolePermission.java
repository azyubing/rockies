package com.rockies.ec.model;

public class RolePermission {
	private int rpId;
	private String pid;//权限/功能 id 
	private String roleId;//角色编号
	private int isCheck;//是否需要验证
	public int getRpId() {
		return rpId;
	}
	public void setRpId(int rpId) {
		this.rpId = rpId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	
}
