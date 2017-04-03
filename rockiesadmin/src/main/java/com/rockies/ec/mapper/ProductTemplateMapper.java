package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.ProductTemplate;

public interface ProductTemplateMapper extends IBaseInfoMapper<ProductTemplate> {
    Map<String,Object> selectByPtypeAndCode(Map<String,Object> param);
}