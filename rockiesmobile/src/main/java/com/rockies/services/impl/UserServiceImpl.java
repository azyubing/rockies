package com.rockies.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.UserMapper;
import com.rockies.model.User;
import com.rockies.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void saveUser(User user) {
		userMapper.saveUser(user);
	}

	@Override
	public void modifyUser(User user) {
		userMapper.modifyUser(user);
	}

	@Override
	public int updatePW(User user) {
		return userMapper.updatePW(user);
	}
	
	@Override
	public int updatePW2(User user) {
		return userMapper.updatePW2(user);
	}

	@Override
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}

	@Override
	public boolean userNameExit(String username) {
		int count = userMapper.userNameExit(username);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public User login(Map<String, Object> resMap) {
		return userMapper.login(resMap);
	}

	@Override
	public boolean telExit(String tel) {
		int count = userMapper.telExit(tel);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public boolean emailExit(String email) {
		int count = userMapper.emailExit(email);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public boolean activateUser(Map<String, Object> resMap) {
		//User user = userMapper.login(resMap);
		int i = userMapper.activateUser(resMap);
		if(i==1){
			return true;
		}
		return false;
	}
	
	@Override
	public User weixinLogin(String openid) {
		return userMapper.weixinLogin(openid);
	}

	@Override
	public User getUserByUserName(String username) {
		return userMapper.getUserByUserName(username);
	}

	@Override
	public boolean getIsCanEvaluation(Map<String, Object> param) {
		int n = userMapper. getIsCanEvaluation(param);
		if(n>0){
			return true;
		}
		return false;
	}

	@Override
	public void updateUserPhoto(Map<String, Object> param) {
		userMapper.updateUserPhoto(param);
		
	}

	@Override
	public void updatePWByTel(User user) {
		userMapper.updatePWByTel(user);
	}

}
