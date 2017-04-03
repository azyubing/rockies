package com.rockies.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.PermissionInfoMapper;
import com.rockies.model.PermissionInfo;
import com.rockies.services.IPermissionInfoService;
import com.rockies.vo.PermissionInfoVO;

@Service
public class PermissionInfoServiceImpl implements IPermissionInfoService {

	@Autowired
	private PermissionInfoMapper permissionInfoMapper;
	@Override
	public void savePermissionInfo(PermissionInfo permissionInfo) {
		permissionInfoMapper.savePermissionInfo(permissionInfo);
	}

	@Override
	public List<PermissionInfoVO> getTwoMenu() {
		return permissionInfoMapper.getTwoMenu();
	}

	@Override
	public List<PermissionInfoVO> loginRolePermission(int lid,String roleId) {
		List<PermissionInfoVO> voList = permissionInfoMapper.loginRolePermission(lid);
		Map<String, Object> map = new HashMap<String, Object>();
		for(PermissionInfoVO vo:voList){
			map.put("parentPid", vo.getPid());
			map.put("roleId", roleId);
			List<PermissionInfo> permissionInfoList = permissionInfoMapper.getPermissionInfo(map);
			vo.setChildPermissionInfoList(permissionInfoList);
		}
		
		return voList;
	}

}
