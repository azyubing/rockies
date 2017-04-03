package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.User;

public interface IUserService {
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

	public boolean userNameExit(String username);

	public User login(Map<String, Object> resMap);

	public boolean telExit(String tel);

	public boolean emailExit(String email);

	public boolean activateUser(Map<String, Object> resMap);

	public int updatePW(User user);
}
