package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.EntInfoMapper;
import com.rockies.ec.mapper.ProductInfoMapper;
import com.rockies.ec.model.EntInfo;
import com.rockies.ec.services.IEntInfoService;

@Service
public class EntInfoServiceImpl implements IEntInfoService {

    @Autowired private EntInfoMapper entInfoMapper;
    @Autowired private ProductInfoMapper productInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = entInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(EntInfo t) throws Exception {
        int i = 0;
        i = entInfoMapper.deleteByPrimaryKey(t);    
        i = productInfoMapper.deleteByPrimaryKey(t); 
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(EntInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insert(t);
        i = entInfoMapper.insert(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(EntInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insertSelective(t); 
        i = entInfoMapper.insertSelective(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(EntInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKeySelective(t); 
        i = entInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(EntInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKey(t);
        i = entInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public EntInfo selectByPrimaryKey(EntInfo t) {
        EntInfo info = null;
        try{
            info = entInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<EntInfo> selectAllListByParam(Map<String, Object> param) {
        List<EntInfo> list = null;
        try{
            list = entInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public EntInfo selectEntInfoByParam(Map<String, Object> param) {
        EntInfo info = null;
        try{
            info = entInfoMapper.selectEntInfoByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectEntInfoByParam:" + e.toString());
        }
        return info;
    }

    private static final Logger logger = LoggerFactory.getLogger(EntInfoServiceImpl.class);
}
