package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rockies.ec.mapper.HotelInfoMapper;
import com.rockies.ec.mapper.ProductInfoMapper;
import com.rockies.ec.model.HotelInfo;
import com.rockies.ec.services.IHotelInfoService;

@Service
public class HotelInfoServiceImpl implements IHotelInfoService {

    
    @Autowired private HotelInfoMapper hotelInfoMapper;
    @Autowired private ProductInfoMapper productInfoMapper;


    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = hotelInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }
    @Override
    @Transactional
    public boolean deleteByPrimaryKey(HotelInfo hotelInfo) throws Exception { 
        int i = 0;
        i = hotelInfoMapper.deleteByPrimaryKey(hotelInfo);    
        i = productInfoMapper.deleteByPrimaryKey(hotelInfo); 
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    @Transactional
    public boolean insert(HotelInfo hotelInfo) throws Exception {
        int i = 0;
        i = productInfoMapper.insert(hotelInfo);
        i = hotelInfoMapper.insert(hotelInfo);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
        
    }

    @Override
    @Transactional
    public boolean insertSelective(HotelInfo hotelInfo) throws Exception {
        int i = 0;
        i = productInfoMapper.insertSelective(hotelInfo); 
        i = hotelInfoMapper.insertSelective(hotelInfo);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    @Transactional
    public boolean updateByPrimaryKeySelective(HotelInfo hotelInfo) throws Exception {     
        int i = 0;
        i = productInfoMapper.updateByPrimaryKeySelective(hotelInfo); 
        i = hotelInfoMapper.updateByPrimaryKeySelective(hotelInfo);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    @Transactional
    public boolean updateByPrimaryKey(HotelInfo hotelInfo) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKey(hotelInfo);
        i = hotelInfoMapper.updateByPrimaryKey(hotelInfo);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }


    
    @Override
    public HotelInfo selectByPrimaryKey(HotelInfo t) {
        HotelInfo hotelInfo = null;
        try{
            hotelInfo = hotelInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return hotelInfo;
    }

    @Override
    public List<HotelInfo> selectAllListByParam(Map<String, Object> param) {
        List<HotelInfo> list = null;
        try{
            list = hotelInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public HotelInfo selectHotelInfoByParam(Map<String, Object> param) {
        HotelInfo hotelInfo = null;
        try{
            hotelInfo = hotelInfoMapper.selectHotelInfoByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectHotelInfoByParam:" + e.toString());
        }
        return hotelInfo;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(HotelInfoServiceImpl.class);

}
