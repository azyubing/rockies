package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.PackagePriceInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IPackagePriceInfoService extends IBaseInfoService<PackagePriceInfo> {

	/**
	 * 通过id查找价格项
	 * @param id
	 * @return
	 */
	PackagePriceInfo selectPackagePriceInfoById(int id);

	/**
	 * 价格日历
	 * @param param
	 * @return
	 */
	List<PackagePriceInfo> getPackagePriceCal(Map<String, Object> param);

}
