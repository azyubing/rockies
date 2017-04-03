package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.TrafficInfo;
import com.rockies.services.base.IBaseInfoService;

public interface ITrafficInfoService extends IBaseInfoService<TrafficInfo> {
    TrafficInfo selectTrafficInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectTrafficListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectStartplace();
    List<Map<String,Object>> selectEndplace();
    List<Map<String,Object>> selectCarlvl();
    List<Map<String,Object>> selectCartype();
    
    int countTrafficListByParam(Map<String, Object> param);
	Integer getTrafficListCount(Map<String, Object> param);
}
