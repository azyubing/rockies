package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.model.PermissionInfo;
import com.rockies.vo.PermissionInfoVO;

public interface PermissionInfoMapper {
	/**
	 * 新增
	 * @param PermissionInfo
	 */
	public void savePermissionInfo(PermissionInfo permissionInfo);
	
	/**
	 * 获取两级的菜单
	 * @return
	 */
	public List<PermissionInfoVO> getTwoMenu();
	
	/**
	 * 用户拥有的权限
	 * @param lid
	 * @return
	 */
	public List<PermissionInfoVO> loginRolePermission(int lid);
	
	/**
	 * 子类权限
	 * @param lid
	 * @return
	 */
	public List<PermissionInfo> getPermissionInfo(Map map);
}
