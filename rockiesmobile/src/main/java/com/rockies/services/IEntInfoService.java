package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.EntInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IEntInfoService extends IBaseInfoService<EntInfo> {
    
    EntInfo selectEntInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectEntListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectEnttype();
    List<Map<String,Object>> selectServicetype();
    List<Map<String,Object>> selectLanguage();
   
    int countEntListByParam(Map<String, Object> param);
	Integer getEntListCount(Map<String, Object> param);
}
