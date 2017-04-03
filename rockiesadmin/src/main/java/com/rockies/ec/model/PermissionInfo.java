package com.rockies.ec.model;

/**
 * 权限
 * @author hyh
 *
 */
public class PermissionInfo extends BaseModel {
	private String pid;//权限/功能 id 'P00000000001'
	private String permissionName;//权限/功能 名称
	private int permissionStatus;//状态 0 停用 1 正常启用 2其它
	private String permissionUrl;//权限url
	private String parentPid;//所属菜单组
	private int permissionOrder;//菜单序号
	private int permissionGroupOrder;//菜单组序号
	private int needCheck;//是否需要权限检查
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public int getPermissionStatus() {
		return permissionStatus;
	}
	public void setPermissionStatus(int permissionStatus) {
		this.permissionStatus = permissionStatus;
	}
	public String getPermissionUrl() {
		return permissionUrl;
	}
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	public String getParentPid() {
		return parentPid;
	}
	public void setParentPid(String parentPid) {
		this.parentPid = parentPid;
	}
	public int getPermissionOrder() {
		return permissionOrder;
	}
	public void setPermissionOrder(int permissionOrder) {
		this.permissionOrder = permissionOrder;
	}
	public int getPermissionGroupOrder() {
		return permissionGroupOrder;
	}
	public void setPermissionGroupOrder(int permissionGroupOrder) {
		this.permissionGroupOrder = permissionGroupOrder;
	}
	public int getNeedCheck() {
		return needCheck;
	}
	public void setNeedCheck(int needCheck) {
		this.needCheck = needCheck;
	}
	@Override
	public String toString() {
		return "PermissionInfo [pid=" + pid + ", permissionName="
				+ permissionName + ", permissionStatus=" + permissionStatus
				+ ", permissionUrl=" + permissionUrl + ", parentPid="
				+ parentPid + ", permissionOrder=" + permissionOrder
				+ ", permissionGroupOrder=" + permissionGroupOrder
				+ ", needCheck=" + needCheck + "]";
	}
	
}
