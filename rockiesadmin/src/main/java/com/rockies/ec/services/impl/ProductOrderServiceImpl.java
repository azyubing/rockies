package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ProductOrderMapper;
import com.rockies.ec.model.ProductOrder;
import com.rockies.ec.services.IProductOrderService;

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
	public List<Map<String, Object>> getOrderCusInfo(Map map) {
		return productOrderMapper.getOrderCusInfo(map);
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

	@Override
	public int getConfirmCount(String orderNo) {
		return productOrderMapper.getConfirmCount(orderNo);
	}

}
