package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.EntRouteDetailInfoMapper;
import com.rockies.model.EntRouteDetailInfo;
import com.rockies.services.IEntRouteDetailInfoService;

@Service
public class EntRouteDetailInfoServiceImpl implements IEntRouteDetailInfoService {

@Autowired private EntRouteDetailInfoMapper entRouteDetailInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = entRouteDetailInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(EntRouteDetailInfo t) throws Exception {
        int i = 0;
        i = entRouteDetailInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(EntRouteDetailInfo t) throws Exception {
        int i = 0;
        i = entRouteDetailInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(EntRouteDetailInfo t) throws Exception {
        int i = 0;
        i = entRouteDetailInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(EntRouteDetailInfo t) throws Exception {
        int i = 0;
        i = entRouteDetailInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(EntRouteDetailInfo t) throws Exception {
        int i = 0;
        i = entRouteDetailInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public EntRouteDetailInfo selectByPrimaryKey(EntRouteDetailInfo t) {
        EntRouteDetailInfo info = null;
        try{
            info = entRouteDetailInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<EntRouteDetailInfo> selectAllListByParam(Map<String, Object> param) {
        List<EntRouteDetailInfo> list = null;
        try{
            list = entRouteDetailInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(EntRouteDetailInfoServiceImpl.class);
}
