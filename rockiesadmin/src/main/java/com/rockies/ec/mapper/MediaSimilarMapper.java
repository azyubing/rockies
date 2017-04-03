package com.rockies.ec.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.ec.mapper.base.BaseMapper;

public interface MediaSimilarMapper extends BaseMapper {
	/**
	 * 验证产品是否绑定
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int checkProductExist(Map map);
	
	/**
	 * 获取视频绑定所有相似套餐
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAllProductList(int id);
	
	/**
	 * 获取视频绑定所有相似套餐count
	 * @param id
	 * @return
	 */
	public int getMediaSimilarCount(int id);
}
