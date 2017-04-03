package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.VillaInfo;

public interface VillaInfoMapper extends IBaseInfoMapper<VillaInfo>{
    VillaInfo selectVillaInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectVillaListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectArea(Map<String, Object> param);
    List<Map<String,Object>> selectGeography();
    List<Map<String,Object>> selectRoomCnt();
    
    int countVillaListByParam(Map<String, Object> param);
	int getVillaCount();
	int getVillaListCount(Map<String, Object> param);
}