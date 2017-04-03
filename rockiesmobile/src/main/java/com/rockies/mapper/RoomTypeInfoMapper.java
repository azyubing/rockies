package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.RoomTypeInfo;

public interface RoomTypeInfoMapper extends IBaseInfoMapper<RoomTypeInfo> {
    
    Map<String,Object> selectRoomInfoByParam(Map<String, Object> param);

	List<Map<String, Object>> selectRoomPriceByParam(Map<String, Object> param);
}