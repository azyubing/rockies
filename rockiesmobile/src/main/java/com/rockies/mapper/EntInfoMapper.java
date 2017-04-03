package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.EntInfo;

public interface EntInfoMapper extends IBaseInfoMapper<EntInfo>{
    
    EntInfo selectEntInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectEntListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectEnttype();
    List<Map<String,Object>> selectServicetype();
    List<Map<String,Object>> selectLanguage();
    
    int countEntListByParam(Map<String, Object> param);
	int getEntListCount(Map<String, Object> param);
}