package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.TrafficCharteredPriceInfo;

public interface TrafficCharteredPriceInfoMapper extends IBaseInfoMapper<TrafficCharteredPriceInfo> {

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