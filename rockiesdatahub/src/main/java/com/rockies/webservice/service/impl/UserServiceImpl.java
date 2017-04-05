package com.rockies.webservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.webservice.mapper.UserMapper;
import com.rockies.webservice.model.User;
import com.rockies.webservice.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		for(Object user: userMapper.getAllList()){
			userList.add((User)user);
		}	
		return userList;
	}
	
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



}
