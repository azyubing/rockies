package com.rockies.services;

import java.util.Map;

import com.rockies.model.ProductTemplate;
import com.rockies.services.base.IBaseInfoService;

public interface IProductTemplateService extends IBaseInfoService<ProductTemplate> {
    Map<String,Object> selectByIdAndPtype(Map<String, Object> param);
}
