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
import com.rockies.ec.model.Destination;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IDestinationService;
import com.rockies.ec.vo.DestinationVO;

@Controller
public class DestinationController extends BaseController{
	@Autowired
    private ICityService cityService;
	
	@Autowired
	private IDestinationService destinationService;
    /**
     * 目的地管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/destination/destinationAdmin")
    public ModelAndView destinationAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
        	request.setAttribute("cityList",cityList);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("destinationAdmin fail. " + e.toString());
        }
        return new ModelAndView("/destination/destinationAdmin","menu",menu);
    }
    /**
     * 目的地列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/destination/destinationAdminSearchList")
    public void destinationAdminSearchList(HttpServletResponse response, HttpServletRequest request,Integer offset, Integer limit) {
        try {
        	String name = request.getParameter("name");
			Map map = new HashMap();
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("name", name);
			List<Map<String, Object>> rows = destinationService.getAllListMap(map);
			long count = destinationService.getCount(map);

			Map<String, Object> reslutMap = new HashMap<>();
			reslutMap.put("rows", rows);
			reslutMap.put("total", count);
			response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("destinationAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 添加或修改目的地
     * @param response
     * @param request
     * @param destination
     */
    @RequestMapping(value = "/destination/saveOrUpdateDestinationAdmin")
    public void saveOrUpdateDestinationAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("destination") Destination destination) {
        try {
        	if(destination.getId()<=0){
        		destination.setCtdt(new Date());
        		destination.setCtuser(getAdminId(request)+"");
        		destinationService.insert(destination);
        		response.getWriter().print("success");
        	}else{
        		destination.setUpdt(new Date());
        		destination.setUpuser(getAdminId(request)+"");
        		destinationService.updateObject(destination);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateDestinationAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 删除目的地
     * @param response
     * @param request
     * @param id
     */
    @RequestMapping(value = "/destination/deleteDestinationAdmin")
    public void deleteDestinationAdmin(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	destinationService.deleteByPrimaryKey(id);
        		response.getWriter().print("success");
        	
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteDestinationAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 目的地商品设定管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/destination/destinationSetProduct")
    public ModelAndView destinationSetProduct(HttpServletResponse response, HttpServletRequest request,
            String menu,int id) {
        try {
        	DestinationVO destinationVO = (DestinationVO) destinationService.getDestinationVO(id);
    		request.setAttribute("destinationVO", destinationVO);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/destination/destinationSetProduct","menu",menu);
    }
    
    /**
     * 目的地商品 列表
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/destination/destinationProductAdminSearcheList")
    public void destinationProductAdminSearcheList(HttpServletResponse response, HttpServletRequest request,int id) {
    	try {
    		List<Map<String,Object>> rows = destinationService.getAllProductList(id);
       	 	Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total",rows.size());
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.info("destinationProductAdminSearcheList fail. " + e.toString());
    	}
    }
    
    /**
     * 目的地商品选择管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/destination/destinationProductAdmin")
    public ModelAndView destinationProductAdmin(HttpServletResponse response, HttpServletRequest request,
    		String menu,int id) {
    	try {
    		DestinationVO destinationVO = (DestinationVO) destinationService.getDestinationVO(id);
    		Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
        	request.setAttribute("cityList", cityList);
    		request.setAttribute("destinationVO", destinationVO);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.info("login fail. " + e.toString());
    	}
    	return new ModelAndView("/destination/destinationProductAdmin","menu",menu);
    }
    
    
    /**
     * 保存商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/destination/saveDestinationProduct")
    public void saveDestinationProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	int destinationId = Integer.parseInt(request.getParameter("destinationId"));
        	String ids = request.getParameter("ids");
        	destinationService.saveListDestinationProduc(ids, destinationId, getAdminId(request));
        	 response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveDestinationProduct fail. " + e.toString());
        }
    }
    
    /**
     * 检测商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/destination/checkDestinationProduct")
    public void checkDestinationProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	int destinationId = Integer.parseInt(request.getParameter("destinationId"));
        	String ids = request.getParameter("ids");
        	String[] idList = ids.split(",");
        	StringBuffer sb = new StringBuffer();
        	sb.append("分类编号为：");
        	boolean flag = true;
        	Map map = new HashMap();
    		for(String id :idList){
    			map.put("destinationId", destinationId);
    			map.put("productId", id);
    			boolean bool = destinationService.checkProductExist(map);
    			if(!bool){
    				flag = false;
    				sb.append(id+",");
    			}
    		}
    		
        	if(flag){//不存在
        		response.getWriter().print("success");
        	}else{
        		sb.append("已存在");
        		response.getWriter().print(sb);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("checkDestinationProduct fail. " + e.toString());
        }
    }
    
    /**
     * 删除商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/destination/deleteDestinationProduct")
    public void deleteDestinationProduct(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	destinationService.deleteDestinationProduct(id);
        	 response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteDestinationProduct fail. " + e.toString());
        }
    }
    
    
}
