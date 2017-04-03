package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.ProductInfoMapper;
import com.rockies.mapper.VillaInfoMapper;
import com.rockies.model.VillaInfo;
import com.rockies.services.IVillaInfoService;

@Service
public class VillaInfoServiceImpl implements IVillaInfoService {

    @Autowired private VillaInfoMapper villaInfoMapper;
    @Autowired private ProductInfoMapper productInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = villaInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(VillaInfo t) throws Exception {
        int i = 0;
        i = villaInfoMapper.deleteByPrimaryKey(t);    
        i = productInfoMapper.deleteByPrimaryKey(t); 
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(VillaInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insert(t);
        i = villaInfoMapper.insert(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(VillaInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insertSelective(t); 
        i = villaInfoMapper.insertSelective(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(VillaInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKeySelective(t); 
        i = villaInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(VillaInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKey(t);
        i = villaInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public VillaInfo selectByPrimaryKey(VillaInfo t) {
        VillaInfo info = null;
        try{
            info = villaInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<VillaInfo> selectAllListByParam(Map<String, Object> param) {
        List<VillaInfo> list = null;
        try{
            list = villaInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public VillaInfo selectVillaInfoByParam(Map<String, Object> param) {
        VillaInfo info = null;
        try{
            info = villaInfoMapper.selectVillaInfoByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectVillaInfoByParam:" + e.toString());
        }
        return info;
    }

	@Override
	public List<Map<String, Object>> selectVillaListByParam(Map<String, Object> param) {
		List<Map<String, Object>> info = null;
        try{
            info = villaInfoMapper.selectVillaListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectVillaInfoByParam:" + e.toString());
        }
        return info;
	}
	

	@Override
	public List<Map<String, Object>> selectArea(Map<String,Object> param) {
		List<Map<String, Object>> info = null;
        try{
            info = villaInfoMapper.selectArea(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectArea:" + e.toString());
        }
        return info;
	}

	@Override
	public List<Map<String, Object>> selectGeography() {
		List<Map<String, Object>> info = null;
        try{
            info = villaInfoMapper.selectGeography();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectArea:" + e.toString());
        }
        return info;
	}

	@Override
	public List<Map<String, Object>> selectRoomCnt() {
		List<Map<String, Object>> info = null;
        try{
            info = villaInfoMapper.selectRoomCnt();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectArea:" + e.toString());
        }
        return info;
	}

    @Override
    public int countVillaListByParam(Map<String, Object> param) {
        int i = 0;
        try{
            i = villaInfoMapper.countVillaListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".countVillaListByParam:" + e.toString());
        }
        return i;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(VillaInfoServiceImpl.class);

	@Override
	public int getVillaCount() {
		 int i = 0;
        try{
            i = villaInfoMapper.getVillaCount();
        }catch(Exception e){
            logger.error("########## fail."+this.getClass()+".getVillaCount:" + e.toString());
        }
        return i;
	}

	@Override
	public Integer getVillaListCount(Map<String, Object> param) {
		 int i = 0;
	        try{
	            i = villaInfoMapper.getVillaListCount(param);
	        }catch(Exception e){
	            logger.error("########## fail."+this.getClass()+".getVillaListCount:" + e.toString());
	        }
	        return i;
	}


}
