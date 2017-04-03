package com.rockies.services;

import java.util.List;

import com.rockies.model.RolePermission;

public interface IRolePermissionService {
	public void saveRolePermission(List<RolePermission> rolePermissionList);
	public List<RolePermission> getRolePermissionList(String roleId);
}
