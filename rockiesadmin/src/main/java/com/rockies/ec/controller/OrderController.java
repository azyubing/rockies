package com.rockies.ec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.common.utils.StatusUtil;
import com.rockies.ec.common.utils.mail.MailSender;
import com.rockies.ec.model.BookCusInfo;
import com.rockies.ec.model.City;
import com.rockies.ec.model.ProductOrder;
import com.rockies.ec.model.RouteOrder;
import com.rockies.ec.model.User;
import com.rockies.ec.services.IBookCusInfoService;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IProductOrderService;
import com.rockies.ec.services.IRouteOrderService;
import com.rockies.ec.services.IUserService;
import com.rockies.ec.vo.LoginInfoVO;


@Controller
public class OrderController extends BaseController{

	@Autowired
	private IRouteOrderService routeOrderService;
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IBookCusInfoService bookCusInfoService;
	@Autowired 
	private ICityService cityService;
	@Autowired private IUserService userService;
	
    /**
     * 订单管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/orderAdmin")
    public ModelAndView orderAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {
        	String status = request.getParameter("status");
        	request.setAttribute("status", status);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/order/orderAdmin","menu",menu);
    }
    
    /**
     * 订单列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/order/orderAdminSearchList")
    public void orderAdminSearchList(HttpServletResponse response, HttpServletRequest request,
    		Integer offset, Integer limit) {
    	
    	try {
    		String orderNo = request.getParameter("orderNo");
    		String status = request.getParameter("status");
    		Map map = new HashMap();
    		map.put("offset", offset);
    		map.put("limit", limit);
    		map.put("status", status);
    		map.put("orderNo", orderNo);
    		
    		List<Map<String,Object>> rows = routeOrderService.getRouteOrderList(map);
    		
    		Map<String, Object> reslutMap = new HashMap<>();
    		reslutMap.put("rows", rows);
    		reslutMap.put("total", routeOrderService.getRouteOrderCount(map));
    		response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
    		
    	} catch (Exception e) {
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.info("login fail. " + e.toString());
    	}
    }
    
    /**
     * 修改订单金额
     * @param response
     * @param request
     * @param productOrder
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/updateRouteAmount")
    public void updateRouteAmount(HttpServletResponse response, HttpServletRequest request,String orderNo,String amount) {
    	try {
    		Map map = new HashMap();
    		map.put("orderNo", orderNo);
    		map.put("amount", amount);
    		routeOrderService.updateRouteAmount(map);
    		response.getWriter().print("success");
    	} catch (Exception e) {
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.info("updateRouteAmount fail. " + e.toString());
    	}
    }
    
    
    /**
     * 修改产品订单
     * @param response
     * @param request
     * @param productOrder
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/updateProductOrder")
    public void updateProductOrder(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("productOrder") ProductOrder productOrder) {
    	
    	try {
    		productOrder.setUpdt(new Date());
    		productOrder.setUpuser(getAdminId(request)+"");
    		productOrderService.updateProductOrder(productOrder);
    		
    		int count = productOrderService.getConfirmCount(productOrder.getOrderNo());
    		
    		if(count==0){
    			Map upmap = new HashMap();
    			upmap.put("orderNo", productOrder.getOrderNo());
    			upmap.put("status", StatusUtil.ROUTE_STATUS_GO);
    			//修改订单状态
    			routeOrderService.updateRouteStatus(upmap);
    			//发邮件
    			Map map = new HashMap();
        		map.put("orderNo", productOrder.getOrderNo());
        		
        		RouteOrder ro = routeOrderService.getRouteOrder(map);
    			String message=productOrder.getOrderNo();
    			MailSender.send_confirm_ok_mail(message, ro.getRouteEmail());
    		}
    		
    		response.getWriter().print("success");
    		
    	} catch (Exception e) {
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.info("login fail. " + e.toString());
    	}
    }
    
    /**
     * 修改联系人
     * @param response
     * @param request
     * @param bookCusInfo
     */
	@RequestMapping(value = "/order/updateCusOrder")
    public void updateCusOrder(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("bookCusInfo") BookCusInfo bookCusInfo) {
        try {
        	bookCusInfo.setUpdt(new Date());
        	bookCusInfo.setUpuser(getAdminId(request)+"");
        	bookCusInfoService.updateBookCusInfo(bookCusInfo);
            response.getWriter().print("success");

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
    }
    
    /**
     * 订单详情
     * @param response
     * @param request
     * @param orderId
     * @param orderNo
     * @param store
     * @param menu
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/detailOrderInfo")
    public ModelAndView detailOrderInfo(HttpServletResponse response, HttpServletRequest request,String orderNo,String status,String menu) {
        ModelAndView retView = new ModelAndView("/order/detailOrderInfo");
        try {
        	Map map = new HashMap();
			map.put("orderNo", orderNo);
        	map.put("status", status);
        	RouteOrder routeOrder = routeOrderService.getRouteOrder(map);
        	if(routeOrder!=null){
        		int cusNo = Integer.parseInt(routeOrder.getCusNo());
        		User user = userService.getUserById(cusNo);
        		City country = cityService.getCity(user.getCountryId());
            	City province = cityService.getCity(user.getProvinceId());
            	City city = cityService.getCity(user.getCityId());
            	
            	request.setAttribute("country", country);
            	request.setAttribute("province", province);
            	request.setAttribute("city", city);
        		List<Map<String,Object>> rows = productOrderService.getRouteOrderInfo(orderNo);
        		
        		
        		int babyCount = bookCusInfoService.getBabyCount(orderNo);
        		if(babyCount>0){
        			retView.addObject("babyCount", babyCount);
        			retView.addObject("babyList", bookCusInfoService.getBaby(orderNo));
        		}
                int childCount = bookCusInfoService.getChildCount(orderNo);
                if(childCount>0){
                	retView.addObject("childCount", childCount);
        			retView.addObject("childList", bookCusInfoService.getChild(orderNo));
        		}
                int adultCount  =bookCusInfoService.getAdultCount(orderNo);
                if(adultCount>0){
                	retView.addObject("adultCount", adultCount);
        			retView.addObject("adultList", bookCusInfoService.getAdult(orderNo));
        		}
        		
        		retView.addObject("routeOrder", routeOrder);
        		retView.addObject("user", user);
        		retView.addObject("rows", rows);
        		retView.addObject("status", status);
        	}
        	
            retView.addObject("menu", menu);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateOrderInfo", e);
        }
        return retView;
    }
    
    /**
     * 供应商订单管理
     */
    @RequestMapping(value = "/order/suppliersOrderAdmin")
    public ModelAndView suppliersOrderAdmin(HttpServletResponse response, HttpServletRequest request,
            String msg,String menu) {
        ModelAndView mav = new ModelAndView("/order/suppliersOrderAdmin","menu",menu);
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("suppliersOrderAdmin fail."+this.getClass()+".suppliersOrderAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 供应商订单列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/suppliersOrderSearchList")
	public void suppliersOrderSearchList(HttpServletResponse response,
			HttpServletRequest request, Integer offset, Integer limit) {
		try {
			String orderNo = request.getParameter("orderNo");
			String confirmStatus = request.getParameter("confirmStatus");
			String confirmTime = request.getParameter("confirmTime");
			String surplusTime = request.getParameter("surplusTime");
			LoginInfoVO loginInfoVO = (LoginInfoVO) request.getSession().getAttribute("user_loginInfoVO");
			Map map = new HashMap();
    		map.put("offset", offset);
    		map.put("limit", limit);
    		map.put("orderNo", orderNo);
    		map.put("confirmStatus", confirmStatus);
    		map.put("confirmTime", confirmTime);
    		map.put("surplusTime", surplusTime);
    		map.put("supplierNo", loginInfoVO.getLoginInfo().getSupplierId());
			
			List<Map<String, Object>> rows = productOrderService.getProductOrderBySupplierNo(map);
			Map<String, Object> reslutMap = new HashMap<>();
			reslutMap.put("rows", rows);
			reslutMap.put("total", productOrderService.getCountBySupplierNo(map));
			response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

		} catch (Exception e) {
			if (null != e.getMessage()) {
				System.out.println(e.toString());
			}
			logger.info("order/suppliersOrderSearchList fail. " + e.toString());
		}
	}
    
    /**
     * 供应商订单详情
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/detailSuppliersOrderInfo")
    public ModelAndView detailSuppliersOrderInfo(HttpServletResponse response, HttpServletRequest request,
            String orderNo,String pid,String menu,String runingNo) {
        ModelAndView mav = new ModelAndView("/order/detailSuppliersOrderInfo","menu",menu);
        try {
        	LoginInfoVO loginInfoVO = (LoginInfoVO) request.getSession().getAttribute("user_loginInfoVO");
        	Map map = new HashMap();
        	map.put("pid", pid);
        	map.put("orderNo", orderNo);
			map.put("runingNo", runingNo);
        	map.put("supplierId", loginInfoVO.getLoginInfo().getSupplierId());
        	
			List<Map<String, Object>> rows = productOrderService.getSupplierOrderInfo(map);
			
			int babyCount = bookCusInfoService.getBabyCount(orderNo);
    		if(babyCount>0){
    			mav.addObject("babyCount", babyCount);
    			mav.addObject("babyList", bookCusInfoService.getBaby(orderNo));
    		}
            int childCount = bookCusInfoService.getChildCount(orderNo);
            if(childCount>0){
            	mav.addObject("childCount", childCount);
            	mav.addObject("childList", bookCusInfoService.getChild(orderNo));
    		}
            int adultCount  =bookCusInfoService.getAdultCount(orderNo);
            if(adultCount>0){
            	mav.addObject("adultCount", adultCount);
            	mav.addObject("adultList", bookCusInfoService.getAdult(orderNo));
    		}
    		
			
        	
			mav.addObject("rows", rows);
			mav.addObject("orderNo", orderNo);
			mav.addObject("runingNo", runingNo);
        	mav.addObject("pid", pid);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("/order/detailSuppliersOrderInfo fail."+this.getClass()+".detailSuppliersOrderInfo " + e.toString());
        }
        return mav;
    }
    /**
     * 获取订单中某个产品的订单详细
     * @param response
     * @param request
     * @param orderNo
     * @param pid
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/getProductOrderDetail")
    public void getProductOrderDetail(HttpServletResponse response, HttpServletRequest request, String orderNo,String pid,String runingNo){
		try {
			
           	Map map = new HashMap();
   			map.put("orderNo", orderNo);
   			map.put("pid", pid);
           	map.put("runingNo", runingNo);
            List<Map<String,Object>> rows = productOrderService.getProductOrderDetail(map);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("order/getProductOrderDetail fail. " + e.toString());
        }
    }
	
	 /**
     * 获取订单主次联系人
     * @param response
     * @param request
     * @param orderNo
     * @param pid
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/getOrderCusInfo")
    public void getOrderCusInfo(HttpServletResponse response, HttpServletRequest request, String orderNo,String runingNo,String priceItem){
		try {
			
           	Map map = new HashMap();
           	map.put("orderNo", orderNo);
            List<Map<String,Object>> rows = productOrderService.getOrderCusInfo(map);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("order/getProductOrderDetail fail. " + e.toString());
        }
    }
    
    
    
}
