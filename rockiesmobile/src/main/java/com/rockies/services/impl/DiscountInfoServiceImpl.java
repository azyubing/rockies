package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.DiscountInfoMapper;
import com.rockies.model.DiscountInfo;
import com.rockies.services.IDiscountInfoService;

@Service
public class DiscountInfoServiceImpl implements IDiscountInfoService {

    @Autowired private DiscountInfoMapper discountInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = discountInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(DiscountInfo t) throws Exception {
        int i = 0;
        i = discountInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(DiscountInfo t) throws Exception {
        int i = 0;
        i = discountInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(DiscountInfo t) throws Exception {
        int i = 0;
        i = discountInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(DiscountInfo t) throws Exception {
        int i = 0;
        i = discountInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(DiscountInfo t) throws Exception {
        int i = 0;
        i = discountInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public DiscountInfo selectByPrimaryKey(DiscountInfo t) {
        DiscountInfo info = null;
        try{
            info = discountInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<DiscountInfo> selectAllListByParam(Map<String, Object> param) {
        List<DiscountInfo> list = null;
        try{
            list = discountInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(DiscountInfoServiceImpl.class);
}
