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
import com.rockies.ec.model.Banner;
import com.rockies.ec.services.IBannerService;


@Controller
public class BannerController extends BaseController{

	@Autowired
	private IBannerService bannerService;
    /**
     * banner管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/banner/bannerAdmin")
    public ModelAndView bannerAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("bannerAdmin fail. " + e.toString());
        }
        return new ModelAndView("/banner/bannerAdmin","menu",menu);
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/banner/bannerAdminSearchList")
    public void bannerAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        try {
        	Map map = new HashMap();
            List<Map<String,Object>> rows = bannerService.getAllListMap(map);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", bannerService.getCount(map));
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("bannerAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 新增or修改
     * @param response
     * @param request
     * @param banner
     */
    @RequestMapping(value = "/banner/saveOrUpdateBanner")
    public void saveOrUpdateBanner(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("banner") Banner banner ) {
        try {
        	if(banner.getId()==0){
        		banner.setCtuser(getAdminId(request)+"");
        		banner.setCtdt(new Date());
        		bannerService.insert(banner);
        		response.getWriter().print("success");
        	}else{
        		banner.setUpdt(new Date());
        		banner.setUpuser(getAdminId(request)+"");
        		bannerService.updateObject(banner);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdatebannerAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 删除
     * @param response
     * @param request
     * @param banner
     */
    @RequestMapping(value = "/banner/deleteBanner")
    public void deleteBanner(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        		bannerService.deleteByPrimaryKey(id);
        		response.getWriter().print("success");
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteBannerAdmin fail. " + e.toString());
        }
    }
    
}
