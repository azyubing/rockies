package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.TrafficInfo;
import com.rockies.ec.services.base.IBaseInfoService;

public interface ITrafficInfoService extends IBaseInfoService<TrafficInfo> {
    TrafficInfo selectTrafficInfoByParam(Map<String,Object> param);

}
