package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ProductTemplateMapper;
import com.rockies.ec.model.ProductTemplate;
import com.rockies.ec.services.IProductTemplateService;

@Service
public class ProductTemplateServiceImpl implements IProductTemplateService {

    @Autowired private ProductTemplateMapper productTemplateMapper;
    
    @Override
    public int getCount(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean deleteByPrimaryKey(ProductTemplate t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insert(ProductTemplate t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertSelective(ProductTemplate t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateByPrimaryKeySelective(ProductTemplate t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(ProductTemplate t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ProductTemplate selectByPrimaryKey(ProductTemplate t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductTemplate> selectAllListByParam(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> selectByIdAndPtype(Map<String, Object> param) {
        
        Map<String, Object> map = null;
        try{
            map = productTemplateMapper.selectByPtypeAndCode(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByIdAndPtype:" + e.toString());
        }
        return map;
    }

    private static final Logger logger = LoggerFactory.getLogger(RouteDetailInfoServiceImpl.class);
}
