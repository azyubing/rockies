package com.rockies.webservice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rockies.webservice.mapper.UserMapper;
import com.rockies.webservice.model.User;
import com.rockies.webservice.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
    @Cacheable(value = "commonService.getAllUsers", key = "new String('commonService.getAllUsers')")  
	public List<User> getAllUsers() {
		return userMapper.getAllList();
	}
	
	@Override
	public List<User> queryUsers(User user) {
		return userMapper.queryUsers(user);
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
    @Cacheable(value = "commonService.getUserById", key = "new String('commonService.getUserById.#')+#id", condition = "null != #id")  
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
		int i = userMapper.activateUser(resMap);
		if(i==1){
			return true;
		}
		return false;
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public void deleteByPrimaryKey(long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getAllList() {
		return userMapper.getAllList();
	}

	@Override
	public List<User> getAllListByMap(Map map) {
		return userMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return userMapper.getAllListMap(map);
	}

	@Override
	public User getOneByPrimaryKey(int id) {
		return (User) userMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		return userMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		userMapper.updateObject(o);
	}



}
