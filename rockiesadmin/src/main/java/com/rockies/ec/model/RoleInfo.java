package com.rockies.ec.model;

/**
 * 角色
 * @author Administrator
 *
 */
public class RoleInfo extends BaseModel {
	private String roleId;//角色编号,'R000000001'
	private String roleName;//角色名称
	private String roleNameEn;//角色英文名称
	private int roleStatus;//状态 0 停用 1正常 2异常 default = 1
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
	public int getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}
	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleNameEn=" + roleNameEn + ", roleStatus=" + roleStatus
				+ "]";
	}
	
	
}
