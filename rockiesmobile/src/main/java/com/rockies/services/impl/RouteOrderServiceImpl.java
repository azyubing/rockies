package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.common.utils.PageResult;
import com.rockies.mapper.BookCusInfoMapper;
import com.rockies.mapper.ProductOrderDetailMapper;
import com.rockies.mapper.ProductOrderMapper;
import com.rockies.mapper.RouteOrderMapper;
import com.rockies.model.BookCusInfo;
import com.rockies.model.ProductOrder;
import com.rockies.model.ProductOrderDetail;
import com.rockies.model.RouteOrder;
import com.rockies.services.IRouteOrderService;
import com.rockies.vo.ProductOrderVO;
import com.rockies.vo.RouteOrderVO;

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
	public void saveRouteOrderVO(RouteOrderVO routeOrderVO) {
		RouteOrder routeOrder = routeOrderVO.getRouteOrder();
		//保存行程单
		if(routeOrder.getOrderNo()==null||routeOrder.getOrderNo().isEmpty()){
			routeOrderMapper.saveRouteOrder(routeOrder);
		}else{
			routeOrderMapper.saveRouteOrderHasOrderNo(routeOrder);
		}
//		String orderNo = routeOrder.getOrderNo();//行程单编号
//		
//		//保存产品订单
//		List<ProductOrderVO> ProductOrderVOList = routeOrderVO.getProductOrderVOList();
//		for(ProductOrderVO productOrderVO :ProductOrderVOList){
//			long runingNo = System.currentTimeMillis();
//			
//			ProductOrder productOrder = productOrderVO.getProductOrder();
//			productOrder.setOrderNo(orderNo);
//			productOrder.setRuningNo(runingNo);
//			productOrderMapper.saveProductOrder(productOrder);
//			
//			//保存产品订单详情
//			List<ProductOrderDetail> ProductOrderDetailList = productOrderVO.getProductOrderDetailList();
//			for(ProductOrderDetail productOrderDetail:ProductOrderDetailList){
//				productOrderDetail.setOrderNo(orderNo);
//				productOrderDetail.setRuningNo(runingNo);
//				productOrderDetailMapper.saveProductOrderDetail(productOrderDetail);
//			}
//			//保存预定人信息
//			List<BookCusInfo> bookCusInfoList = productOrderVO.getBookCusInfoList();
//			if(bookCusInfoList!=null&&!bookCusInfoList.isEmpty()){
//				for(BookCusInfo bookCusInfo:bookCusInfoList){
//					bookCusInfo.setOrderNo(orderNo);
//					bookCusInfoMapper.saveBookCusInfo(bookCusInfo);
//				}
//			}
//			
//		}
//		
	}

	@Override
	public List<Map<String,Object>> getRouteOrderList(Map<String,Object> map) {
		return routeOrderMapper.getRouteOrderList(map);
	}

	@Override
	public RouteOrder getRouteOrder(Map<String,Object> map) {
		return routeOrderMapper.getRouteOrder(map);
	}

	@Override
	public long getRouteStatusCount(String status) {
		return routeOrderMapper.getRouteStatusCount(status);
	}

	@Override
	public int getRouteOrderCount(Map<String,Object> map) {
		return routeOrderMapper.getRouteOrderCount(map);
	}

	@Override
	public List<Map<String, Object>> getMyorder(Map<String,Object> param) {
		return routeOrderMapper.getMyorder(param);
	}

	@Override
	public List<Map<String, Object>> getMyPayByOrderNo(Map<String, Object> param) {
		return routeOrderMapper.getMyPayByOrderNo(param);
	}

	@Override
	public List<Map<String, Object>> getMyPayDetailByOrderNo(
			Map<String, Object> param) {
		return routeOrderMapper.getMyPayDetailByOrderNo(param);
	}

	@Override
	public PageResult<Map<String, Object>> getMyorderPage(PageResult<Map<String, Object>> pageResult) {
		Map<String, Object> param = pageResult.getT();
		int count = this.getMyorderCount(param);
		param.put("offset", pageResult.getOffset());
		param.put("limit", pageResult.getPageSize());
		List<Map<String, Object>> list = this.getMyorder(param);
		pageResult.setTotal(count);
		pageResult.setPageResultList(list);
		return pageResult;
	}

	@Override
	public int getMyorderCount(Map<String, Object> param) {
		return routeOrderMapper.getMyorderCount(param);
	}

    @Override
    public RouteOrder getMyPayRouteOrderByOrderNo(Map<String, Object> param) {
        return routeOrderMapper.getMyPayRouteOrderByOrderNo(param);
    }

	@Override
	public void deleteRoute(String orderNo) {
		productOrderDetailMapper.deleteProductOrderDetail(orderNo);
		productOrderMapper.deleteProductOrder(orderNo);
		routeOrderMapper.deleteRouteOrder(orderNo);
	}

	@Override
	public void updateRouteUser(Map<String, Object> param) {
		routeOrderMapper.updateRouteUser(param);
	}
	
	/**
	 * 支付开始 存入支付渠道生成的支付单id（ping_orderid）
	 * @param param
	 */
	public int updateRouteStartPay(Map<String,Object> param){
		return routeOrderMapper.updateRouteStartPay(param);
	}
	
	/**
	 * 支付成功 支付成功，更新订单状态和支付时间
	 * @param param
	 */
	public int updateRoutePaySuccess(Map<String,Object> param){
		routeOrderMapper.updateProductSaleCnt(param);
		return routeOrderMapper.updateRoutePaySuccess(param);
	}
	
	/**
	 * 支付中
	 * @param param
	 */
	public int updateRoutePaying(Map<String,Object> param){
		return routeOrderMapper.updateRoutePaying(param);
	}
	
	/**
	 * 退款成功 更新订单状态已退款，和退款支付时间
	 * @param param
	 */
	public int updateRouteRefundSuccess(Map<String,Object> param){
		return routeOrderMapper.updateRouteRefundSuccess(param);
	}

	@Override
	public RouteOrder getLatestOrderByUser(Map<String, Object> params) {
		return routeOrderMapper.getLatestOrderByUser(params);
	}

	@Override
	public void updateStatus(Map<String, Object> params) {
		routeOrderMapper.updateStatus(params);	
	}

}
