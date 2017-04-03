package com.rockies.ec.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.ec.mapper.base.BaseMapper;
import com.rockies.ec.model.DestinationProduct;
import com.rockies.ec.vo.DestinationVO;

public interface DestinationMapper extends BaseMapper{
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
	@SuppressWarnings("rawtypes")
	int checkProductExist(Map map);
	
	/**
	 * 获取目的地绑定所有套餐
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAllProductList(int id);
	
	void saveDestinationProduct(DestinationProduct destinationProduct);
	
	/**
	 * 删除绑定的产品
	 * @param id
	 */
	public void deleteDestinationProduct(int id);
}
