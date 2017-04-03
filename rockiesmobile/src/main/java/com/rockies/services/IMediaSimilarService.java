package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.services.base.BaseService;

public interface IMediaSimilarService extends BaseService {
	/**
	 * 验证产品是否绑定
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	boolean checkProductExist(Map map);
	
	/**
	 * 保存绑定产品
	 * @param productIds
	 * @param themeCatalogId
	 * @param userId
	 * @param themeId
	 */
	public void saveListMediaSimilarProduct(String productIds, int mediaId, int userId);

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
