package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.PackageInfo;

public interface PackageInfoMapper extends IBaseInfoMapper<PackageInfo> {

    PackageInfo selectPackageInfoByParam(Map<String,Object> param);
    
}