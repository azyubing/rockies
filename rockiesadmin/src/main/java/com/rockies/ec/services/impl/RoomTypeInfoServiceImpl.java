package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.RoomTypeInfoMapper;
import com.rockies.ec.model.RoomTypeInfo;
import com.rockies.ec.services.IRoomTypeInfoService;

@Service
public class RoomTypeInfoServiceImpl implements IRoomTypeInfoService {

    @Autowired private RoomTypeInfoMapper roomTypeInfoMapper;
    
    @Override
    public boolean deleteByPrimaryKey(RoomTypeInfo record) throws Exception {
        int i = 0;
        i = roomTypeInfoMapper.updateByPrimaryKeySelective(record);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(RoomTypeInfo record) throws Exception {
        int i = 0;
        i = roomTypeInfoMapper.insert(record);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(RoomTypeInfo record) throws Exception {
        int i = 0;
        i = roomTypeInfoMapper.insertSelective(record);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public RoomTypeInfo selectByPrimaryKey(RoomTypeInfo record) {
        RoomTypeInfo roomTypeInfo = null;
        try{
            roomTypeInfo = roomTypeInfoMapper.selectByPrimaryKey(record);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return roomTypeInfo;
    }

    @Override
    public List<RoomTypeInfo> selectAllListByParam(Map<String, Object> param) {
        List<RoomTypeInfo> list = null;
        try{
            list = roomTypeInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectRoomTypeByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public boolean updateByPrimaryKeySelective(RoomTypeInfo record) throws Exception {
        int i = 0;
        i = roomTypeInfoMapper.updateByPrimaryKeySelective(record);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(RoomTypeInfo record) throws Exception {
        int i = 0;
        i = roomTypeInfoMapper.updateByPrimaryKey(record);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = roomTypeInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public Map<String, Object> selectRoomInfoByParam(Map<String, Object> param) {
        Map<String, Object> map = null;
        try{
            map = roomTypeInfoMapper.selectRoomInfoByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectRoomInfoByParam:" + e.toString());
        }
        return map;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(RoomTypeInfoServiceImpl.class);



}
