package com.rockies.ec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ProductOrderDetailMapper;
import com.rockies.ec.model.ProductOrderDetail;
import com.rockies.ec.services.IProductOrderDetailService;

@Service
public class ProductOrderDetailServiceImpl implements IProductOrderDetailService {

	@Autowired
	private ProductOrderDetailMapper productOrderDetailMapper;
	
	@Override
	public void saveProductOrderDetail(ProductOrderDetail productOrderDetail) {
		productOrderDetailMapper.saveProductOrderDetail(productOrderDetail);
	}

}
