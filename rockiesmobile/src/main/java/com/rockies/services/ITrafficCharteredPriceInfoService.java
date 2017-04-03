package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.TrafficCharteredPriceInfo;
import com.rockies.services.base.IBaseInfoService;

public interface ITrafficCharteredPriceInfoService extends IBaseInfoService<TrafficCharteredPriceInfo> {

	/**
	 * 获取价格项
	 * @param id
	 * @return
	 */
	TrafficCharteredPriceInfo selectTrafficCharteredPriceInfoById(int id);
	
	/**
	 * 有效价格项
	 * @param param
	 * @return
	 */
	List<TrafficCharteredPriceInfo> selectPriceInfo(Map<String, Object> param);
	
	

}
