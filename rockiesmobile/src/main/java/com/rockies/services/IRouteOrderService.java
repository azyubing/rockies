package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.common.utils.PageResult;
import com.rockies.model.RouteOrder;
import com.rockies.vo.RouteOrderVO;

public interface IRouteOrderService {
	public String saveRouteOrder(RouteOrder routeOrder);
	
	/**
	 * 添加行程单以及子订单
	 * @param routeOrderVO
	 */
	public void saveRouteOrderVO(RouteOrderVO routeOrderVO);
	
	public void deleteRoute(String orderNo);
	
	/**
	 * 行程单列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRouteOrderList(Map<String, Object> map);
	
	/**
	 * 获取行程单
	 * @param orderNo
	 * @return
	 */
	public RouteOrder getRouteOrder(Map<String, Object> map);
	
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
	public int getRouteOrderCount(Map<String, Object> map);

	/**
	 * 我的订单
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getMyorder(Map<String, Object> param);
	/**
	 * 我的订单列表，分页
	 * @param param
	 * @return
	 */
	public PageResult<Map<String, Object>> getMyorderPage(PageResult<Map<String, Object>> pageResult);
	
	/**
	 * 我的订单数量
	 * @param param
	 * @return
	 */
	public int getMyorderCount(Map<String, Object> param);
	
	/**
     * 通过订单ID 查询行程单信息
     * @param param
     * @return
     */
	public RouteOrder getMyPayRouteOrderByOrderNo(Map<String, Object> param);
	/**
     * 通过订单ID 查询行程单关联的产品信息 
     * @param param
     * @return
     */
	public List<Map<String, Object>> getMyPayByOrderNo(Map<String, Object> param);
	
	/**
     * 通过订单ID 查询行程单关联的产品信息详情
     * @param param
     * @return
     */
	public List<Map<String, Object>> getMyPayDetailByOrderNo(Map<String, Object> param);
	/**
	 * 修改行程单中的联系人
	 * @param param
	 */
	public void updateRouteUser(Map<String, Object> param);
	
	/**
	 * 支付开始 存入支付渠道生成的支付单id（ping_orderid）
	 * @param param
	 */
	public int updateRouteStartPay(Map<String, Object> param);
	
	/**
	 * 支付成功 支付成功，更新订单状态和支付时间
	 * @param param
	 */
	public int updateRoutePaySuccess(Map<String, Object> param);
	
	/**
	 * 支付中
	 * @param param
	 */
	public int updateRoutePaying(Map<String, Object> param);
	
	/**
	 * 退款成功 更新订单状态已退款，和退款支付时间
	 * @param param
	 */
	public int updateRouteRefundSuccess(Map<String, Object> param);

	public RouteOrder getLatestOrderByUser(Map<String, Object> params);

	public void updateStatus(Map<String, Object> params);
	
}
