package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.PackagePriceInfoMapper;
import com.rockies.model.PackagePriceInfo;
import com.rockies.services.IPackagePriceInfoService;

@Service
public class PackagePriceInfoServiceImpl implements IPackagePriceInfoService {

    @Autowired private PackagePriceInfoMapper packagePriceInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = packagePriceInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(PackagePriceInfo t) throws Exception {
        int i = 0;
        i = packagePriceInfoMapper.deleteByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(PackagePriceInfo t) throws Exception {
        int i = 0;
        i = packagePriceInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(PackagePriceInfo t) throws Exception {
        int i = 0;
        i = packagePriceInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(PackagePriceInfo t) throws Exception {
        int i = 0;
        i = packagePriceInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(PackagePriceInfo t) throws Exception {
        int i = 0;
        i = packagePriceInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception("login fail."+this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public PackagePriceInfo selectByPrimaryKey(PackagePriceInfo t) {
        PackagePriceInfo info = null;
        try{
            info = packagePriceInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<PackagePriceInfo> selectAllListByParam(Map<String, Object> param) {
        List<PackagePriceInfo> list = null;
        try{
            list = packagePriceInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(PackagePriceInfoServiceImpl.class);

	@Override
	public PackagePriceInfo selectPackagePriceInfoById(int id) {
		return packagePriceInfoMapper.selectPackagePriceInfoById(id);
	}

	@Override
	public List<PackagePriceInfo> getPackagePriceCal(Map<String, Object> param) {
		return packagePriceInfoMapper.getPackagePriceCal(param);
	}
}
