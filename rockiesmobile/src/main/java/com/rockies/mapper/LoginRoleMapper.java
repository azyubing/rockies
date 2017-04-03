package com.rockies.mapper;

import com.rockies.model.LoginRole;

public interface LoginRoleMapper {
	public void saveLoginRole(LoginRole loginRole);
	public void deleteLoginRole(int lid);
}
