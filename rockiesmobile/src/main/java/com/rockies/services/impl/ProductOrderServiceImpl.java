package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.ProductOrderMapper;
import com.rockies.model.ProductOrder;
import com.rockies.services.IProductOrderService;

@Service
public class ProductOrderServiceImpl implements IProductOrderService {

	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Override
	public void saveProductOrder(ProductOrder productOrder) {
		productOrderMapper.saveProductOrder(productOrder);
	}
	
	@Override
	public List<Map<String, Object>> getRouteOrderInfo(String orderNo) {
		return productOrderMapper.getRouteOrderInfo(orderNo);
	}
	
	@Override
	public List<Map<String, Object>> getProductOrderDetail(Map map) {
		return productOrderMapper.getProductOrderDetail(map);
	}

	@Override
	public List<Map<String, Object>> getOrderCusInfo(String orderNo) {
		return productOrderMapper.getOrderCusInfo(orderNo);
	}

	@Override
	public List<Map<String, Object>> getProductOrderBySupplierNo(Map map) {
		return productOrderMapper.getProductOrderBySupplierNo(map);
	}

	@Override
	public int getCountBySupplierNo(Map map) {
		return productOrderMapper.getCountBySupplierNo(map);
	}

	@Override
	public List<Map<String, Object>> getSupplierOrderInfo(Map map) {
		return productOrderMapper.getSupplierOrderInfo(map);
	}

	@Override
	public void updateProductOrder(ProductOrder productOrder) {
		productOrderMapper.updateProductOrder(productOrder);
		
	}

}
