package com.rockies.ec.services;

import java.util.List;
import java.util.Map;

import com.rockies.ec.services.base.BaseService;
import com.rockies.ec.vo.ThemeCatalogVO;

public interface IThemeCatalogService extends BaseService {
	/**
	 * 主题分类vo
	 * @param id
	 * @return
	 */
	public ThemeCatalogVO getThemeCatalogVO(int id);
	
	/**
	 * 查询分类下绑定的商品
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getAllProductList(int id);
	
	/**
	 * 查询分类下绑定的商品count
	 * @param param
	 * @return
	 */
	public int getAllProductCount(Map param);

	public List<Map<String, Object>> themeProductAdminSearchList(Map param);
}
