package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.BookCusInfoMapper;
import com.rockies.ec.mapper.ProductOrderDetailMapper;
import com.rockies.ec.mapper.ProductOrderMapper;
import com.rockies.ec.mapper.RouteOrderMapper;
import com.rockies.ec.model.BookCusInfo;
import com.rockies.ec.model.ProductOrder;
import com.rockies.ec.model.ProductOrderDetail;
import com.rockies.ec.model.RouteOrder;
import com.rockies.ec.services.IRouteOrderService;
import com.rockies.ec.vo.ProductOrderVO;
import com.rockies.ec.vo.RouteOrderVO;

@Service
public class RouteOrderServiceImpl implements IRouteOrderService {

	@Autowired
	private RouteOrderMapper routeOrderMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private ProductOrderDetailMapper productOrderDetailMapper;
	@Autowired
	private BookCusInfoMapper bookCusInfoMapper;
	
	@Override
	public String saveRouteOrder(RouteOrder routeOrder) {
		routeOrderMapper.saveRouteOrder(routeOrder);
		return routeOrder.getCusNo();
	}

	@Override
	public List<Map<String,Object>> getRouteOrderList(Map map) {
		return routeOrderMapper.getRouteOrderList(map);
	}

	@Override
	public RouteOrder getRouteOrder(Map map) {
		return routeOrderMapper.getRouteOrder(map);
	}

	@Override
	public long getRouteStatusCount(String status) {
		return routeOrderMapper.getRouteStatusCount(status);
	}

	@Override
	public int getRouteOrderCount(Map map) {
		return routeOrderMapper.getRouteOrderCount(map);
	}

	@Override
	public void updateRouteStatus(Map map) {
		routeOrderMapper.updateRouteStatus(map);
	}

	@Override
	public void updateRouteAmount(Map map) {
		routeOrderMapper.updateRouteAmount(map);
	}

}
