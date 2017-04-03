package com.rockies.ec.mapper;

import java.util.List;

import com.rockies.ec.model.RolePermission;

public interface RolePermissionMapper {
	public void saveRolePermission(RolePermission rolePermission);
	public void deleteRolePermission(String roleId);
	public List<RolePermission> getRolePermissionList(String roleId);
}
