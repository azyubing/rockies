package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.ProductInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IProductInfoService extends IBaseInfoService<ProductInfo> {

    List<Map<String,Object>> selectProductInfoListByParam(Map<String, Object> param);
    
    List<Map<String,Object>> queryProductsByParam(Map<String, Object> param);

	int getOutCount(Map<String, Object> param);
}
