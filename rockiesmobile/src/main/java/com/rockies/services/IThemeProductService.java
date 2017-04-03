package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.services.base.BaseService;

public interface IThemeProductService extends BaseService {
	/**
	 * 保存绑定产品
	 * @param productIds
	 * @param themeCatalogId
	 * @param userId
	 * @param themeId
	 */
	public void saveListThemeProduct(String productIds, String themeCatalogId, int userId, int themeId);

	/**
	 * 验证产品是否绑定
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkProductExist(Map map);

	/**
	 * 删除绑定的商品
	 * @param id
	 * @param themeCatalogId
	 */
	public void deleteThemeProduct(int id, int themeCatalogId);
	
	/**
     * 主题对应的商品
     * @param themeId
     * @param themeCatalogId
     */
	List<Map<String,Object>> getThemeproductListMapBythemeId(Map<String, Object> param);
	
	/**
     * 主题首页显示的主题商品详情
     * @param themeId
     */
	List<Map<String, Object>> getThemeProductDetailListMap(Map<String, Object> param);
	
	/**
     * 主题首页显示的主题国家列表
     * @param themeId
     */
	List<Map<String, Object>> getThemeProductCityList(Map<String, Object> param);
}
