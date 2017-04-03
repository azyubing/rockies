package com.rockies.mapper;

import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.ProductTemplate;

public interface ProductTemplateMapper extends IBaseInfoMapper<ProductTemplate> {
    Map<String,Object> selectByPtypeAndCode(Map<String, Object> param);
}