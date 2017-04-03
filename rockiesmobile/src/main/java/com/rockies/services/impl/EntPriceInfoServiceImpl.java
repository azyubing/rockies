package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.EntPriceInfoMapper;
import com.rockies.model.EntPriceInfo;
import com.rockies.model.PackagePriceInfo;
import com.rockies.services.IEntPriceInfoService;

@Service
public class EntPriceInfoServiceImpl implements IEntPriceInfoService {
    
    @Autowired private EntPriceInfoMapper entPriceInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = entPriceInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(EntPriceInfo t) throws Exception {
        int i = 0;
        i = entPriceInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(EntPriceInfo t) throws Exception {
        int i = 0;
        i = entPriceInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(EntPriceInfo t) throws Exception {
        int i = 0;
        i = entPriceInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(EntPriceInfo t) throws Exception {
        int i = 0;
        i = entPriceInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(EntPriceInfo t) throws Exception {
        int i = 0;
        i = entPriceInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public EntPriceInfo selectByPrimaryKey(EntPriceInfo t) {
        EntPriceInfo info = null;
        try{
            info = entPriceInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<EntPriceInfo> selectAllListByParam(Map<String, Object> param) {
        List<EntPriceInfo> list = null;
        try{
            list = entPriceInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(EntPriceInfoServiceImpl.class);

	@Override
	public EntPriceInfo selectEntPriceInfoById(int id) {
		return entPriceInfoMapper.selectEntPriceInfoById(id);
	}

	@Override
	public List<PackagePriceInfo> getEntPriceCal(Map<String, Object> param) {
		return entPriceInfoMapper.getEntPriceCal(param);
	}
}
