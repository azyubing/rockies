package com.rockies.mapper;

import java.util.Map;

import com.rockies.model.User;

public interface UserMapper {
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

	public int userNameExit(String username);

	public User login(Map<String, Object> resMap);

	public int telExit(String tel);

	public int activateUser(Map<String, Object> resMap);

	public int emailExit(String email);

	public int updatePW(User user);
	
	public User weixinLogin(String openid);

	public User getUserByUserName(String username);

	public int updatePW2(User user);

	public int getIsCanEvaluation(Map<String, Object> param);

	public void updateUserPhoto(Map<String, Object> param);

	public void updatePWByTel(User user);
}
