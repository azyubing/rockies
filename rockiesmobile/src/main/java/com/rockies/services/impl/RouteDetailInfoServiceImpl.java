package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.RouteDetailInfoMapper;
import com.rockies.model.RouteDetailInfo;
import com.rockies.services.IRouteDetailInfoService;

@Service
public class RouteDetailInfoServiceImpl implements IRouteDetailInfoService {

    @Autowired private RouteDetailInfoMapper routeDetailInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = routeDetailInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(RouteDetailInfo t) throws Exception {
        int i = 0;
        i = routeDetailInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(RouteDetailInfo t) throws Exception {
        int i = 0;
        i = routeDetailInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(RouteDetailInfo t) throws Exception {
        int i = 0;
        i = routeDetailInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(RouteDetailInfo t) throws Exception {
        int i = 0;
        i = routeDetailInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(RouteDetailInfo t) throws Exception {
        int i = 0;
        i = routeDetailInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public RouteDetailInfo selectByPrimaryKey(RouteDetailInfo t) {
        RouteDetailInfo info = null;
        try{
            info = routeDetailInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<RouteDetailInfo> selectAllListByParam(Map<String, Object> param) {
        List<RouteDetailInfo> list = null;
        try{
            list = routeDetailInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(RouteDetailInfoServiceImpl.class);

}
