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
import com.rockies.ec.model.City;
import com.rockies.ec.model.Customer;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.ICustomerService;

@Controller
public class CustomerController extends BaseController{
    
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ICityService cityService;
	
    /**
     * 管理员管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/customer/customerAdmin")
    public ModelAndView customerAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {
        	Map map = new HashMap();
        	map.put("parentId", 0);
        	List<City> cityList = cityService.getCityList(map);
        	request.setAttribute("cityList", cityList);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/customer/customerAdmin","menu",menu);
    }
    /**
     * 客户列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/customer/customerAdminSearchList")
    public void customerAdminSearchList(HttpServletResponse response, HttpServletRequest request,Integer offset, Integer limit) {
        try {
        	String name = request.getParameter("customerName");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("customerName", name);

            List<Map<String,Object>> rows = customerService.getAllListMap(map);
            long count = customerService.getCount(map);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", count);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
    }
    
    /**
     * 到新增客户页面
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/customer/addCustomer")
    public ModelAndView addCustomer(HttpServletResponse response, HttpServletRequest request,String menu) {
        try {
        	Map map = new HashMap();
        	map.put("parentId", 0);
        	List<City> cityList = cityService.getCityList(map);
        	request.setAttribute("cityList", cityList);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/customer/addCustomer","menu",menu);
    }
    
    /**
     * 到新增客户页面
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/customer/saveCustomer")
    public void saveCustomer(HttpServletResponse response, HttpServletRequest request,String menu,@ModelAttribute("customer") Customer customer) {
        try {
        	customer.setCtuser(getAdminId(request)+"");
        	customer.setCtdt(new Date());
        	customerService.insert(customer);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
    }
    
    /**
     * 修改客户
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/customer/updateCustomer")
    public void updateCustomer(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("customer") Customer customer) {
        try {
        	customer.setUpdt(new Date());
        	customer.setUpuser(getAdminId(request)+"");
        	customerService.updateObject(customer);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("updateCustomer fail. " + e.toString());
        }
    }
    
    /**
     * 删除客户
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/customer/deleteCustomer")
    public void deleteCustomer(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	customerService.deleteByPrimaryKey(id);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteCustomer fail. " + e.toString());
        }
    }
    
    
}
