package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.TrafficInfo;

public interface TrafficInfoMapper extends IBaseInfoMapper<TrafficInfo> {
    TrafficInfo selectTrafficInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectTrafficListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectStartplace();
    List<Map<String,Object>> selectEndplace();
    List<Map<String,Object>> selectCarlvl();
    List<Map<String,Object>> selectCartype();
    
    int countTrafficListByParam(Map<String, Object> param);
	int getTrafficListCount(Map<String, Object> param);
}