package com.rockies.webservice.service;

import java.util.List;
import java.util.Map;

import com.rockies.webservice.service.base.BaseService;
import com.rockies.webservice.model.User;

public interface IUserService extends BaseService<User> {
	/**
	 * 添加用户 注册
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void modifyUser(User user);
	
	/**
	 * 通过id查找用户
	 * @param id
	 * @return
	 */
	public User getUserById(int id);

	public List<User> getAllUsers();
	
	public List<User> queryUsers(User user);

	public boolean userNameExit(String username);

	public User login(Map<String, Object> resMap);

	public boolean telExit(String tel);

	public boolean emailExit(String email);

	public boolean activateUser(Map<String, Object> resMap);

	public int updatePW(User user);
	
}

