package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.VillaInfo;

public interface VillaInfoMapper extends IBaseInfoMapper<VillaInfo>{
    VillaInfo selectVillaInfoByParam(Map<String,Object> param);
}