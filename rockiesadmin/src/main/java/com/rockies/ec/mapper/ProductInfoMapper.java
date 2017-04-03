package com.rockies.ec.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.ProductInfo;

public interface ProductInfoMapper extends IBaseInfoMapper<ProductInfo> {
    
    List<Map<String,Object>> selectProductInfoListByParam(Map<String,Object> param);
    
}