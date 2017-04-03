package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.TrafficCharteredPriceInfoMapper;
import com.rockies.ec.model.TrafficCharteredPriceInfo;
import com.rockies.ec.services.ITrafficCharteredPriceInfoService;

@Service
public class TrafficCharteredPriceInfoServiceImpl implements ITrafficCharteredPriceInfoService {

    @Autowired private TrafficCharteredPriceInfoMapper trafficCharteredPriceInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = trafficCharteredPriceInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(TrafficCharteredPriceInfo t) throws Exception {
        int i = 0;
        i = trafficCharteredPriceInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(TrafficCharteredPriceInfo t) throws Exception {
        int i = 0;
        i = trafficCharteredPriceInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(TrafficCharteredPriceInfo t) throws Exception {
        int i = 0;
        i = trafficCharteredPriceInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(TrafficCharteredPriceInfo t) throws Exception {
        int i = 0;
        i = trafficCharteredPriceInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(TrafficCharteredPriceInfo t) throws Exception {
        int i = 0;
        i = trafficCharteredPriceInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public TrafficCharteredPriceInfo selectByPrimaryKey(TrafficCharteredPriceInfo t) {
        TrafficCharteredPriceInfo info = null;
        try{
            info = trafficCharteredPriceInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<TrafficCharteredPriceInfo> selectAllListByParam(Map<String, Object> param) {
        List<TrafficCharteredPriceInfo> list = null;
        try{
            list = trafficCharteredPriceInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(TrafficCharteredPriceInfoServiceImpl.class);

}
