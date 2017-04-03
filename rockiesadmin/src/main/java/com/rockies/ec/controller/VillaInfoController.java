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
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.model.VillaDetailPriceInfo;
import com.rockies.ec.model.VillaInfo;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.ISuppliersService;
import com.rockies.ec.services.IVillaDetailPriceInfoService;
import com.rockies.ec.services.IVillaInfoService;

@Controller
public class VillaInfoController extends BaseController {

    @Autowired private IVillaInfoService villaInfoService;
    @Autowired private ISuppliersService suppliersService;
    @Autowired private ICityService cityService;
    @Autowired private IVillaDetailPriceInfoService villaDetailPriceInfoService;
    
    /** 
     * 别墅管理
     * @param response
     * @param request
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/villa/villaAdmin",method=RequestMethod.GET)
    public ModelAndView villaAdmin(HttpServletResponse response, HttpServletRequest request,
            String msg,String menu) {
        ModelAndView mav = new ModelAndView("/villa/villaAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".villaAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/villa/villaAdminSearchList")
    public void villaAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pname, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pname", pname);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = villaInfoService.getCount(param);
            List<VillaInfo> rows = villaInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".villaAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转别墅增加页面
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/villa/toAddVillaAdmin",method=RequestMethod.GET)
    public ModelAndView toAddVillaAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        ModelAndView mav = new ModelAndView("/villa/toAddVillaAdmin","menu",menu);
        try {

            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);
            
            mav.addObject("row",new VillaInfo());
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype2"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode2"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddVillaAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 新增别墅
     * @param response
     * @param request
     * @param menu
     * @param villaInfo
     * @return
     */
    @RequestMapping(value = "/villa/addVillaAdmin",method=RequestMethod.POST)
    public ModelAndView addVillaAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            VillaInfo villaInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/villa/villaAdmin.html","menu",menu);
        try {
            String userName = "test1";
            villaInfo.setCtdt(new Date());
            villaInfo.setCtuser(userName);
            villaInfo.setDel_flg("0");
            if(villaInfoService.insert(villaInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addVillaAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转别墅修改页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/villa/toUpdateVillaAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateVillaAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/villa/toUpdateVillaAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            
            VillaInfo row = villaInfoService.selectVillaInfoByParam(param);
            mav.addObject("row",row);
            
            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);

            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype2"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode2"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateVillaAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 修改别墅
     * @param response
     * @param request
     * @param menu
     * @param villaInfo
     * @return
     */
    @RequestMapping(value = "/villa/updateVillaAdmin",method=RequestMethod.POST)
    public ModelAndView updateVillaAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            VillaInfo villaInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/villa/villaAdmin.html","menu",menu);
        try {
            String userName = "test2";
            villaInfo.setUpdt(new Date());
            villaInfo.setUpuser(userName);
            if(villaInfoService.updateByPrimaryKeySelective(villaInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateVillaAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 删除别墅
     * @param response
     * @param request
     * @param menu
     * @param villaInfo
     * @return
     */
    @RequestMapping(value = "/villa/deleteVillaAdmin",method=RequestMethod.POST)
    public void deleteVillaAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            VillaInfo villaInfo) {
        
        try {
            villaInfo.setDel_flg("1");
            if(villaInfoService.updateByPrimaryKeySelective(villaInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteVillaAdmin " + e.toString());

        }
    }
    
    /** 
     * 别墅价格管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/villa/toSetVillaDetailPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toSetVillaDetailPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/villa/toSetVillaDetailPriceAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            VillaInfo row = villaInfoService.selectVillaInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetVillaDetailPriceAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/villa/villaDetailPriceAdminSearchList")
    public void villaDetailPriceAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String villaNm, String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("villaNm", villaNm);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = villaDetailPriceInfoService.getCount(param);
            List<VillaDetailPriceInfo> rows = villaDetailPriceInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".villaDetailPriceAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转别墅价格新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/villa/toAddVillaDetailPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toAddVillaDetailPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/villa/toAddVillaDetailPriceAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            VillaInfo row = villaInfoService.selectVillaInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddVillaDetailPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 别墅价格新增
     * @param response
     * @param request
     * @param menu
     * @param villaDetailPriceInfo
     * @return
     */
    @RequestMapping(value = "/villa/addVillaDetailPriceAdmin",method=RequestMethod.POST)
    public ModelAndView addVillaDetailPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            VillaDetailPriceInfo villaDetailPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/villa/toSetVillaDetailPriceAdmin.html","menu",menu);
        mav.addObject("pid",villaDetailPriceInfo.getPid());
        try {
            String userName = "test1";
            villaDetailPriceInfo.setCtdt(new Date());
            villaDetailPriceInfo.setCtuser(userName);
            villaDetailPriceInfo.setDelFlg("0");
            if(villaDetailPriceInfoService.insert(villaDetailPriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addVillaDetailPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转别墅价格修改页面
     * @param response
     * @param request
     * @param villaDetailPriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/villa/toUpdateVillaDetailPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateVillaDetailPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            VillaDetailPriceInfo villaDetailPriceInfo,String menu) {
        ModelAndView mav = new ModelAndView("/villa/toUpdateVillaDetailPriceAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", villaDetailPriceInfo.getPid());
            VillaInfo res = villaInfoService.selectVillaInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",villaDetailPriceInfoService.selectByPrimaryKey(villaDetailPriceInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateVillaDetailPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 别墅价格修改
     * @param response
     * @param request
     * @param menu
     * @param villaDetailPriceInfo
     * @return
     */
    @RequestMapping(value = "/villa/updateVillaDetailPriceAdmin",method=RequestMethod.POST)
    public ModelAndView updateVillaDetailPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            VillaDetailPriceInfo villaDetailPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/villa/toSetVillaDetailPriceAdmin.html","menu",menu);
        mav.addObject("pid",villaDetailPriceInfo.getPid());
        try {
            String userName = "test2";
            villaDetailPriceInfo.setUpdt(new Date());
            villaDetailPriceInfo.setUpuser(userName);
            if(villaDetailPriceInfoService.updateByPrimaryKeySelective(villaDetailPriceInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateVillaDetailPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 删除别墅价格
     * @param response
     * @param request
     * @param menu
     * @param villaDetailPriceInfo
     */
    @RequestMapping(value = "/villa/deleteVillaDetailPriceAdmin",method=RequestMethod.POST)
    public void deleteVillaDetailPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            VillaDetailPriceInfo villaDetailPriceInfo) {
        
        try {
            villaDetailPriceInfo.setDelFlg("1");
            if(villaDetailPriceInfoService.deleteByPrimaryKey(villaDetailPriceInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteVillaDetailPriceAdmin " + e.toString());

        }
    }
    

    
    
}
