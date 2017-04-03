package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.HotelInfo;
import com.rockies.ec.services.base.IBaseInfoService;


public interface IHotelInfoService extends IBaseInfoService<HotelInfo> {
    
    HotelInfo selectHotelInfoByParam(Map<String,Object> param);
    
    
}
