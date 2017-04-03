package com.rockies.ec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.model.City;
import com.rockies.ec.model.EntInfo;
import com.rockies.ec.model.EntPriceInfo;
import com.rockies.ec.model.EntRouteDetailInfo;
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IEntInfoService;
import com.rockies.ec.services.IEntPriceInfoService;
import com.rockies.ec.services.IEntRouteDetailInfoService;
import com.rockies.ec.services.ISuppliersService;

@Controller
public class EntInfoController extends BaseController {
    
    @Autowired private IEntInfoService entInfoService;
    @Autowired private ISuppliersService suppliersService;
    @Autowired private ICityService cityService;
    @Autowired private IEntPriceInfoService entPriceInfoService;
    @Autowired private IEntRouteDetailInfoService entRouteDetailInfoService;
    
    /** 
     * 娱乐管理
     * @param response
     * @param request
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/entAdmin",method=RequestMethod.GET)
    public ModelAndView entAdmin(HttpServletResponse response, HttpServletRequest request,
            String msg,String menu) {
        ModelAndView mav = new ModelAndView("/ent/entAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".entAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/ent/entAdminSearchList")
    public void entAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pname, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pname", pname);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = entInfoService.getCount(param);
            List<EntInfo> rows = entInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".entAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转娱乐增加页面
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toAddEntAdmin",method=RequestMethod.GET)
    public ModelAndView toAddEntAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        ModelAndView mav = new ModelAndView("/ent/toAddEntAdmin","menu",menu);
        try {

            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);
            
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);
            
            mav.addObject("row",new EntInfo());
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype4"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode4"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddEntAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 新增娱乐
     * @param response
     * @param request
     * @param menu
     * @param entInfo
     * @return
     */
    @RequestMapping(value = "/ent/addEntAdmin",method=RequestMethod.POST)
    public ModelAndView addEntAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntInfo entInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/ent/entAdmin.html","menu",menu);
        try {
            String userName = "test1";
            entInfo.setCtdt(new Date());
            entInfo.setCtuser(userName);
            entInfo.setDel_flg("0");
            if(entInfoService.insert(entInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addEntAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转娱乐修改页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toUpdateEntAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateEntAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toUpdateEntAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            
            EntInfo row = entInfoService.selectEntInfoByParam(param);
            mav.addObject("row",row);
            
            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);

            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype4"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode4"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddEntAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 修改娱乐
     * @param response
     * @param request
     * @param menu
     * @param entInfo
     * @return
     */
    @RequestMapping(value = "/ent/updateEntAdmin",method=RequestMethod.POST)
    public ModelAndView updateEntAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntInfo entInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/ent/entAdmin.html","menu",menu);
        try {
            String userName = "test2";
            entInfo.setUpdt(new Date());
            entInfo.setUpuser(userName);
            if(entInfoService.updateByPrimaryKeySelective(entInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteEntAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 删除娱乐
     * @param response
     * @param request
     * @param menu
     * @param entInfo
     * @return
     */
    @RequestMapping(value = "/ent/deleteEntAdmin",method=RequestMethod.POST)
    public void deleteEntAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntInfo entInfo) {
        
        try {
            entInfo.setDel_flg("1");
            if(entInfoService.updateByPrimaryKeySelective(entInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteEntAdmin " + e.toString());

        }
    }
    
    /** 
     * 娱乐详细管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toSetEntPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toSetEntPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toSetEntPriceAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            EntInfo row = entInfoService.selectEntInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetEntPriceAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/ent/entPriceAdminSearchList")
    public void entPriceAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String priceItem, String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("priceItem", priceItem);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = entPriceInfoService.getCount(param);
            List<EntPriceInfo> rows = entPriceInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".entPriceAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转娱乐详细新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toAddEntPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toAddEntPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toAddEntPriceAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            EntInfo row = entInfoService.selectEntInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddEntPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 娱乐详细新增
     * @param response
     * @param request
     * @param menu
     * @param entPriceInfo
     * @return
     */
    @RequestMapping(value = "/ent/addEntPriceAdmin",method=RequestMethod.POST)
    public ModelAndView addEntPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntPriceInfo entPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/ent/toSetEntPriceAdmin.html","menu",menu);
        mav.addObject("pid",entPriceInfo.getPid());
        try {
            String userName = "test1";
            entPriceInfo.setCtdt(new Date());
            entPriceInfo.setCtuser(userName);
            entPriceInfo.setDelFlg("0");
            if(entPriceInfoService.insert(entPriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addEntPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转娱乐详细修改页面
     * @param response
     * @param request
     * @param entPriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toUpdateEntPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateEntPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            EntPriceInfo entPriceInfo,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toUpdateEntPriceAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", entPriceInfo.getPid());
            EntInfo res = entInfoService.selectEntInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",entPriceInfoService.selectByPrimaryKey(entPriceInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateEntPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 娱乐详细修改
     * @param response
     * @param request
     * @param menu
     * @param entPriceInfo
     * @return
     */
    @RequestMapping(value = "/ent/updateEntPriceAdmin",method=RequestMethod.POST)
    public ModelAndView updateEntPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntPriceInfo entPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/ent/toSetEntPriceAdmin.html","menu",menu);
        mav.addObject("pid",entPriceInfo.getPid());
        try {
            String userName = "test2";
            entPriceInfo.setUpdt(new Date());
            entPriceInfo.setUpuser(userName);
            if(entPriceInfoService.updateByPrimaryKeySelective(entPriceInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateEntPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 删除娱乐详细
     * @param response
     * @param request
     * @param menu
     * @param entPriceInfo
     */
    @RequestMapping(value = "/ent/deleteEntPriceAdmin",method=RequestMethod.POST)
    public void deleteEntPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntPriceInfo entPriceInfo) {
        
        try {
            entPriceInfo.setDelFlg("1");
            if(entPriceInfoService.deleteByPrimaryKey(entPriceInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteEntPriceAdmin " + e.toString());

        }
    }
    
    /**  
     * 校验娱乐详细中开始时间是否重复
     * @param response
     * @param request
     * @param routeDetailInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/checkEntPriceStartTimeRepeat",method=RequestMethod.GET)
    public void checkEntPriceStartTimeRepeat(HttpServletResponse response, HttpServletRequest request,
            EntPriceInfo entPriceInfo) {
        
        try {
            EntPriceInfo rows = entPriceInfoService.selectByPrimaryKey(entPriceInfo);
            response.getWriter().print(JSON.toJSONString(rows, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".checkEntPriceStartTimeRepeat " + e.toString());
        }

    }
    
    /** 
     * 娱乐详细管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toSetEntRouteDetailAdmin",method=RequestMethod.GET)
    public ModelAndView toSetEntRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toSetEntRouteDetailAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            EntInfo row = entInfoService.selectEntInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetEntRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/ent/entRouteDetailAdminSearchList")
    public void entRouteDetailAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pday, String pid,String title, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("pday", pday);
            param.put("title", title);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = entRouteDetailInfoService.getCount(param);
            List<EntRouteDetailInfo> rows = entRouteDetailInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".entRouteDetailAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转娱乐详细新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toAddEntRouteDetailAdmin",method=RequestMethod.GET)
    public ModelAndView toAddEntRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toAddEntRouteDetailAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            EntInfo row = entInfoService.selectEntInfoByParam(param);
            mav.addObject("row",row);

        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddEntRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 娱乐详细新增
     * @param response
     * @param request
     * @param menu
     * @param entPriceInfo
     * @return
     */
    @RequestMapping(value = "/ent/addEntRouteDetailAdmin",method=RequestMethod.POST)
    public ModelAndView addEntRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntRouteDetailInfo entRouteDetailInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/ent/toSetEntRouteDetailAdmin.html","menu",menu);
        mav.addObject("pid",entRouteDetailInfo.getPid());
        try {
            String userName = "test1";
            entRouteDetailInfo.setCtdt(new Date());
            entRouteDetailInfo.setCtuser(userName);
            entRouteDetailInfo.setDelFlg("0");
            if(entRouteDetailInfoService.insert(entRouteDetailInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addEntRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转娱乐详细修改页面
     * @param response
     * @param request
     * @param entPriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/toUpdateEntRouteDetailAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateEntRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request,
            EntRouteDetailInfo entRouteDetailInfo,String menu) {
        ModelAndView mav = new ModelAndView("/ent/toUpdateEntRouteDetailAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", entRouteDetailInfo.getPid());
            EntInfo res = entInfoService.selectEntInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",entRouteDetailInfoService.selectByPrimaryKey(entRouteDetailInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateEntRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 娱乐详细修改
     * @param response
     * @param request
     * @param menu
     * @param entPriceInfo
     * @return
     */
    @RequestMapping(value = "/ent/updateEntRouteDetailAdmin",method=RequestMethod.POST)
    public ModelAndView updateEntRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntRouteDetailInfo entRouteDetailInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/ent/toSetEntRouteDetailAdmin.html","menu",menu);
        mav.addObject("pid",entRouteDetailInfo.getPid());
        try {
            String userName = "test2";
            entRouteDetailInfo.setUpdt(new Date());
            entRouteDetailInfo.setUpuser(userName);
            if(entRouteDetailInfoService.updateByPrimaryKeySelective(entRouteDetailInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateEntRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 删除娱乐详细
     * @param response
     * @param request
     * @param menu
     * @param entPriceInfo
     */
    @RequestMapping(value = "/ent/deleteEntRouteDetailAdmin",method=RequestMethod.POST)
    public void deleteEntRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            EntRouteDetailInfo entRouteDetailInfo) {
        
        try {
            entRouteDetailInfo.setDelFlg("1");
            if(entRouteDetailInfoService.deleteByPrimaryKey(entRouteDetailInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteEntRouteDetailAdmin " + e.toString());

        }
    }
    
    /**  
     * 校验娱乐详细中开始时间是否重复
     * @param response
     * @param request
     * @param routeDetailInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/ent/checkEntRouteDetailTimeRepeat",method=RequestMethod.GET)
    public void checkEntRouteDetailTimeRepeat(HttpServletResponse response, HttpServletRequest request,
            EntRouteDetailInfo entRouteDetailInfo) {
        
        try {
            EntRouteDetailInfo rows = entRouteDetailInfoService.selectByPrimaryKey(entRouteDetailInfo);
            response.getWriter().print(JSON.toJSONString(rows, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".checkEntRouteDetailTimeRepeat " + e.toString());
        }

    }
    
    private int getEntRouteDetailPday(Map<String,Object> param){
        int pday = entRouteDetailInfoService.getCount(param);
        return pday+1;
    }
    
}
