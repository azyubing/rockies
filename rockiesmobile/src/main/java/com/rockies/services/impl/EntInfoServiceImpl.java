package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.EntInfoMapper;
import com.rockies.mapper.ProductInfoMapper;
import com.rockies.model.EntInfo;
import com.rockies.services.IEntInfoService;

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


	@Override
	public List<Map<String, Object>> selectEntListByParam(Map<String, Object> param) {
		List<Map<String, Object>> list = null;
        try{
            list = entInfoMapper.selectEntListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectEntListByParam:" + e.toString());
        }
        return list;
	}

	@Override
	public List<Map<String, Object>> selectEnttype() {
		List<Map<String, Object>> list = null;
        try{
            list = entInfoMapper.selectEnttype();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectEnttype:" + e.toString());
        }
        return list;
	}

	@Override
	public List<Map<String, Object>> selectServicetype() {
		List<Map<String, Object>> list = null;
        try{
            list = entInfoMapper.selectServicetype();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectServicetype:" + e.toString());
        }
        return list;
	}

	@Override
	public List<Map<String, Object>> selectLanguage() {
		List<Map<String, Object>> list = null;
        try{
            list = entInfoMapper.selectLanguage();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectLanguage:" + e.toString());
        }
        return list;
	}

    @Override
    public int countEntListByParam(Map<String, Object> param) {
        int i = 0;
        try{
            i = entInfoMapper.countEntListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".countEntListByParam:" + e.toString());
        }
        return i;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(EntInfoServiceImpl.class);

	@Override
	public Integer getEntListCount(Map<String, Object> param) {
		int i = 0;
        try{
            i = entInfoMapper.getEntListCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getEntListCount:" + e.toString());
        }
        return i;
	}

}
