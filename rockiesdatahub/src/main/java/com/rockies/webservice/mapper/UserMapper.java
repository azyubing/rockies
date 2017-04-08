package com.rockies.webservice.mapper;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rockies.webservice.mapper.base.BaseMapper;
import com.rockies.webservice.model.User;

@Repository
public interface UserMapper extends BaseMapper {
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
	
	public List<User> queryUsers(User user);
	
}


