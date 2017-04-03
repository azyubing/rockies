package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.services.base.BaseService;
import com.rockies.vo.DestinationVO;

public interface IDestinationService extends BaseService{
	/**
	 * 获取目的地vo
	 * @param id
	 * @return
	 */
	public DestinationVO getDestinationVO(int id);
	/**
	 * 验证产品是否绑定
	 * @param map
	 * @return
	 */
	public boolean checkProductExist(Map map);
	/**
	 * 保存绑定产品
	 * @param productIds
	 * @param themeCatalogId
	 * @param userId
	 * @param themeId
	 */
	public void saveListDestinationProduc(String ids, int destinationId, int i);
	/**
	 * 获取目的地绑定所有产品
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAllProductList(int id);
	
	/**
	 * 删除绑定的产品
	 * @param id
	 */
	public void deleteDestinationProduct(int id);
	public List getDestinationDetail(Long desId);
}
