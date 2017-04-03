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
import com.rockies.ec.services.ICityService;

@Controller
public class CityController extends BaseController {
	
	@Autowired
	private ICityService cityService;
	 /**
     * 城市管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/city/cityAdmin")
    public ModelAndView cityAdmin(HttpServletResponse response, HttpServletRequest request,String menu) {
        try {
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
        	request.setAttribute("cityList", cityList);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("cityAdminSecrchList fail. " + e.toString());
        }
        return new ModelAndView("city/cityAdmin","menu",menu) ;
    }
    @RequestMapping(value = "/city/cityAdminSearchList")
    public void cityAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            int parentId, Integer offset, Integer limit) {
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("parentId", parentId);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = cityService.getCityListCount(param);
            List<City> rows = cityService.getCityList(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".cityAdminSearchList " + e.toString());
        }
    }
    
    /**
     * ajax获取城市列表
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/city/ajaxGetCityList")
    public void ajaxGetCityList(HttpServletResponse response, HttpServletRequest request,int parentId) {
        try {
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", parentId);
            List<City> cityList = cityService.getCityList(paramCity);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", cityList);
            reslutMap.put("total", cityList.size());
        	response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("ajaxGetCityList fail. " + e.toString());
        }
    }
    
    /**
     * ajax添加城市
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/city/ajaxSaveCity")
    public void ajaxSaveCity(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("city") City city) {
        try {
        	city.setCtdt(new Date());
        	city.setCtuser(getAdminId(request)+"");
        	cityService.saveCity(city);
        	response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("ajaxSaveCity fail. " + e.toString());
        }
    }
    
    /**
     * ajax修改城市
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/city/ajaxUpdateCity")
    public void ajaxUpdateCity(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("city") City city,int parentId) {
        try {
        	city.setUpdt(new Date());
        	city.setUpuser(getAdminId(request)+"");
        	cityService.updateCity(city);
        	response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("ajaxUpdateCity fail. " + e.toString());
        }
    }
    
    /**
     * ajax删除城市
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/city/ajaxDeleteCity")
    public void ajaxDeleteCity(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	cityService.deleteCity(id);
			response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("ajaxUpdateCity fail. " + e.toString());
        }
    }
    /**
     * ajax验证cityCode是否存在
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/city/ajaxCheckCityCode")
    public void ajaxCheckCityCode(HttpServletResponse response, HttpServletRequest request,String cityCode) {
        try {
        	boolean bool = cityService.checkCityCode(cityCode);
        	response.getWriter().write(bool+"");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("ajaxCheckCityCode fail. " + e.toString());
        }
    }
}
