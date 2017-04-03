package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.LoginInfoMapper;
import com.rockies.ec.mapper.LoginRoleMapper;
import com.rockies.ec.model.LoginInfo;
import com.rockies.ec.model.LoginRole;
import com.rockies.ec.services.ILoginInfoService;
import com.rockies.ec.vo.LoginInfoVO;

@Service
public class LoginInfoServiceImpl implements ILoginInfoService {

	@Autowired
	private LoginInfoMapper loginInfoMapper;
	@Autowired
	private LoginRoleMapper loginRoleMapper;
	@Override
	public void saveLoginInfo(LoginInfoVO loginInfoVO) {
		LoginInfo loginInfo =  loginInfoVO.getLoginInfo();
		loginInfoMapper.insert(loginInfo);
		int lid = loginInfo.getLid();
		LoginRole loginRole = new LoginRole();
		loginRole.setLid(lid);
		loginRole.setRoleId(loginInfoVO.getRoleId());
		loginRoleMapper.deleteLoginRole(lid);
		loginRoleMapper.saveLoginRole(loginRole);
	}
	
	@Override
	public int insert(Object o) {
		return 0;
	}
	@Override
	public void deleteByPrimaryKey(int lid) {
		loginInfoMapper.deleteByPrimaryKey(lid);
		loginRoleMapper.deleteLoginRole(lid);
	}
	@Override
	public List<Object> getAllList() {
		return null;
	}
	@Override
	public List<Object> getAllListByMap(Map map) {
		return null;
	}
	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return loginInfoMapper.getAllListMap(map);
	}
	@Override
	public Object getOneByPrimaryKey(int id) {
		return null;
	}
	@Override
	public long getCount(Map map) {
		return 0;
	}
	@Override
	public void updateObject(Object o) {
		loginInfoMapper.updateObject(o);
	}

	@Override
	public void updateLoginInfo(LoginInfoVO loginInfoVO) {
		LoginInfo loginInfo =  loginInfoVO.getLoginInfo();
		loginInfoMapper.updateObject(loginInfo);
		int lid = loginInfo.getLid();
		LoginRole loginRole = new LoginRole();
		loginRole.setLid(lid);
		loginRole.setRoleId(loginInfoVO.getRoleId());
		loginRoleMapper.deleteLoginRole(lid);
		loginRoleMapper.saveLoginRole(loginRole);
		
	}

	@Override
	public boolean checkLoginName(String loginName) {
		int count = loginInfoMapper.checkLoginName(loginName);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public LoginInfoVO getLoginInfo(Map map) {
		return loginInfoMapper.getLoginInfo(map);
	}

	@Override
	public boolean login(Map map) {
		int count = loginInfoMapper.login(map);
		if(count==1){
			return true;
		}
		return false;
	}

}
