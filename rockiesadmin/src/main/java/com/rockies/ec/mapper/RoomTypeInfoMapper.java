package com.rockies.ec.mapper;

import java.util.Map;

import com.rockies.ec.mapper.base.IBaseInfoMapper;
import com.rockies.ec.model.RoomTypeInfo;

public interface RoomTypeInfoMapper extends IBaseInfoMapper<RoomTypeInfo> {
    
    Map<String,Object> selectRoomInfoByParam(Map<String,Object> param);
}