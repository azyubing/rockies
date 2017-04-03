package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.RouteDayInfoMapper;
import com.rockies.ec.model.RouteDayInfo;
import com.rockies.ec.services.IRouteDayInfoService;

@Service
public class RouteDayInfoServiceImpl implements IRouteDayInfoService {

    @Autowired private RouteDayInfoMapper routeDayInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = routeDayInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(RouteDayInfo t) throws Exception {
        int i = 0;
        i = routeDayInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(RouteDayInfo t) throws Exception {
        int i = 0;
        i = routeDayInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(RouteDayInfo t) throws Exception {
        int i = 0;
        i = routeDayInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(RouteDayInfo t) throws Exception {
        int i = 0;
        i = routeDayInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(RouteDayInfo t) throws Exception {
        int i = 0;
        i = routeDayInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public RouteDayInfo selectByPrimaryKey(RouteDayInfo t) {
        RouteDayInfo info = null;
        try{
            info = routeDayInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<RouteDayInfo> selectAllListByParam(Map<String, Object> param) {
        List<RouteDayInfo> list = null;
        try{
            list = routeDayInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(RouteDayInfoServiceImpl.class);
}
