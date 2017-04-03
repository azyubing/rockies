package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.PackageInfoMapper;
import com.rockies.ec.mapper.ProductInfoMapper;
import com.rockies.ec.model.PackageInfo;
import com.rockies.ec.services.IPackageInfoService;

@Service
public class PackageInfoServiceImpl implements IPackageInfoService {

    @Autowired private PackageInfoMapper packageInfoMapper;
    @Autowired private ProductInfoMapper productInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = packageInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(PackageInfo t) throws Exception {
        int i = 0;
        i = packageInfoMapper.deleteByPrimaryKey(t);    
        i = productInfoMapper.deleteByPrimaryKey(t); 
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(PackageInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insert(t);
        i = packageInfoMapper.insert(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(PackageInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.insertSelective(t); 
        i = packageInfoMapper.insertSelective(t);    
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(PackageInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKeySelective(t); 
        i = packageInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(PackageInfo t) throws Exception {
        int i = 0;
        i = productInfoMapper.updateByPrimaryKey(t);
        i = packageInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public PackageInfo selectByPrimaryKey(PackageInfo t) {
        PackageInfo info = null;
        try{
            info = packageInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<PackageInfo> selectAllListByParam(Map<String, Object> param) {
        List<PackageInfo> list = null;
        try{
            list = packageInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    @Override
    public PackageInfo selectPackageInfoByParam(Map<String, Object> param) {
        PackageInfo info = null;
        try{
            info = packageInfoMapper.selectPackageInfoByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectPackageInfoByParam:" + e.toString());
        }
        return info;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(PackageInfoServiceImpl.class);
    
}
