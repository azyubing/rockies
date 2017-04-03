package com.rockies.ec.mapper;

import com.rockies.ec.model.LoginRole;

public interface LoginRoleMapper {
	public void saveLoginRole(LoginRole loginRole);
	public void deleteLoginRole(int lid);
}
