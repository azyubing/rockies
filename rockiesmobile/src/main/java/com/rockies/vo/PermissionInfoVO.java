package com.rockies.vo;

import java.util.Date;
import java.util.List;

import com.rockies.model.PermissionInfo;

public class PermissionInfoVO {
	private String pid;//权限/功能 id 'P00000000001'
	private String permissionName;//权限/功能 名称
	private String permissionStatus;//状态 0 停用 1 正常启用 2其它
	private String permissionUrl;//权限url
	private String parentPid;//所属菜单组
	private int permissionOrder;//菜单序号
	private int permissionGroupOrder;//菜单组序号
	private int needCheck;//是否需要权限检查
	private String ctuser;
	private Date ctdt;
	private String upuser;
	private Date updt;
	private List<PermissionInfo> childPermissionInfoList;//子菜单
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
	public String getPermissionStatus() {
		return permissionStatus;
	}
	public void setPermissionStatus(String permissionStatus) {
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
	public String getCtuser() {
		return ctuser;
	}
	public void setCtuser(String ctuser) {
		this.ctuser = ctuser;
	}
	public Date getCtdt() {
		return ctdt;
	}
	public void setCtdt(Date ctdt) {
		this.ctdt = ctdt;
	}
	public String getUpuser() {
		return upuser;
	}
	public void setUpuser(String upuser) {
		this.upuser = upuser;
	}
	public Date getUpdt() {
		return updt;
	}
	public void setUpdt(Date updt) {
		this.updt = updt;
	}
	public List<PermissionInfo> getChildPermissionInfoList() {
		return childPermissionInfoList;
	}
	public void setChildPermissionInfoList(
			List<PermissionInfo> childPermissionInfoList) {
		this.childPermissionInfoList = childPermissionInfoList;
	}
	
}
