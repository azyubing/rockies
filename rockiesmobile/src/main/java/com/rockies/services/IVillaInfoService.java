package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.VillaInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IVillaInfoService extends IBaseInfoService<VillaInfo> {
    VillaInfo selectVillaInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectVillaListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectArea(Map<String, Object> param);
    List<Map<String,Object>> selectGeography();
    List<Map<String,Object>> selectRoomCnt();
    
    int countVillaListByParam(Map<String, Object> param);
	int getVillaCount();
	Integer getVillaListCount(Map<String, Object> param);
}
