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
import com.rockies.ec.model.ExtraCostInfo;
import com.rockies.ec.model.ProductInfo;
import com.rockies.ec.services.IExtraCostInfoService;
import com.rockies.ec.services.IProductInfoService;


@Controller
public class ExtraCostController extends BaseController {

    @Autowired private IExtraCostInfoService extraCostInfoService;
    @Autowired private IProductInfoService productInfoService;
    
    /**
     * 加价管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/extraCost/extraCostAdmin",method=RequestMethod.GET)
    public ModelAndView extraCostAdmin(HttpServletResponse response, HttpServletRequest request,
            ProductInfo productInfo, String msg,String menu) {
        ModelAndView mav = new ModelAndView("/extraCost/extraCostAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            ProductInfo row = productInfoService.selectByPrimaryKey(productInfo);
            mav.addObject("row",row);
            
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype"+row.getPtype()));
            mav.addObject("pTypeUrl",CommonUtils.getPropertiesBykey("ptype"+row.getPtype()+"_url"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".extraCostAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/extraCost/extraCostAdminSearchList")
    public void extraCostAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String extraCostType, String pid, Integer offset, Integer limit) {

        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            param.put("extraCostType", extraCostType);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = extraCostInfoService.getCount(param);
            List<ExtraCostInfo> rows = extraCostInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".extraCostAdminSearchList " + e.toString());
        }
    }
    
    /**
     * 跳转加价新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/extraCost/toAddExtraCostAdmin",method=RequestMethod.GET)
    public ModelAndView toAddExtraCostAdmin(HttpServletResponse response, HttpServletRequest request,
            ProductInfo productInfo,String menu) {
        ModelAndView mav = new ModelAndView("/extraCost/toAddExtraCostAdmin","menu",menu);
        try {
            
            ProductInfo row = productInfoService.selectByPrimaryKey(productInfo);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddExtraCostAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 加价新增
     * @param response
     * @param request
     * @param menu
     * @param extraCostInfo
     * @return
     */
    @RequestMapping(value = "/extraCost/addExtraCostAdmin",method=RequestMethod.POST)
    public ModelAndView addExtraCostAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            ExtraCostInfo extraCostInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/extraCost/extraCostAdmin.html","menu",menu);
        mav.addObject("pid",extraCostInfo.getPid());
        try {
            String userName = "test1";
            extraCostInfo.setCtdt(new Date());
            extraCostInfo.setCtuser(userName);
            extraCostInfo.setDelFlg("0");
            if(extraCostInfoService.insert(extraCostInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addExtraCostAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 跳转加价修改页面
     * @param response
     * @param request
     * @param extraCostInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/extraCost/toUpdateExtraCostAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateExtraCostAdmin(HttpServletResponse response, HttpServletRequest request,
            ExtraCostInfo extraCostInfo,String menu) {
        ModelAndView mav = new ModelAndView("/extraCost/toUpdateExtraCostAdmin","menu",menu);
        try {
            
            ProductInfo productInfo = new ProductInfo();
            productInfo.setPid(extraCostInfo.getPid());
            ProductInfo res = productInfoService.selectByPrimaryKey(productInfo);
            mav.addObject("res",res);
            mav.addObject("row",extraCostInfoService.selectByPrimaryKey(extraCostInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateExtraCostAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 加价修改
     * @param response
     * @param request
     * @param menu
     * @param extraCostInfo
     * @return
     */
    @RequestMapping(value = "/extraCost/updateExtraCostAdmin",method=RequestMethod.POST)
    public ModelAndView updateExtraCostAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            ExtraCostInfo extraCostInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/extraCost/extraCostAdmin.html","menu",menu);
        mav.addObject("pid",extraCostInfo.getPid());
        try {
            String userName = "test2";
            extraCostInfo.setCtdt(new Date());
            extraCostInfo.setCtuser(userName);
            if(extraCostInfoService.updateByPrimaryKeySelective(extraCostInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateExtraCostAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 加价删除
     * @param response
     * @param request
     * @param menu
     * @param extraCostInfo
     */
    @RequestMapping(value = "/extraCost/deleteExtraCostAdmin",method=RequestMethod.POST)
    public void deleteExtraCostAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            ExtraCostInfo extraCostInfo) {
        try {
            extraCostInfo.setDelFlg("1");
            if(extraCostInfoService.updateByPrimaryKeySelective(extraCostInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteExtraCostAdmin " + e.toString());

        }
    }
    
}
