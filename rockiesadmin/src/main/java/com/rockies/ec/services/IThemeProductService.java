package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.services.base.BaseService;

public interface IThemeProductService extends BaseService {
	/**
	 * 保存绑定产品
	 * @param productIds
	 * @param themeCatalogId
	 * @param userId
	 * @param themeId
	 */
	public void saveListThemeProduct(String productIds,String themeCatalogId,int userId,int themeId);

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
}
