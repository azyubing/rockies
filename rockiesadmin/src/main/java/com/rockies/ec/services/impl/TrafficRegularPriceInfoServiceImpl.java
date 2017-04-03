package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.TrafficRegularPriceInfoMapper;
import com.rockies.ec.model.TrafficRegularPriceInfo;
import com.rockies.ec.services.ITrafficRegularPriceInfoService;

@Service
public class TrafficRegularPriceInfoServiceImpl implements ITrafficRegularPriceInfoService {

    @Autowired private TrafficRegularPriceInfoMapper trafficRegularPriceInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = trafficRegularPriceInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(TrafficRegularPriceInfo t) throws Exception {
        int i = 0;
        i = trafficRegularPriceInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(TrafficRegularPriceInfo t) throws Exception {
        int i = 0;
        i = trafficRegularPriceInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(TrafficRegularPriceInfo t) throws Exception {
        int i = 0;
        i = trafficRegularPriceInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(TrafficRegularPriceInfo t) throws Exception {
        int i = 0;
        i = trafficRegularPriceInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(TrafficRegularPriceInfo t) throws Exception {
        int i = 0;
        i = trafficRegularPriceInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public TrafficRegularPriceInfo selectByPrimaryKey(TrafficRegularPriceInfo t) {
        TrafficRegularPriceInfo info = null;
        try{
            info = trafficRegularPriceInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<TrafficRegularPriceInfo> selectAllListByParam(Map<String, Object> param) {
        List<TrafficRegularPriceInfo> list = null;
        try{
            list = trafficRegularPriceInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(TrafficRegularPriceInfoServiceImpl.class);
}
