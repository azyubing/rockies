package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.HotelInfo;

public interface HotelInfoMapper extends IBaseInfoMapper<HotelInfo> {

    HotelInfo selectHotelInfoByParam(Map<String,Object> param);
    
    
}