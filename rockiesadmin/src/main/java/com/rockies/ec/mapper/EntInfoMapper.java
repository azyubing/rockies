package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.EntInfo;

public interface EntInfoMapper extends IBaseInfoMapper<EntInfo>{
    
    EntInfo selectEntInfoByParam(Map<String,Object> param);

}