package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.BaseMapper;

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
	
	List<Map<String,Object>> getThemeproductListMapBythemeId(Map<String, Object> param);

	List<Map<String, Object>> getThemeproductListMapBythemeId2(
            Map<String, Object> param);
	
	List<Map<String, Object>> getThemeProductDetailListMap(Map<String, Object> param);
	
	List<Map<String, Object>> getThemeProductCityList(Map<String, Object> param);
}
