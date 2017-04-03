package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.EntInfo;
import com.rockies.ec.services.base.IBaseInfoService;

public interface IEntInfoService extends IBaseInfoService<EntInfo> {
    
    EntInfo selectEntInfoByParam(Map<String,Object> param);
}
