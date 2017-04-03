package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.services.base.BaseService;
import com.rockies.ec.vo.LoginInfoVO;

public interface ILoginInfoService extends BaseService{
	/**
	 * 添加
	 * @param loginInfoVO
	 */
	public void saveLoginInfo(LoginInfoVO loginInfoVO);
	
	
	/**
	 * 修改
	 * @param loginInfoVO
	 */
	public void updateLoginInfo(LoginInfoVO loginInfoVO);

	/**
	 * 验证用户名和密码
	 * @param loginName
	 * @return
	 */
	public boolean checkLoginName(String loginName);
	
	/**
	 * 登入心虚
	 * @param map
	 * @return
	 */
	public LoginInfoVO getLoginInfo(Map map);
	/**
	 * 登入
	 * @param map
	 * @return
	 */
	public boolean login(Map map);
	
}
