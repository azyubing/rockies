package com.rockies.mobile.controller;

import java.io.IOException;
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
import com.rockies.services.ICityService;

@Controller
public class DestinationController {
	private static final Logger logger = LoggerFactory.getLogger(DestinationController.class);
	@Autowired
	private ICityService cityService;

	/**
	 * get hot destinations/cities
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/mobile/hotDestinations", method = RequestMethod.GET)
	public void getHotDesinations(HttpServletResponse response, HttpServletRequest request, int page, int pageSize) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("isHot", "1");
			params.put("parentId", "1");
			//if (null == page )
			params.put("page", DBUtils.page(page, pageSize));
			params.put("pageSize", DBUtils.pageSize(page, pageSize));
			List<City> cities = cityService.getCityListByIsHot(params);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, cities, null));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.SystemError));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e1);
			}
		}
	}

	@RequestMapping(value = "/mobile/destinations", method = RequestMethod.GET)
	public void getDestinations(HttpServletResponse response, HttpServletRequest request, int page, int pageSize) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("page", DBUtils.page(page, pageSize));
			params.put("pageSize", DBUtils.pageSize(page, pageSize));
			List<City> cityList = cityService.getCityListAll();
			Map<String, Object> reslutMap = new HashMap<>();
			reslutMap.put("list", cityList);
			reslutMap.put("total", cityList.size());
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, reslutMap, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.SystemError));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e1);
			}
		}
	}
	
	@RequestMapping(value = "/mobile/cityPic", method = RequestMethod.POST)
	public void updateCityPic(HttpServletResponse response, HttpServletRequest request, int id, String icon) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			params.put("img", icon);
			cityService.updateImg(params);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, "城市图片更新成功！"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
