package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.VillaDetailPriceInfoMapper;
import com.rockies.ec.model.VillaDetailPriceInfo;
import com.rockies.ec.services.IVillaDetailPriceInfoService;

@Service
public class VillaDetailPriceInfoServiceImpl implements IVillaDetailPriceInfoService {

    @Autowired private VillaDetailPriceInfoMapper villaDetailPriceInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = villaDetailPriceInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(VillaDetailPriceInfo t) throws Exception {
        int i = 0;
        i = villaDetailPriceInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(VillaDetailPriceInfo t) throws Exception {
        int i = 0;
        i = villaDetailPriceInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(VillaDetailPriceInfo t) throws Exception {
        int i = 0;
        i = villaDetailPriceInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(VillaDetailPriceInfo t) throws Exception {
        int i = 0;
        i = villaDetailPriceInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(VillaDetailPriceInfo t) throws Exception {
        int i = 0;
        i = villaDetailPriceInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public VillaDetailPriceInfo selectByPrimaryKey(VillaDetailPriceInfo t) {
        VillaDetailPriceInfo info = null;
        try{
            info = villaDetailPriceInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<VillaDetailPriceInfo> selectAllListByParam(Map<String, Object> param) {
        List<VillaDetailPriceInfo> list = null;
        try{
            list = villaDetailPriceInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(VillaDetailPriceInfoMapper.class);

}
