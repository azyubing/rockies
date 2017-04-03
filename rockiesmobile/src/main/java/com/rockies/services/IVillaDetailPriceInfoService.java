package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.PackagePriceInfo;
import com.rockies.model.VillaDetailPriceInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IVillaDetailPriceInfoService extends IBaseInfoService<VillaDetailPriceInfo> {

	/**
	 * 通过id查找价格项
	 * @param id
	 * @return
	 */
	VillaDetailPriceInfo selectVillaDetailPriceInfoById(int id);

	/**
	 * 别墅价格日历
	 * @param param
	 * @return
	 */
	List<PackagePriceInfo> getVillaPriceCal(Map<String, Object> param);

}
