package com.rockies.mobile.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rockies.mobile.constants.Constants;
import com.rockies.mobile.utils.DBUtils;
import com.rockies.mobile.utils.Utils;
import com.rockies.model.City;
import com.rockies.model.Option;
import com.rockies.model.User;
import com.rockies.services.ICityService;
import com.rockies.services.IRouteOrderService;

@Controller
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private IRouteOrderService routeOrderService;
	@Autowired
	private ICityService cityService;

	/**
	 * get myorder search options
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/mobile/orderSearchOptions", method = RequestMethod.GET)
	public void getOrderSearchOptions(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> returnObj = new HashMap<String, Object>();
			// 目的地
			List<Option> destOptions = new ArrayList<Option>();
			destOptions.add(new Option(Constants.AllDestinations, "全部"));
			Map<String, Object> params = new HashMap<>();
			params.put("isHot", "1");
			params.put("parentId", "1");
			List<City> cities = cityService.getCityListByIsHot(params);
			for (City city : cities) {
				destOptions.add(new Option(city.getCityCode(), city.getCityName()));
			}
			returnObj.put("destinationOptions", destOptions);
			// by 单项服务，目前没有专门的表保存这些数据，所以这里是写死的
			List<Option> serviceOptions = new ArrayList<Option>();
			serviceOptions.add(new Option(Constants.AllServices, "全部"));
			serviceOptions.add(new Option("0", "套餐"));
			serviceOptions.add(new Option("1", "酒店"));
			serviceOptions.add(new Option("2", "娱乐"));
			serviceOptions.add(new Option("3", "别墅"));
			serviceOptions.add(new Option("4", "交通"));
			returnObj.put("servicesOptions", serviceOptions);
			// by time
			List<Option> orderbyOptions = new ArrayList<Option>();
			orderbyOptions.add(new Option(Constants.AllDate, "全部"));
			orderbyOptions.add(new Option(Constants.OrderDate, "下单时间"));
			orderbyOptions.add(new Option(Constants.SetoutDate, "出发时间"));
			returnObj.put("orderbyOptions", orderbyOptions);
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, returnObj, null));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * status为空是获取所有订单 status=1 待付款订单 status=2待处理订单 status=3待付尾款 status=4待出行订单
	 * status=5已完成订单 status=6出行中 status=8支付中 status=9已退款
	 * 
	 * @param response
	 * @param request
	 * @param status
	 * @param ptype
	 * @param destination
	 * @param orderby
	 * @param page
	 * @param pageSize
	 */
	@RequestMapping(value = "/mobile/myorders", method = RequestMethod.GET)
	public void getMyOrders(HttpServletResponse response, HttpServletRequest request, String status, String ptype,
			String destination, String orderby, int page, int pageSize) {
		try {
			Map<String, Object> param = new HashMap<>();
			if (null != status && !status.equals("")) {
				param.put("status", status);
			} // else, it will return all the orders from DB
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
			}
			param.put("cusNo", user.getId());
			if (null != ptype && !"".equals(ptype)) {

			}

			int count = routeOrderService.getMyorderCount(param);
			param.put("offset", DBUtils.page(page, pageSize));
			param.put("limit", DBUtils.pageSize(page, pageSize));
			List<Map<String, Object>> orders = routeOrderService.getMyorder(param);
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("total", count);
			ret.put("list", orders);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, ret, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/delmyorder", method = RequestMethod.POST)
	public void deleteOrder(HttpServletResponse response, HttpServletRequest request, String orderNo) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.ErrorUser));
				return;
			}
			routeOrderService.deleteRoute(orderNo);
			// request.getSession().setAttribute("routeJson",
			// routeOrder.getRouteJson());
			// request.getSession().setAttribute("orderNo",
			// routeOrder.getOrderNo());
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, "订单 " + orderNo + "被成功删除！"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
