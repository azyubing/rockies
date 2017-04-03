package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.ProductInfo;

public interface ProductInfoMapper extends IBaseInfoMapper<ProductInfo> {
    
    List<Map<String,Object>> selectProductInfoListByParam(Map<String, Object> param);
    
    List<Map<String, Object>> queryProductsByParam(Map<String, Object> param);

	int getOutCount(Map<String, Object> param);
    
}