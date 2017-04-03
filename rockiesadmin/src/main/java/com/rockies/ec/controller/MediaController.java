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
import com.rockies.ec.model.Media;
import com.rockies.ec.model.MediaTag;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IMediaService;
import com.rockies.ec.services.IMediaSimilarService;
import com.rockies.ec.services.IMediaTagService;

@Controller
public class MediaController extends BaseController{
    
	@Autowired
	private IMediaService mediaService;
	@Autowired
	private IMediaSimilarService mediaSimilarService;
	@Autowired
	private IMediaTagService mediaTagService;
	@Autowired
	private ICityService cityService;
    /**
     * 视频管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/media/mediaAdmin")
    public ModelAndView mediaAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("mediaAdmin fail. " + e.toString());
        }
        return new ModelAndView("/media/mediaAdmin","menu",menu);
    }
    
    /**
     * 视屏列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/media/mediaAdminSearchList")
    public void mediaAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        
        try {
        	String name = request.getParameter("name");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("name", name);
        	
        	
            List<Map<String,Object>> rows = mediaService.getAllListMap(map);
            long count = mediaService.getCount(map);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total",count);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("mediaAdminSearchList fail. " + e.toString());
        }
    }
    /**
     * 视屏新增or修改
     * @param response
     * @param request
     * @param media
     */
	@RequestMapping(value = "/media/saveOrUpdatemediaAdmin")
    public void saveOrUpdatemediaAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("media") Media media) {
        try {
        	if(media.getId()<=0){
        		media.setCtdt(new Date());
        		media.setCtuser(getAdminId(request)+"");
        		mediaService.insert(media);
        		response.getWriter().print("success");
        	}else{
        		media.setUpdt(new Date());
        		media.setUpuser(getAdminId(request)+"");
        		mediaService.updateObject(media);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdatemediaAdmin fail. " + e.toString());
        }
    }
    
	/**
	 * 删除视屏
	 * @param response
	 * @param request
	 * @param media
	 */
	@RequestMapping(value = "/media/deletemediaAdmin")
    public void deletemediaAdmin(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	mediaService.deleteByPrimaryKey(id);
        		response.getWriter().print("success");
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deletemediaAdmin fail. " + e.toString());
        }
    }
    /**
     * 标签管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/media/mediaTagAdmin")
    public ModelAndView mediaTagAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu,int id) {
        try {
        	Media media = (Media) mediaService.getOneByPrimaryKey(id);
        	request.setAttribute("media", media);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("mediaTagAdmin fail. " + e.toString());
        }
        return new ModelAndView("/media/mediaTagAdmin","menu",menu);
    }
    
    /**
     * 标签列表
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/media/tagAdminSearchList")
    public void tagAdminSearchList(HttpServletResponse response, HttpServletRequest request,Integer offset, Integer limit) {
        try {
        	String name = request.getParameter("name");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("name", name);
        	
            List<Map<String,Object>> rows = mediaTagService.getAllListMap(map);
            long count = mediaTagService.getCount(map);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total",count);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("tagAdminSearchList fail. " + e.toString());
        }
    }
    
    
    /**
     * 标签列表新增or修改
     * @param response
     * @param request
     * @param menu
     * @return
     */
	@RequestMapping(value = "/media/saveOrUpdatetagAdmin")
    public void saveOrUpdatetagAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("mediaTag") MediaTag mediaTag) {
        try {
        	if(mediaTag.getId()<=0){
        		mediaTag.setCtdt(new Date());
        		mediaTag.setCtuser(getAdminId(request)+"");
        		mediaTagService.insert(mediaTag);
        		response.getWriter().print("success");
        	}else{
        		mediaTag.setUpdt(new Date());
        		mediaTag.setUpuser(getAdminId(request)+"");
        		mediaTagService.updateObject(mediaTag);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("mediaTagService fail. " + e.toString());
        }
    }
	
	/**
     * 删除标签列表
     * @param response
     * @param request
     * @param menu
     * @return
     */
	@RequestMapping(value = "/media/deletetagAdmin")
    public void deletetagAdmin(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	mediaTagService.deleteByPrimaryKey(id);
        		response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deletetagAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 相似套餐管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/media/mediaSimilarAdmin")
    public ModelAndView similarAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu,int id) {
        try {
        	Media media = (Media) mediaService.getOneByPrimaryKey(id);
        	request.setAttribute("media", media);
        	
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/media/mediaSimilarAdmin","menu",menu);
    }
    
    @RequestMapping(value = "/media/mediaSimilarAdminSearchList")
    public void similarAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit,int id) {
        try {
        	List<Map<String,Object>> rows = mediaSimilarService.getAllProductList(id);
       	 	Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total",mediaSimilarService.getMediaSimilarCount(id));
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("similarAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 选择商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/media/mediaProductAdmin")
    public ModelAndView mediaProductAdmin(HttpServletResponse response, HttpServletRequest request,String menu,int id) {
        try {
        	Media media = (Media) mediaService.getOneByPrimaryKey(id);
        	Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
        	request.setAttribute("cityList", cityList);
        	request.setAttribute("media", media);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("themeProductAdmin fail. " + e.toString());
        }
        return new ModelAndView("/media/mediaProductAdmin","menu",menu);
    }
    
    /**
     * 保存商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/media/saveMediaProduct")
    public void saveMediaProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	int mediaId = Integer.parseInt(request.getParameter("mediaId"));
        	String ids = request.getParameter("ids");
        	mediaSimilarService.saveListMediaSimilarProduct(ids, mediaId, getAdminId(request));
        	 response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveMediaProduct fail. " + e.toString());
        }
    }
    
    /**
     * 保存商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/media/checkMediaProduct")
    public void checkMediaProduct(HttpServletResponse response, HttpServletRequest request) {
        try {
        	int mediaId = Integer.parseInt(request.getParameter("mediaId"));
        	String ids = request.getParameter("ids");
        	String[] idList = ids.split(",");
        	StringBuffer sb = new StringBuffer();
        	sb.append("分类编号为：");
        	boolean flag = true;
        	Map map = new HashMap();
    		for(String id :idList){
    			map.put("mediaId", mediaId);
    			map.put("productId", id);
    			boolean bool = mediaSimilarService.checkProductExist(map);
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
            logger.info("checkMediaProduct fail. " + e.toString());
        }
    }
    
    /**
     * 删除商品
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/media/deleteMediaProduct")
    public void deleteMediaProduct(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	mediaSimilarService.deleteByPrimaryKey(id);
        	 response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveMediaProduct fail. " + e.toString());
        }
    }
    
}
