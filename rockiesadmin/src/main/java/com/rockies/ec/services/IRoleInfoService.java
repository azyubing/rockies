package com.rockies.ec.services;

import java.util.List;
import java.util.Map;

import com.rockies.ec.model.RoleInfo;

public interface IRoleInfoService {
	
	/**
	 * 登入
	 * @param map
	 * @return
	 */
	public boolean login(Map map);
	/**
	 * 添加
	 * @param roleInfo
	 */
	public void saveRoleInfo(RoleInfo roleInfo);
	
	/**
	 * 删除
	 * @param roleId
	 */
	public void deleteRoleInfo(String roleId);
	
	/**
	 * 修改
	 * @param roleInfo
	 */
	public void updateRoleInfo(RoleInfo roleInfo);
	
	/**
	 * 获取角色集合
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRoleInfoList(Map map);
	
	/**
	 * 所有
	 * @return
	 */
	public List<RoleInfo> getAllList();
	
	/**
	 * 查找角色
	 * @param roleId
	 * @return
	 */
	public RoleInfo getRoleInfo(String roleId);
}
