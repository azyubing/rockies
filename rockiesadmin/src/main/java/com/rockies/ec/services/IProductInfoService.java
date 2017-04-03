package com.rockies.ec.services;

import java.util.List;
import java.util.Map;

import com.rockies.ec.model.ProductInfo;
import com.rockies.ec.services.base.IBaseInfoService;

public interface IProductInfoService extends IBaseInfoService<ProductInfo> {

    List<Map<String,Object>> selectProductInfoListByParam(Map<String,Object> param);
}
