package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.ProductTemplate;
import com.rockies.ec.services.base.IBaseInfoService;

public interface IProductTemplateService extends IBaseInfoService<ProductTemplate> {
    Map<String,Object> selectByIdAndPtype(Map<String,Object> param);
}
