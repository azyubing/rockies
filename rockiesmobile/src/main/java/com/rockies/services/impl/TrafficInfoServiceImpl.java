package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.ProductInfoMapper;
import com.rockies.mapper.TrafficInfoMapper;
import com.rockies.model.TrafficInfo;
import com.rockies.services.ITrafficInfoService;

@Service
public class TrafficInfoServiceImpl implements ITrafficInfoService {

    @Autowired private TrafficInfoMapper trafficInfoMapper;
    @Autowired private ProductInfoMapper productInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = trafficInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(TrafficInfo t) throws Exception {
        int i = 0;
        i = trafficInfoMapper.deleteByPrimaryKey(t);    
        i = productInfoMapper.deleteByPrimaryKey(t); 
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(TrafficInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insert(t);
        i = trafficInfoMapper.insert(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(TrafficInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insertSelective(t); 
        i = trafficInfoMapper.insertSelective(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(TrafficInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKeySelective(t); 
        i = trafficInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(TrafficInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKey(t);
        i = trafficInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public TrafficInfo selectByPrimaryKey(TrafficInfo t) {
        TrafficInfo info = null;
        try{
            info = trafficInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<TrafficInfo> selectAllListByParam(Map<String, Object> param) {
        List<TrafficInfo> list = null;
        try{
            list = trafficInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public TrafficInfo selectTrafficInfoByParam(Map<String, Object> param) {
        TrafficInfo info = null;
        try{
            info = trafficInfoMapper.selectTrafficInfoByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectTrafficInfoByParam:" + e.toString());
        }
        return info;
    }

    @Override
    public List<Map<String, Object>> selectTrafficListByParam(Map<String, Object> param) {
        List<Map<String, Object>> list = null;
        try{
            list = trafficInfoMapper.selectTrafficListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectTrafficListByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectStartplace() {
        List<Map<String, Object>> list = null;
        try{
            list = trafficInfoMapper.selectStartplace();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectStartplace:" + e.toString());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectEndplace() {
        List<Map<String, Object>> list = null;
        try{
            list = trafficInfoMapper.selectEndplace();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectEndplace:" + e.toString());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectCarlvl() {
        List<Map<String, Object>> list = null;
        try{
            list = trafficInfoMapper.selectCarlvl();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectCarlvl:" + e.toString());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectCartype() {
        List<Map<String, Object>> list = null;
        try{
            list = trafficInfoMapper.selectCartype();
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectCartype:" + e.toString());
        }
        return list;
    }

    @Override
    public int countTrafficListByParam(Map<String, Object> param) {
        int i = 0;
        try{
            i = trafficInfoMapper.countTrafficListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".countTrafficListByParam:" + e.toString());
        }
        return i;
    }

    private static final Logger logger = LoggerFactory.getLogger(TrafficInfoServiceImpl.class);

	@Override
	public Integer getTrafficListCount(Map<String, Object> param) {
		 int i = 0;
	        try{
	            i = trafficInfoMapper.getTrafficListCount(param);
	        }catch(Exception e){
	            logger.error("login fail."+this.getClass()+".getTrafficListCount:" + e.toString());
	        }
	        return i;
	}

}
