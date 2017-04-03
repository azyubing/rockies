package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.HotelInfo;
import com.rockies.services.base.IBaseInfoService;


public interface IHotelInfoService extends IBaseInfoService<HotelInfo> {
    
    HotelInfo selectHotelInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectHotelListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectArea(Map<String, Object> param);
    List<Map<String,Object>> selectStaylvl();
    List<Map<String,Object>> selectMaxCnt();
    
    int countHotelListByParam(Map<String, Object> param);
	Integer getHotelListCount(Map<String, Object> param);
}
