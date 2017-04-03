package com.rockies.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.ProductOrderDetailMapper;
import com.rockies.model.ProductOrderDetail;
import com.rockies.services.IProductOrderDetailService;

@Service
public class ProductOrderDetailServiceImpl implements IProductOrderDetailService {

	@Autowired
	private ProductOrderDetailMapper productOrderDetailMapper;
	
	@Override
	public void saveProductOrderDetail(ProductOrderDetail productOrderDetail) {
		productOrderDetailMapper.saveProductOrderDetail(productOrderDetail);
	}

}
