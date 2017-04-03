package com.rockies.ec.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.ec.model.RouteOrder;

public interface RouteOrderMapper {
	/**
	 * 保存行程单
	 * @param routeOrder
	 */
	public void saveRouteOrder(RouteOrder routeOrder);
	/**
	 * 行程单列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRouteOrderList(Map map);
	
	/**
	 * 获取行程单
	 * @param orderNo
	 * @return
	 */
	public RouteOrder getRouteOrder(Map map);
	
	/**
	 * 各种状态下的订单数量
	 * @param status
	 * @return
	 */
	public long getRouteStatusCount(String status);
	
	/**
	 * 获取行程单count
	 * @param map
	 * @return
	 */
	public int getRouteOrderCount(Map map);
	/**
	 * 修改订单状态
	 * @param map
	 */
	public void updateRouteStatus(Map map);
	/**
	 * 修改订单金额
	 * @param map
	 */
	public void updateRouteAmount(Map map);
	
}
