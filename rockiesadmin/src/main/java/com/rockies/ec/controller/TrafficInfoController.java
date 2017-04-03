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
import com.rockies.ec.model.TrafficCharteredPriceInfo;
import com.rockies.ec.model.TrafficInfo;
import com.rockies.ec.model.TrafficRegularPriceInfo;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.ISuppliersService;
import com.rockies.ec.services.ITrafficCharteredPriceInfoService;
import com.rockies.ec.services.ITrafficInfoService;
import com.rockies.ec.services.ITrafficRegularPriceInfoService;

@Controller
public class TrafficInfoController extends BaseController {
    @Autowired private ITrafficInfoService trafficInfoService;
    @Autowired private ISuppliersService suppliersService;
    @Autowired private ICityService cityService;
    @Autowired private ITrafficCharteredPriceInfoService trafficCharteredPriceInfoService;
    @Autowired private ITrafficRegularPriceInfoService trafficRegularPriceInfoService;
    
    /** 
     * 交通管理
     * @param response
     * @param request
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/trafficAdmin",method=RequestMethod.GET)
    public ModelAndView trafficAdmin(HttpServletResponse response, HttpServletRequest request,
            String msg,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/trafficAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".trafficAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/traffic/trafficAdminSearchList")
    public void trafficAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pname, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pname", pname);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = trafficInfoService.getCount(param);
            List<TrafficInfo> rows = trafficInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".trafficAdminSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转交通增加页面
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toAddTrafficAdmin",method=RequestMethod.GET)
    public ModelAndView toAddTrafficAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toAddTrafficAdmin","menu",menu);
        try {

            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);
            
            mav.addObject("row",new TrafficInfo());
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype3"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode3"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddTrafficAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 新增交通
     * @param response
     * @param request
     * @param menu
     * @param trafficInfo
     * @return
     */
    @RequestMapping(value = "/traffic/addTrafficAdmin",method=RequestMethod.POST)
    public ModelAndView addTrafficAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficInfo trafficInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/traffic/trafficAdmin.html","menu",menu);
        try {
            String userName = "test1";
            trafficInfo.setCtdt(new Date());
            trafficInfo.setCtuser(userName);
            trafficInfo.setDel_flg("0");
            if(trafficInfoService.insert(trafficInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addTrafficAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转交通修改页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toUpdateTrafficAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateTrafficAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toUpdateTrafficAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            
            TrafficInfo row = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("row",row);
            
            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);

            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);

            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype3"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode3"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateTrafficAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 修改交通
     * @param response
     * @param request
     * @param menu
     * @param trafficInfo
     * @return
     */
    @RequestMapping(value = "/traffic/updateTrafficAdmin",method=RequestMethod.POST)
    public ModelAndView updateTrafficAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficInfo trafficInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/traffic/trafficAdmin.html","menu",menu);
        try {
            String userName = "test2";
            trafficInfo.setUpdt(new Date());
            trafficInfo.setUpuser(userName);
            if(trafficInfoService.updateByPrimaryKeySelective(trafficInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateTrafficAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 删除交通
     * @param response
     * @param request
     * @param menu
     * @param trafficInfo
     * @return
     */
    @RequestMapping(value = "/traffic/deleteTrafficAdmin",method=RequestMethod.POST)
    public void deleteTrafficAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficInfo trafficInfo) {
        
        try {
            trafficInfo.setDel_flg("1");
            if(trafficInfoService.updateByPrimaryKeySelective(trafficInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteTrafficAdmin " + e.toString());

        }
    }
    
    /** 
     * 交通包车价格管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toSetTrafficCharteredPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toSetTrafficCharteredPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toSetTrafficCharteredPriceAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            TrafficInfo row = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetTrafficCharteredPriceAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/traffic/trafficCharteredPriceAdminSearchList")
    public void trafficCharteredPriceAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String carbrand, String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("carbrand", carbrand);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = trafficCharteredPriceInfoService.getCount(param);
            List<TrafficCharteredPriceInfo> rows = trafficCharteredPriceInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".trafficCharteredPriceSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转交通包车价格新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toAddTrafficCharteredPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toAddTrafficCharteredPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toAddTrafficCharteredPriceAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            TrafficInfo row = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddTrafficCharteredPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 交通包车价格新增
     * @param response
     * @param request
     * @param menu
     * @param trafficCharteredPriceInfo
     * @return
     */
    @RequestMapping(value = "/traffic/addTrafficCharteredPriceAdmin",method=RequestMethod.POST)
    public ModelAndView addTrafficCharteredPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficCharteredPriceInfo trafficCharteredPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/traffic/toSetTrafficCharteredPriceAdmin.html","menu",menu);
        mav.addObject("pid",trafficCharteredPriceInfo.getPid());
        try {
            String userName = "test1";
            trafficCharteredPriceInfo.setCtdt(new Date());
            trafficCharteredPriceInfo.setCtuser(userName);
            trafficCharteredPriceInfo.setDelFlg("0");
            if(trafficCharteredPriceInfoService.insert(trafficCharteredPriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addTrafficCharteredPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转交通包车价格修改页面
     * @param response
     * @param request
     * @param trafficCharteredPriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toUpdateTrafficCharteredPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateTrafficCharteredPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            TrafficCharteredPriceInfo trafficCharteredPriceInfo,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toUpdateTrafficCharteredPriceAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", trafficCharteredPriceInfo.getPid());
            TrafficInfo res = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",trafficCharteredPriceInfoService.selectByPrimaryKey(trafficCharteredPriceInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateTrafficCharteredPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 交通包车价格修改
     * @param response
     * @param request
     * @param menu
     * @param trafficCharteredPriceInfo
     * @return
     */
    @RequestMapping(value = "/traffic/updateTrafficCharteredPriceAdmin",method=RequestMethod.POST)
    public ModelAndView updateTrafficCharteredPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficCharteredPriceInfo trafficCharteredPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/traffic/toSetTrafficCharteredPriceAdmin.html","menu",menu);
        mav.addObject("pid",trafficCharteredPriceInfo.getPid());
        try {
            String userName = "test2";
            trafficCharteredPriceInfo.setUpdt(new Date());
            trafficCharteredPriceInfo.setUpuser(userName);
            if(trafficCharteredPriceInfoService.updateByPrimaryKeySelective(trafficCharteredPriceInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateTrafficCharteredPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 删除交通包车价格
     * @param response
     * @param request
     * @param menu
     * @param trafficCharteredPriceInfo
     */
    @RequestMapping(value = "/traffic/deleteTrafficCharteredPriceAdmin",method=RequestMethod.POST)
    public void deleteTrafficCharteredPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficCharteredPriceInfo trafficCharteredPriceInfo) {
        
        try {
            trafficCharteredPriceInfo.setDelFlg("1");
            if(trafficCharteredPriceInfoService.deleteByPrimaryKey(trafficCharteredPriceInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteTrafficCharteredPriceAdmin " + e.toString());

        }
    }
    
    /**  
     * 校验交通包车价格中开始时间是否重复
     * @param response
     * @param request
     * @param routeDetailInfo
     */
    @RequestMapping(value = "/traffic/checkStartTimeRepeat",method=RequestMethod.GET)
    public void checkStartTimeRepeat(HttpServletResponse response, HttpServletRequest request,
            TrafficCharteredPriceInfo trafficCharteredPriceInfo) {
        
        try {
            TrafficCharteredPriceInfo rows = trafficCharteredPriceInfoService.selectByPrimaryKey(trafficCharteredPriceInfo);
            response.getWriter().print(JSON.toJSONString(rows, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".checkStartTimeRepeat " + e.toString());
        }

    }

    /**  
     * 交通班车价格管理
     * @param response
     * @param request
     * @param pid
     * @param msg
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toSetTrafficRegularPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toSetTrafficRegularPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toSetTrafficRegularPriceAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            TrafficInfo row = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetTrafficRegularPriceAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/traffic/trafficRegularPriceAdminSearchList")
    public void trafficRegularPriceAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String carbrand, String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(4);
            param.put("pid", pid);
            param.put("carbrand", carbrand);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = trafficRegularPriceInfoService.getCount(param);
            List<TrafficRegularPriceInfo> rows = trafficRegularPriceInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".trafficRegularPriceSearchList " + e.toString());
        }
    }
    
    /** 
     * 跳转交通班车价格新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toAddTrafficRegularPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toAddTrafficRegularPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toAddTrafficRegularPriceAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            TrafficInfo row = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddTrafficRegularPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 交通班车价格新增
     * @param response
     * @param request
     * @param menu
     * @param trafficRegularPriceInfo
     * @return
     */
    @RequestMapping(value = "/traffic/addTrafficRegularPriceAdmin",method=RequestMethod.POST)
    public ModelAndView addTrafficRegularPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficRegularPriceInfo trafficRegularPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/traffic/toSetTrafficRegularPriceAdmin.html","menu",menu);
        mav.addObject("pid",trafficRegularPriceInfo.getPid());
        try {
            String userName = "test1";
            trafficRegularPriceInfo.setCtdt(new Date());
            trafficRegularPriceInfo.setCtuser(userName);
            trafficRegularPriceInfo.setDelFlg("0");
            if(trafficRegularPriceInfoService.insert(trafficRegularPriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addTrafficRegularPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 跳转交通班车价格修改页面
     * @param response
     * @param request
     * @param trafficRegularPriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/traffic/toUpdateTrafficRegularPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateTrafficRegularPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            TrafficRegularPriceInfo trafficRegularPriceInfo,String menu) {
        ModelAndView mav = new ModelAndView("/traffic/toUpdateTrafficRegularPriceAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", trafficRegularPriceInfo.getPid());
            TrafficInfo res = trafficInfoService.selectTrafficInfoByParam(param);
            mav.addObject("res",res);
            
            mav.addObject("row",trafficRegularPriceInfoService.selectByPrimaryKey(trafficRegularPriceInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateTrafficRegularPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /** 
     * 交通班车价格修改
     * @param response
     * @param request
     * @param menu
     * @param trafficRegularPriceInfo
     * @return
     */
    @RequestMapping(value = "/traffic/updateTrafficRegularPriceAdmin",method=RequestMethod.POST)
    public ModelAndView updateTrafficRegularPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficRegularPriceInfo trafficRegularPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/traffic/toSetTrafficRegularPriceAdmin.html","menu",menu);
        mav.addObject("pid",trafficRegularPriceInfo.getPid());
        try {
            String userName = "test2";
            trafficRegularPriceInfo.setUpdt(new Date());
            trafficRegularPriceInfo.setUpuser(userName);
            if(trafficRegularPriceInfoService.updateByPrimaryKeySelective(trafficRegularPriceInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateTrafficRegularPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**  
     * 删除交通班车价格
     * @param response
     * @param request
     * @param menu
     * @param trafficRegularPriceInfo
     */
    @RequestMapping(value = "/traffic/deleteTrafficRegularPriceAdmin",method=RequestMethod.POST)
    public void deleteTrafficRegularPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            TrafficRegularPriceInfo trafficRegularPriceInfo) {
        
        try {
            trafficRegularPriceInfo.setDelFlg("1");
            if(trafficRegularPriceInfoService.deleteByPrimaryKey(trafficRegularPriceInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteTrafficRegularPriceAdmin " + e.toString());

        }
    }
    
    /**  
     * 校验交通班车价格中开始时间是否重复
     * @param response
     * @param request
     * @param trafficRegularPriceInfo
     */
    @RequestMapping(value = "/traffic/checkStartTimeRegularPriceRepeat",method=RequestMethod.GET)
    public void checkStartTimeRegularPriceRepeat(HttpServletResponse response, HttpServletRequest request,
            TrafficRegularPriceInfo trafficRegularPriceInfo) {
        
        try {
            TrafficRegularPriceInfo rows = trafficRegularPriceInfoService.selectByPrimaryKey(trafficRegularPriceInfo);
            response.getWriter().print(JSON.toJSONString(rows, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".checkStartTimeRegularPriceRepeat " + e.toString());
        }

    }
}
