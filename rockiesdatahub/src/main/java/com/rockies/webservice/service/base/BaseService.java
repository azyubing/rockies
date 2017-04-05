package com.rockies.webservice.service.base;

import java.util.List;
import java.util.Map;

/**
 * 基础service
 *
 */
public interface BaseService {
	/**
	 * 添加
	 * @param o
	 * @return
	 */
	public int insert(Object o);
	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteByPrimaryKey(int id);
	/**
	 * 获取对象集合
	 * @return
	 */
	public List<Object> getAllList();
	
	/**
	 * 获取对象集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> getAllListByMap(Map map);
	
	/**
	 * 获取map集合
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,Object>> getAllListMap(Map map);
	
	/**
	 * 根据主键获取一个对象
	 * @param id
	 * @return
	 */
	public Object getOneByPrimaryKey(int id);
	
	/**
	 * 总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public long getCount(Map map);
	/**
	 * 更新对象
	 * @param o
	 */
	public void updateObject(Object o);
}
