package com.rockies.ec.model;

/**
 * 用户角色
 * @author hyh
 *
 */
public class LoginRole extends BaseModel {
	private int lrId;
	private int lid;
	private String roleId;
	public int getLrId() {
		return lrId;
	}
	public void setLrId(int lrId) {
		this.lrId = lrId;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "LoginRole [lrId=" + lrId + ", lid=" + lid + ", roleId="
				+ roleId + "]";
	}
	
}
