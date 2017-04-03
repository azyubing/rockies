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
import com.rockies.ec.model.Theme;
import com.rockies.ec.model.ThemeCatalog;
import com.rockies.ec.model.ThemeProduct;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IThemeCatalogService;
import com.rockies.ec.services.IThemeProductService;
import com.rockies.ec.services.IThemeService;
import com.rockies.ec.vo.ThemeCatalogVO;

@Controller
public class ThemeController extends BaseController{
   
	@Autowired
	private IThemeService themeService;
	@Autowired
	private IThemeCatalogService themeCatalogService;
	@Autowired
	private IThemeProductService themeProductService;
	@Autowired
	private ICityService cityService;
	
    /**
     * 主题管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/theme/themeAdmin")
    public ModelAndView themeAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/theme/themeAdmin","menu",menu);
    }
    
    /**
     * 主题查询
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/theme/themeAdminSearchList")
    public void themeAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        
        try {
        	String name = request.getParameter("name");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("name", name);
        	
            List<Map<String,Object>> rows = themeService.getAllListMap(map);
            long count = themeService.getCount(map);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", count);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("themeAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 主题删除
     * 
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/theme/deleteThemeAdmin")
    public void deleteThemeAdmin(HttpServletResponse response, HttpServletRequest request,int id) {
    	try {
    		themeService.deleteByPrimaryKey(id);
    		response.getWriter().print("success");
    	} catch (Exception e) {
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.info("deleteThemeAdmin fail. " + e.toString());
    	}
    }
    /**
     * 主题新增or修改
     * 
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/theme/saveOrUpdateThemeAdmin")
    public void saveOrUpdateThemeAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("theme") Theme theme) {
        
        try {
        	if(theme.getId()<=0){
        		theme.setCtdt(new Date());
        		theme.setCtuser(getAdminId(request)+"");
        		themeService.insert(theme);
        		response.getWriter().print("success");
        	}else{
        		theme.setUpdt(new Date());
        		theme.setUpuser(getAdminId(request)+"");
        		themeService.updateObject(theme);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateThemeAdmin fail. " + e.toString());
        }
    }
    
    
    
    /**
     * 主题分类管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/theme/themeCatalogAdmin")
    public ModelAndView themeCatalogAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu,int id) {
        try {
        	Theme theme = (Theme) themeService.getOneByPrimaryKey(id);
        	request.setAttribute("theme", theme);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/theme/themeCatalogAdmin","menu",menu);
    }
    
    /**
     * 主题分类查询
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/theme/themeCatalogAdminSearchList")
    public void themeCatalogAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        try {
        	int themeId = Integer.parseInt(request.getParameter("themeId"));
        	Map map = new HashMap();
			map.put("offset", offset);
			map.put("limit", limit);
        	map.put("themeId", themeId);
        	
            List<Map<String,Object>> rows = themeCatalogService.getAllListMap(map);
            long count = themeCatalogService.getCount(map);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total",count);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("themeCatalogAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 主题分类新增or修改
     * 
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/theme/saveOrUpdateThemeCatalogAdmin")
    public void saveOrUpdateThemeCatalogAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("themeCatalog") ThemeCatalog themeCatalog) {
        
        try {
        	if(themeCatalog.getId()<=0){
        		themeCatalog.setCtdt(new Date());
        		themeCatalog.setCtuser(getAdminId(request)+"");
        		themeCatalog.setHasProduct(1);
        		themeCatalogService.insert(themeCatalog);
        		response.getWriter().print("success");
        	}else{
        		themeCatalog.setUpdt(new Date());
        		themeCatalog.setUpuser(getAdminId(request)+"");
        		themeCatalogService.updateObject(themeCatalog);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateThemeAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 删除主题分类
     * 
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/theme/deleteThemeCatalogAdmin")
    public void deleteThemeCatalogAdmin(HttpServletResponse response, HttpServletRequest request,int id) {
        
        try {
        	themeCatalogService.deleteByPrimaryKey(id);
        		response.getWriter().print("success");
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteThemeCatalogAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 主题设定商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/theme/themeSetProduct")
    public ModelAndView themeSetProduct(HttpServletResponse response, HttpServletRequest request,
            String menu,int id) {
        try {
        	ThemeCatalogVO themeCatalogVO = themeCatalogService.getThemeCatalogVO(id);
        	
        	request.setAttribute("themeCatalogVO", themeCatalogVO);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/theme/themeSetProduct","menu",menu);
    }
    
    /**
     * 主题选择商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/theme/themeProductAdmin")
    public ModelAndView themeProductAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu,int id) {
        try {
        	ThemeCatalogVO themeCatalogVO = themeCatalogService.getThemeCatalogVO(id);
        	Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
        	request.setAttribute("cityList", cityList);
        	request.setAttribute("themeCatalogVO", themeCatalogVO);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/theme/themeProductAdmin","menu",menu);
    }
    
    /**
     * 主题选择商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/theme/themeProductAdminSearchList")
    public void themeProductAdminSearchList(HttpServletResponse response, HttpServletRequest request,Integer offset, 
            Integer limit,String pname,
            String menu,int id) {
        try {
        	Map param = new HashMap();
        	  param.put("offset", offset);
              param.put("limit", limit);
              param.put("id", id);
              param.put("pname", pname);
//        	 List<Map<String,Object>> rows = themeCatalogService.getAllProductList(id);
        	 List<Map<String,Object>> rows = themeCatalogService.themeProductAdminSearchList(param);
        	 Map<String, Object> reslutMap = new HashMap<>();
             reslutMap.put("rows", rows);
             reslutMap.put("total",themeCatalogService.getAllProductCount(param));
             response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("themeProductAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 主题选择商品  添加
     * @param response
     * @param request
     * @param menu
     */
	@RequestMapping(value = "/theme/saveProduct")
    public void saveProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	String ids=request.getParameter("ids");
        	String themeCatalogId = request.getParameter("themeCatalogId");
        	int themeId = Integer.parseInt(request.getParameter("themeId"));
        	themeProductService.saveListThemeProduct(ids, themeCatalogId, getAdminId(request), themeId);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveProduct fail. " + e.toString());
        }
    } 
	
	
	 /**
     * 主题选择商品 删除
     * @param response
     * @param request
     * @param menu
     */
	@RequestMapping(value = "/theme/deleteProduct")
    public void deleteProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	int id= Integer.parseInt(request.getParameter("id"));
        	int themeCatalogId= Integer.parseInt(request.getParameter("themeCatalogId"));
        	themeProductService.deleteThemeProduct(id, themeCatalogId);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteProduct fail. " + e.toString());
        }
    } 
	
	/**
     * 主题选择商品  验证是否存在
     * @param response
     * @param request
     * @param menu
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/theme/checkProduct")
    public void checkProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	String ids=request.getParameter("ids");
        	String themeCatalogId = request.getParameter("themeCatalogId");
        	int themeId = Integer.parseInt(request.getParameter("themeId"));
        	String[] idList = ids.split(",");
        	StringBuffer sb = new StringBuffer();
        	sb.append("分类编号为：");
        	boolean flag = true;
        	Map map = new HashMap();
    		for(String id :idList){
    			map.put("themeId", themeId);
    			map.put("productId", id);
    			map.put("themeCatalogId", Integer.parseInt(themeCatalogId));
    			boolean bool = themeProductService.checkProductExist(map);
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
            logger.info("saveProduct fail. " + e.toString());
        }
    } 
	
	 /**
     * 主题选择商品 删除
     * @param response
     * @param request
     * @param menu
     */
	@RequestMapping(value = "/theme/updateProduct")
    public void updateProduct(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("themeProduct") ThemeProduct themeProduct) {
        try {
        	themeProduct.setUpdt(new Date());
        	themeProduct.setUpuser(getAdminId(request)+"");
        	themeProductService.updateObject(themeProduct);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteProduct fail. " + e.toString());
        }
    } 
    
}
