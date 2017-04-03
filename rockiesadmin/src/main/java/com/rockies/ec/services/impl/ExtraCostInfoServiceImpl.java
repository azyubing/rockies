package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ExtraCostInfoMapper;
import com.rockies.ec.model.ExtraCostInfo;
import com.rockies.ec.services.IExtraCostInfoService;

@Service
public class ExtraCostInfoServiceImpl implements IExtraCostInfoService {

    @Autowired private ExtraCostInfoMapper extraCostInfoMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = extraCostInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(ExtraCostInfo t) throws Exception {
        int i = 0;
        i = extraCostInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".deleteByPrimaryKey:"+i);
        }
    }

    @Override
    public boolean insert(ExtraCostInfo t) throws Exception {
        int i = 0;
        i = extraCostInfoMapper.insert(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".insert:"+i);
        }
    }

    @Override
    public boolean insertSelective(ExtraCostInfo t) throws Exception {
        int i = 0;
        i = extraCostInfoMapper.insertSelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".insertSelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(ExtraCostInfo t) throws Exception {
        int i = 0;
        i = extraCostInfoMapper.updateByPrimaryKeySelective(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".updateByPrimaryKeySelective:"+i);
        }
    }

    @Override
    public boolean updateByPrimaryKey(ExtraCostInfo t) throws Exception {
        int i = 0;
        i = extraCostInfoMapper.updateByPrimaryKey(t);
        if(i == 1){
            return true;
        }else{
            throw new Exception(this.getClass()+".updateByPrimaryKey:"+i);
        }
    }

    @Override
    public ExtraCostInfo selectByPrimaryKey(ExtraCostInfo t) {
        ExtraCostInfo info = null;
        try{
            info = extraCostInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<ExtraCostInfo> selectAllListByParam(Map<String, Object> param) {
        List<ExtraCostInfo> list = null;
        try{
            list = extraCostInfoMapper.selectAllListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectAllListByParam:" + e.toString());
        }
        return list;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ExtraCostInfoServiceImpl.class);

}
