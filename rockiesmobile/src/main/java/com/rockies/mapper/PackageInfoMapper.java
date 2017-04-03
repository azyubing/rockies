package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.PackageInfo;

public interface PackageInfoMapper extends IBaseInfoMapper<PackageInfo> {

    PackageInfo selectPackageInfoByParam(Map<String, Object> param);
    List<Map<String,Object>> selectPackageListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectDaycnt();
    List<Map<String,Object>> selectStaylvl();
    List<Map<String,Object>> selectProperty();
    PackageInfo selectByPrimaryKey(String pid);
    
    int countPackageListByParam(Map<String, Object> param);
	int getPackageCount();
	int getPackageListCount(Map<String, Object> param);
}