package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.CollectionMapper;
import com.rockies.model.Collection;
import com.rockies.services.ICollectionService;

@Service
public class CollectionServiceImpl implements ICollectionService {

	@Autowired private CollectionMapper collectionMapper;
	
	@Override
	public int getCount(Map<String, Object> param) {
		int i = 0;
        try{
            i = collectionMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
	}

	@Override
	public boolean deleteByPrimaryKey(Collection t) throws Exception {
		int i = 0;
        i = collectionMapper.deleteByPrimaryKey(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
	}

	@Override
	public boolean insert(Collection t) throws Exception {
		int i = 0;
        i = collectionMapper.insert(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
	}

	@Override
	public boolean insertSelective(Collection t) throws Exception {
		int i = 0;
        i = collectionMapper.insertSelective(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
	}

	@Override
	public boolean updateByPrimaryKeySelective(Collection t) throws Exception {
		int i = 0;
        i = collectionMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
	}

	@Override
	public boolean updateByPrimaryKey(Collection t) throws Exception {
		int i = 0;
        i = collectionMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
	}

	@Override
	public Collection selectByPrimaryKey(Collection t) {
		Collection info = null;
        try{
            info = collectionMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
	}

	@Override
	public List<Collection> selectAllListByParam(Map<String, Object> param) {
		List<Collection> list = null;
        try{
            list = collectionMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
	}

	@Override
	public List<Map<String, Object>> selectCollectionProductListByParam(Map<String, Object> param) {
		List<Map<String, Object>> list = null;
        try{
            list = collectionMapper.selectCollectionProductListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectCollectionProductListByParam:" + e.toString());
        }
        return list;
	}
	
	@Override
	public int selectByPid(Collection collection) {
		int i = 0;
        try{
            i = collectionMapper.selectByPid(collection);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPid:" + e.toString());
        }
        return i;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CollectionServiceImpl.class);

	@Override
	public boolean delete(Collection favorite) {
		int i = 0;
        i = collectionMapper.delete(favorite);    
        if(i == 1){
            return true;
        }else{
            return false;
        }
	}


}
