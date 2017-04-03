package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.RoomTypeInfo;
import com.rockies.services.base.IBaseInfoService;

public interface IRoomTypeInfoService extends IBaseInfoService<RoomTypeInfo> {
    
    Map<String,Object> selectRoomInfoByParam(Map<String, Object> param);

	List<Map<String, Object>> selectRoomPriceByParam(Map<String, Object> param);
}
