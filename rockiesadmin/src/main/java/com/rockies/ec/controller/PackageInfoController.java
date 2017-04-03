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
import com.rockies.ec.model.PackageInfo;
import com.rockies.ec.model.PackagePriceInfo;
import com.rockies.ec.model.RouteDayInfo;
import com.rockies.ec.model.RouteDetailInfo;
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IPackageInfoService;
import com.rockies.ec.services.IPackagePriceInfoService;
import com.rockies.ec.services.IRouteDayInfoService;
import com.rockies.ec.services.IRouteDetailInfoService;
import com.rockies.ec.services.ISuppliersService;

@Controller
public class PackageInfoController extends BaseController {

    @Autowired private IPackageInfoService packageInfoService;
    @Autowired private ISuppliersService suppliersService;
    @Autowired private ICityService cityService;
    @Autowired private IPackagePriceInfoService packagePriceInfoService;
    @Autowired private IRouteDayInfoService routeDayInfoService;
    @Autowired private IRouteDetailInfoService routeDetailInfoService;
    
    /** 
     * 套餐管理
     * @param response
     * @param request
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/packageAdmin",method=RequestMethod.GET)
    public ModelAndView packageAdmin(HttpServletResponse response, HttpServletRequest request,
            String msg,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/packageAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".packageAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/setpack/packageAdminSearchList")
    public void packageAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pname, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pname", pname);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = packageInfoService.getCount(param);
            List<PackageInfo> rows = packageInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".packageAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转套餐增加页面
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toAddPackageAdmin",method=RequestMethod.GET)
    public ModelAndView toAddPackageAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toAddPackageAdmin","menu",menu);
        try {

            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);
            
            mav.addObject("row",new PackageInfo());
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype0"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode0"));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddPackageAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 新增套餐
     * @param response
     * @param request
     * @param menu
     * @param packageInfo
     * @return
     */
    @RequestMapping(value = "/setpack/addPackageAdmin",method=RequestMethod.POST)
    public ModelAndView addPackageAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            PackageInfo packageInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/packageAdmin.html","menu",menu);
        try {
            String userName = "test1";
            packageInfo.setCtdt(new Date());
            packageInfo.setCtuser(userName);
            if(packageInfoService.insert(packageInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addPackageAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转套餐修改页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toUpdatePackageAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdatePackageAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toUpdatePackageAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            
            PackageInfo row = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("row",row);
            
            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);

            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype0"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode0"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddPackageAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 修改套餐
     * @param response
     * @param request
     * @param menu
     * @param packageInfo
     * @return
     */
    @RequestMapping(value = "/setpack/updatePackageAdmin",method=RequestMethod.POST)
    public ModelAndView updatePackageAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            PackageInfo packageInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/packageAdmin.html","menu",menu);
        try {
            String userName = "test2";
            packageInfo.setUpdt(new Date());
            packageInfo.setUpuser(userName);
            if(packageInfoService.updateByPrimaryKeySelective(packageInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deletePackageAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 删除套餐
     * @param response
     * @param request
     * @param menu
     * @param packageInfo
     * @return
     */
    @RequestMapping(value = "/setpack/deletePackageAdmin",method=RequestMethod.POST)
    public void deletePackageAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            PackageInfo packageInfo) {
        
        try {
            packageInfo.setDel_flg("1");
            if(packageInfoService.updateByPrimaryKeySelective(packageInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deletePackageAdmin " + e.toString());

        }
    }
    
    /** 
     * 套餐价格管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toSetPackagePriceAdmin",method=RequestMethod.GET)
    public ModelAndView toSetPackagePriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toSetPackagePriceAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            PackageInfo row = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetPackagePriceAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/setpack/packagePriceAdminSearchList")
    public void packagePriceAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String priceItem, String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("priceItem", priceItem);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = packagePriceInfoService.getCount(param);
            List<PackagePriceInfo> rows = packagePriceInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".packagePriceAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转套餐价格新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toAddPackagePriceAdmin",method=RequestMethod.GET)
    public ModelAndView toAddPackagePriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toAddPackagePriceAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            PackageInfo row = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddPackagePriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 套餐价格新增
     * @param response
     * @param request
     * @param menu
     * @param packagePriceInfo
     * @return
     */
    @RequestMapping(value = "/setpack/addPackagePriceAdmin",method=RequestMethod.POST)
    public ModelAndView addPackagePriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            PackagePriceInfo packagePriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/toSetPackagePriceAdmin.html","menu",menu);
        mav.addObject("pid",packagePriceInfo.getPid());
        try {
            String userName = "test1";
            packagePriceInfo.setCtdt(new Date());
            packagePriceInfo.setCtuser(userName);
            packagePriceInfo.setDelFlg("0");
            if(packagePriceInfoService.insert(packagePriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addPackagePriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转套餐价格修改页面
     * @param response
     * @param request
     * @param packagePriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toUpdatePackagePriceAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdatePackagePriceAdmin(HttpServletResponse response, HttpServletRequest request,
            PackagePriceInfo packagePriceInfo,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toUpdatePackagePriceAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", packagePriceInfo.getPid());
            param.put("id", packagePriceInfo.getId());
            PackageInfo res = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",packagePriceInfoService.selectByPrimaryKey(packagePriceInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdatePackagePriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 套餐价格修改
     * @param response
     * @param request
     * @param menu
     * @param packagePriceInfo
     * @return
     */
    @RequestMapping(value = "/setpack/updatePackagePriceAdmin",method=RequestMethod.POST)
    public ModelAndView updatePackagePriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            PackagePriceInfo packagePriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/toSetPackagePriceAdmin.html","menu",menu);
        mav.addObject("pid",packagePriceInfo.getPid());
        try {
            String userName = "test2";
            packagePriceInfo.setUpdt(new Date());
            packagePriceInfo.setUpuser(userName);
            if(packagePriceInfoService.updateByPrimaryKeySelective(packagePriceInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updatePackagePriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 删除套餐价格
     * @param response
     * @param request
     * @param menu
     * @param packagePriceInfo
     */
    @RequestMapping(value = "/setpack/deletePackagePriceAdmin",method=RequestMethod.POST)
    public void deletePackagePriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            PackagePriceInfo packagePriceInfo) {
        
        try {
            packagePriceInfo.setDelFlg("1");
            if(packagePriceInfoService.deleteByPrimaryKey(packagePriceInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deletePackagePriceAdmin " + e.toString());

        }
    }
    
    /**   
     * 套餐行程(单日)管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toSetRouteDayAdmin",method=RequestMethod.GET)
    public ModelAndView toSetRouteDayAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid, String msg,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toSetRouteDayAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            PackageInfo row = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("row",row);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetRouteDayAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/setpack/routeDayAdminSearchList")
    public void routeDayAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String tabs, String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("tabs", tabs);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = routeDayInfoService.getCount(param);
            List<RouteDayInfo> rows = routeDayInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".routeDayAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转套餐行程新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toAddRouteDayAdmin",method=RequestMethod.GET)
    public ModelAndView toAddRouteDayAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toAddRouteDayAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            PackageInfo row = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("row",row);
            int pday = routeDayInfoService.getCount(param);
            mav.addObject("pday",pday+1);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddRouteDayAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 套餐行程新增
     * @param response
     * @param request
     * @param menu
     * @param routeDayInfo
     * @return
     */
    @RequestMapping(value = "/setpack/addRouteDayAdmin",method=RequestMethod.POST)
    public ModelAndView addRouteDayAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RouteDayInfo routeDayInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/toSetRouteDayAdmin.html","menu",menu);
        mav.addObject("pid",routeDayInfo.getPid());
        try {
            String userName = "test1";
            routeDayInfo.setCtdt(new Date());
            routeDayInfo.setCtuser(userName);
            routeDayInfo.setDelFlg("0");
            if(routeDayInfoService.insert(routeDayInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addRouteDayAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 跳转套餐行程修改页面
     * @param response
     * @param request
     * @param routeDayInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toUpdateRouteDayAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateRouteDayAdmin(HttpServletResponse response, HttpServletRequest request,
            RouteDayInfo routeDayInfo,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toUpdateRouteDayAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", routeDayInfo.getPid());
            PackageInfo res = packageInfoService.selectPackageInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",routeDayInfoService.selectByPrimaryKey(routeDayInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateRouteDayAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 套餐行程修改
     * @param response
     * @param request
     * @param menu
     * @param routeDayInfo
     * @return
     */
    @RequestMapping(value = "/setpack/updateRouteDayAdmin",method=RequestMethod.POST)
    public ModelAndView updateRouteDayAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RouteDayInfo routeDayInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/toSetRouteDayAdmin.html","menu",menu);
        mav.addObject("pid",routeDayInfo.getPid());
        try {
            String userName = "test2";
            routeDayInfo.setUpdt(new Date());
            routeDayInfo.setUpuser(userName);
            if(routeDayInfoService.updateByPrimaryKeySelective(routeDayInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateRouteDayAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 删除套餐行程
     * @param response
     * @param request
     * @param menu
     * @param routeDayInfo
     */
    @RequestMapping(value = "/setpack/deleteRouteDayAdmin",method=RequestMethod.POST)
    public void deleteRouteDayAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RouteDayInfo routeDayInfo) {
        
        try {
            routeDayInfo.setDelFlg("1");
        //    if(routeDayInfoService.updateByPrimaryKeySelective(routeDayInfo)){
              if(routeDayInfoService.deleteByPrimaryKey(routeDayInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteRouteDayAdmin " + e.toString());

        }
    }

    /** 
     * 套餐行程详细管理
     * @param response
     * @param request
     * @param pid
     * @param pday
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toSetRouteDetailAdmin",method=RequestMethod.GET)
    public ModelAndView toSetRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,Integer pday, String msg,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toSetRouteDetailAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            RouteDayInfo t = new RouteDayInfo();
            t.setPid(pid);
            t.setPday(pday);
            RouteDayInfo row = routeDayInfoService.selectByPrimaryKey(t);
            mav.addObject("row",row);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/setpack/routeDetailAdminSearchList")
    public void routeDetailAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String title, String pid, String pday, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(5);
            param.put("pid", pid);
            param.put("pday", pday);
            param.put("title", title);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = routeDetailInfoService.getCount(param);
            List<RouteDetailInfo> rows = routeDetailInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".routeDetailAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转套餐行程详细新增页面
     * @param response
     * @param request
     * @param pid
     * @param pday
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toAddRouteDetailAdmin",method=RequestMethod.GET)
    public ModelAndView toAddRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,Integer pday,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toAddRouteDetailAdmin","menu",menu);
        try {

            RouteDayInfo t = new RouteDayInfo();
            t.setPid(pid);
            t.setPday(pday);
            RouteDayInfo row = routeDayInfoService.selectByPrimaryKey(t);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 套餐行程详细新增
     * @param response
     * @param request
     * @param menu
     * @param routeDetailInfo
     * @return
     */
    @RequestMapping(value = "/setpack/addRouteDetailAdmin",method=RequestMethod.POST)
    public ModelAndView addRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RouteDetailInfo routeDetailInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/toSetRouteDetailAdmin.html","menu",menu);
        mav.addObject("pid",routeDetailInfo.getPid());
        mav.addObject("pday",routeDetailInfo.getPday());
        try {
            String userName = "test1";
            routeDetailInfo.setCtdt(new Date());
            routeDetailInfo.setCtuser(userName);
            routeDetailInfo.setDelFlg("0");
            routeDetailInfo.setItypeImg(CommonUtils.getPropertiesBykey(routeDetailInfo.getItype()));
            if(routeDetailInfoService.insert(routeDetailInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 跳转套餐行程详细修改页面
     * @param response
     * @param request
     * @param routeDetailInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/toUpdateRouteDetailAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request,
            RouteDetailInfo routeDetailInfo,String menu) {
        ModelAndView mav = new ModelAndView("/setpack/toUpdateRouteDetailAdmin","menu",menu);
        try {

            RouteDayInfo t = new RouteDayInfo();
            t.setPid(routeDetailInfo.getPid());
            t.setPday(routeDetailInfo.getPday());
            RouteDayInfo res = routeDayInfoService.selectByPrimaryKey(t);
            mav.addObject("res",res);
            
            mav.addObject("row",routeDetailInfoService.selectByPrimaryKey(routeDetailInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 套餐行程详细修改
     * @param response
     * @param request
     * @param menu
     * @param routeDetailInfo
     * @return
     */
    @RequestMapping(value = "/setpack/updateRouteDetailAdmin",method=RequestMethod.POST)
    public ModelAndView updateRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RouteDetailInfo routeDetailInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/setpack/toSetRouteDetailAdmin.html","menu",menu);
        mav.addObject("pid",routeDetailInfo.getPid());
        mav.addObject("pday",routeDetailInfo.getPday());
        try {
            String userName = "test2";
            routeDetailInfo.setUpdt(new Date());
            routeDetailInfo.setUpuser(userName);
            routeDetailInfo.setItypeImg(CommonUtils.getPropertiesBykey(routeDetailInfo.getItype()));
            if(routeDetailInfoService.updateByPrimaryKeySelective(routeDetailInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateRouteDetailAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 删除套餐行程详细
     * @param response
     * @param request
     * @param menu
     * @param routeDetailInfo
     */
    @RequestMapping(value = "/setpack/deleteRouteDetailAdmin",method=RequestMethod.POST)
    public void deleteRouteDetailAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RouteDetailInfo routeDetailInfo) {
        
        try {
            routeDetailInfo.setDelFlg("1");
            if(routeDetailInfoService.deleteByPrimaryKey(routeDetailInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteRouteDetailAdmin " + e.toString());

        }
    }
    
    /**  
     * 校验套餐行程详细中开始时间是否重复
     * @param response
     * @param request
     * @param routeDetailInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/setpack/checkStartTimeRepeat",method=RequestMethod.GET)
    public void checkStartTimeRepeat(HttpServletResponse response, HttpServletRequest request,
            RouteDetailInfo routeDetailInfo) {
        
        try {
            RouteDetailInfo rows = routeDetailInfoService.selectByPrimaryKey(routeDetailInfo);
            response.getWriter().print(JSON.toJSONString(rows, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".checkStartTimeRepeat " + e.toString());
        }

    }
    
}
