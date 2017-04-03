package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.TrafficInfo;

public interface TrafficInfoMapper extends IBaseInfoMapper<TrafficInfo> {
    TrafficInfo selectTrafficInfoByParam(Map<String,Object> param);
}