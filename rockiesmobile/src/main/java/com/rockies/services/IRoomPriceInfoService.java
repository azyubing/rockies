package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.PackagePriceInfo;
import com.rockies.model.RoomPriceInfo;
import com.rockies.model.VillaDetailPriceInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IRoomPriceInfoService extends IBaseInfoService<RoomPriceInfo> {

	/**
	 * 通过id查找价格项
	 * @param id
	 * @return
	 */
	RoomPriceInfo selectRoomPriceInfoById(int id);
	
	/**
	 * 酒店价格日历
	 * @param param
	 * @return
	 */
	List<PackagePriceInfo> gethotelPriceCal(Map<String, Object> param);

	RoomPriceInfo queryPrice(Map<String, Object> param);

    
}
