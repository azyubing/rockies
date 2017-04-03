package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.PackageInfo;
import com.rockies.ec.services.base.IBaseInfoService;

public interface IPackageInfoService extends IBaseInfoService<PackageInfo> {
    
    PackageInfo selectPackageInfoByParam(Map<String,Object> param);

}
