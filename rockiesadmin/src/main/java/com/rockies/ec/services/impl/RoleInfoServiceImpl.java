package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.RoleInfoMapper;
import com.rockies.ec.model.RoleInfo;
import com.rockies.ec.services.IRoleInfoService;

@Service
public class RoleInfoServiceImpl implements IRoleInfoService {

	@Autowired
	private RoleInfoMapper roleInfoMapper;
	
	@Override
	public void saveRoleInfo(RoleInfo roleInfo) {
		roleInfoMapper.saveRoleInfo(roleInfo);
	}

	@Override
	public void deleteRoleInfo(String roleId) {
		roleInfoMapper.deleteRoleInfo(roleId);
	}

	@Override
	public void updateRoleInfo(RoleInfo roleInfo) {
		roleInfoMapper.updateRoleInfo(roleInfo);
	}

	@Override
	public List<Map<String, Object>> getRoleInfoList(Map map) {
		return roleInfoMapper.getRoleInfoList(map);
	}

	@Override
	public List<RoleInfo> getAllList() {
		return roleInfoMapper.getAllList();
	}

	@Override
	public boolean login(Map map) {
		int count = roleInfoMapper.login(map);
		if(count==1){
			return true;
		}
		return false;
	}
	/**
	 * 查找角色
	 * @param roleId
	 * @return
	 */
	@Override
	public RoleInfo getRoleInfo(String roleId){
		return roleInfoMapper.getRoleInfo(roleId);
	}

}
