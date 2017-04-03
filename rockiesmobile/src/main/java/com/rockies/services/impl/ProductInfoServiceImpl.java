package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.ProductInfoMapper;
import com.rockies.model.ProductInfo;
import com.rockies.services.IProductInfoService;

@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    @Autowired ProductInfoMapper productInfoMapper;
    
    @Override
    public List<Map<String, Object>> selectProductInfoListByParam(Map<String, Object> param) {
        List<Map<String, Object>> list = null;
        try{
            list = productInfoMapper.selectProductInfoListByParam(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectRoomTypeByParam:" + e.toString());
        }
        return list;
    }

    private static final Logger logger = LoggerFactory.getLogger(RoomTypeInfoServiceImpl.class);

    @Override
    public int getCount(Map<String, Object> param) {
        int i = 0;
        try{
            i = productInfoMapper.getCount(param);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".getCount:" + e.toString());
        }
        return i;
    }

    @Override
    public boolean deleteByPrimaryKey(ProductInfo t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insert(ProductInfo t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertSelective(ProductInfo t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateByPrimaryKeySelective(ProductInfo t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(ProductInfo t) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ProductInfo selectByPrimaryKey(ProductInfo t) {
        ProductInfo info = null;
        try{
            info = productInfoMapper.selectByPrimaryKey(t);
        }catch(Exception e){
            logger.error("login fail."+this.getClass()+".selectByPrimaryKey:" + e.toString());
        }
        return info;
    }

    @Override
    public List<ProductInfo> selectAllListByParam(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public int getOutCount(Map<String, Object> param) {
		
		return productInfoMapper.getOutCount(param);
	}

	@Override
	public List<Map<String, Object>> queryProductsByParam(Map<String, Object> param) {
		return productInfoMapper.queryProductsByParam(param);
	}
    
}
