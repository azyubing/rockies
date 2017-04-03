package com.rockies.ec.services;

import java.util.Map;

import com.rockies.ec.model.RoomTypeInfo;
import com.rockies.ec.services.base.IBaseInfoService;

public interface IRoomTypeInfoService extends IBaseInfoService<RoomTypeInfo> {
    
    Map<String,Object> selectRoomInfoByParam(Map<String,Object> param);
}
