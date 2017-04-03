package com.rockies.mobile.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.rockies.mobile.model.PriceItem;
import com.rockies.mobile.utils.Utils;
import com.rockies.model.EntPriceInfo;
import com.rockies.model.PackagePriceInfo;
import com.rockies.model.RoomPriceInfo;
import com.rockies.model.TrafficCharteredPriceInfo;
import com.rockies.model.VillaDetailPriceInfo;
import com.rockies.services.IEntPriceInfoService;
import com.rockies.services.IPackagePriceInfoService;
import com.rockies.services.IRoomPriceInfoService;
import com.rockies.services.IRoomTypeInfoService;
import com.rockies.services.ITrafficCharteredPriceInfoService;
import com.rockies.services.IVillaDetailPriceInfoService;

@Controller
public class PriceController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	@Autowired
	private IRoomPriceInfoService roomPriceInfoService;
	@Autowired
	private IPackagePriceInfoService packagePriceInfoService;
	@Autowired
	private IVillaDetailPriceInfoService villaDetailPriceInfoService;
	@Autowired
	private IEntPriceInfoService entPriceInfoService;
	@Autowired
	private ITrafficCharteredPriceInfoService trafficCharteredPriceInfoService;
	@Autowired
	private IRoomTypeInfoService roomTypeInfoService;

	/**
	 * 酒店价格日历
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param date
	 */
	@RequestMapping(value = "/mobile/hotelPriceCalendar", method = RequestMethod.GET)
	public void getHotelPriceCalendar(HttpServletRequest request, HttpServletResponse response, String pid,
			String date) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("beginDate", Utils.getFirstDay(date));
			param.put("endDate", Utils.getLastDay(date));
			// 酒店价格日历
			List<PackagePriceInfo> prices = roomPriceInfoService.gethotelPriceCal(param);
			List ret = getPriceList(date, prices);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, ret, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * get all prices for the price canlendar
	 * @param date: 日期，查询date日期当月的所有的价格
	 * @param prices： 价格数组，如果存在价格就将价格填写上，如果不存在价格就以{"status":"0"}填充
	 * @return
	 */
	private List getPriceList(String date, List prices) {
		Map pricesMap = orderPrice(prices);
		Date beginDate = Utils.getFirstDayOfMonth(date);
		Date endDate = Utils.getLastDayOfMonth(date);
		List ret = new ArrayList();
		Date d = Utils.getDay(beginDate, 0);
		for (int i = 0; d.getTime() <= endDate.getTime(); i++) {
			d = Utils.getDay(beginDate, i);
			String tmpDate = Utils.getDay(d, Constants.StardardFmt);
			if (pricesMap.containsKey(tmpDate)) {
				ret.add(pricesMap.get(tmpDate));
			} else {
				ret.add(new PriceItem("0"));
			}
		}
		return ret;
	}

	private Map orderPrice(List prices) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.StardardFmt);
		Map pricesMap = new HashMap();
		for (Object p : prices) {
			Map price = (Map) p;
			String d = sdf.format((Date) price.get("start"));
			pricesMap.put(d, price);
		}
		return pricesMap;
	}

	@RequestMapping(value = "/mobile/hotelRoomOptions", method = RequestMethod.GET)
	public void getHotelRoomOptions(HttpServletRequest request, HttpServletResponse response, String pid) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			List<Map<String, Object>> roomTypes = roomTypeInfoService.selectRoomPriceByParam(param);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, roomTypes, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/hotelRoomOptionPrice", method = RequestMethod.GET)
	public void getHotelRoomOptionPrice(HttpServletRequest request, HttpServletResponse response, String pid,
			String roomTypeId, String date) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("roomType", roomTypeId);
			if (null != date && !"".equals(date)) {
				param.put("date", date);
			}
			RoomPriceInfo price = roomPriceInfoService.queryPrice(param);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, price, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 别墅价格日历
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param date
	 */
	@RequestMapping(value = "/mobile/villaPriceCalendar", method = RequestMethod.GET)
	public void getVillaPriceCalendar(HttpServletRequest request, HttpServletResponse response, String pid,
			String date) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("beginDate", Utils.getFirstDay(date));
			param.put("endDate", Utils.getLastDay(date));
			// 别墅价格日历
			List<PackagePriceInfo> prices = villaDetailPriceInfoService.getVillaPriceCal(param);
			List ret = getPriceList(date, prices);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, ret, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/villaRoomOptions", method = RequestMethod.GET)
	public void getVillaRoomOptions(HttpServletRequest request, HttpServletResponse response, String pid, String date) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("date", date);
			List<VillaDetailPriceInfo> options = villaDetailPriceInfoService.selectAllListByParam(param);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, options, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 套餐价格日历
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param date
	 */
	@RequestMapping(value = "/mobile/packagePriceCalendar", method = RequestMethod.GET)
	public void getPackagePriceCalendar(HttpServletRequest request, HttpServletResponse response, String pid,
			String date) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("beginDate", Utils.getFirstDay(date));
			param.put("endDate", Utils.getLastDay(date));
			// 套餐价格项
			List<PackagePriceInfo> prices = packagePriceInfoService.getPackagePriceCal(param);
			List ret = getPriceList(date, prices);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, ret, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/packageOptions", method = RequestMethod.GET)
	public void getPackageOptions(HttpServletRequest request, HttpServletResponse response, String pid, String date) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("date", date);
			List<PackagePriceInfo> options = packagePriceInfoService.selectAllListByParam(param);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, options, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 娱乐价格日历
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param date
	 */
	@RequestMapping(value = "/mobile/entertainmentPriceCalendar", method = RequestMethod.GET)
	public void getEntertainmentPriceCalendar(HttpServletRequest request, HttpServletResponse response, String pid,
			String date) {
		
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("beginDate", Utils.getFirstDay(date));
			param.put("endDate", Utils.getLastDay(date));
			// 娱乐价格日历
			List<PackagePriceInfo> prices = entPriceInfoService.getEntPriceCal(param);
			List ret = getPriceList(date, prices);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, ret, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/entertainmentOptions", method = RequestMethod.GET)
	public void getEntertainmentOptions(HttpServletRequest request, HttpServletResponse response, String pid,
			String date) {
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			param.put("date", date);
			List<EntPriceInfo> options = entPriceInfoService.selectAllListByParam(param);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, options, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/villaPriceDetail", method = RequestMethod.GET)
	public void getVillaPriceDetail(HttpServletRequest request, HttpServletResponse response, String pid, String date) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			Map<String, Object> param = new HashMap<>(3);
			param.put("pid", pid);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// param.put("starttime", sdf.parse(date));
			List<VillaDetailPriceInfo> villaDetailPriceInfoList = villaDetailPriceInfoService
					.selectAllListByParam(param);
			// List<VillaDetailPriceInfo> priceItems =
			// villaDetailPriceInfoService.selectAllListByParam(param);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, villaDetailPriceInfoList, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取租车的价格信息
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param time
	 *            ： 格式如09:00
	 */
	@RequestMapping(value = "/mobile/carPriceDetail", method = RequestMethod.GET)
	public void getCarPriceDetail(HttpServletRequest request, HttpServletResponse response, String pid, String time) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", pid);
			params.put("time", time);
			List<TrafficCharteredPriceInfo> prices = trafficCharteredPriceInfoService.selectPriceInfo(params);
			response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, prices, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
