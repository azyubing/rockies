package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.EntPriceInfo;
import com.rockies.model.PackagePriceInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IEntPriceInfoService extends IBaseInfoService<EntPriceInfo> {

	/**
	 * 通过id查找价格项
	 * @param id
	 * @return
	 */
	EntPriceInfo selectEntPriceInfoById(int id);

	/**
	 * 价格日历
	 * @param param
	 * @return
	 */
	List<PackagePriceInfo> getEntPriceCal(Map<String, Object> param);

}
