package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.VillaInfo;
import com.rockies.ec.services.base.IBaseInfoService;

public interface IVillaInfoService extends IBaseInfoService<VillaInfo> {
    VillaInfo selectVillaInfoByParam(Map<String,Object> param);
}
