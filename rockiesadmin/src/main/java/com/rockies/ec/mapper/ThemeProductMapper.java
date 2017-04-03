package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.BaseMapper;

public interface ThemeProductMapper extends BaseMapper {

	/**
	 * 验证产品是否绑定
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int checkProductExist(Map map);
	
	/**
	 * 该分类下是否还有产品
	 * @param themeCatalogId
	 * @return
	 */
	int checkHasProduct(int themeCatalogId);
	
}
