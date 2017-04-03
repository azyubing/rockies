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
import com.rockies.ec.model.DiscountInfo;
import com.rockies.ec.model.ProductInfo;
import com.rockies.ec.services.IDiscountInfoService;
import com.rockies.ec.services.IProductInfoService;


@Controller
public class DiscountController extends BaseController {

    @Autowired private IDiscountInfoService discountInfoService;
    @Autowired private IProductInfoService productInfoService;
    
    /**
     * 折扣管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/discount/discountAdmin",method=RequestMethod.GET)
    public ModelAndView discountAdmin(HttpServletResponse response, HttpServletRequest request,
            ProductInfo productInfo, String msg,String menu) {
        ModelAndView mav = new ModelAndView("/discount/discountAdmin","menu",menu);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8;");
        
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
            logger.error("login fail."+this.getClass()+".discountAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/discount/discountAdminSearchList")
    public void discountAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String discountType,String pid, Integer offset, Integer limit) {

        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            param.put("discountType", discountType);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = discountInfoService.getCount(param);
            List<DiscountInfo> rows = discountInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".discountAdminSearchList " + e.toString());
        }
    }
    
    /**
     * 跳转折扣新增页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/discount/toAddDiscountAdmin",method=RequestMethod.GET)
    public ModelAndView toAddDiscountAdmin(HttpServletResponse response, HttpServletRequest request,
            ProductInfo productInfo,String menu) {
        ModelAndView mav = new ModelAndView("/discount/toAddDiscountAdmin","menu",menu);
        try {
            
            ProductInfo row = productInfoService.selectByPrimaryKey(productInfo);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddDiscountAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 折扣新增
     * @param response
     * @param request
     * @param menu
     * @param discountInfo
     * @return
     */
    @RequestMapping(value = "/discount/addDiscountAdmin",method=RequestMethod.POST)
    public ModelAndView addDiscountAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            DiscountInfo discountInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/discount/discountAdmin.html","menu",menu);
        mav.addObject("pid",discountInfo.getPid());
        try {
            String userName = "test1";
            discountInfo.setCtdt(new Date());
            discountInfo.setCtuser(userName);
            discountInfo.setDelFlg("0");
            if(discountInfoService.insert(discountInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addDiscountAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 跳转折扣修改页面
     * @param response
     * @param request
     * @param discountInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/discount/toUpdateDiscountAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateDiscountAdmin(HttpServletResponse response, HttpServletRequest request,
            DiscountInfo discountInfo,String menu) {
        ModelAndView mav = new ModelAndView("/discount/toUpdateDiscountAdmin","menu",menu);
        try {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setPid(discountInfo.getPid());
            ProductInfo res = productInfoService.selectByPrimaryKey(productInfo);
            mav.addObject("res",res);
            mav.addObject("row",discountInfoService.selectByPrimaryKey(discountInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateDiscountAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 折扣修改
     * @param response
     * @param request
     * @param menu
     * @param discountInfo
     * @return
     */
    @RequestMapping(value = "/discount/updateDiscountAdmin",method=RequestMethod.POST)
    public ModelAndView updateDiscountAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            DiscountInfo discountInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/discount/discountAdmin.html","menu",menu);
        mav.addObject("pid",discountInfo.getPid());
        try {
            String userName = "test2";
            discountInfo.setCtdt(new Date());
            discountInfo.setCtuser(userName);
            if(discountInfoService.updateByPrimaryKeySelective(discountInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateDiscountAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 折扣删除
     * @param response
     * @param request
     * @param menu
     * @param discountInfo
     */
    @RequestMapping(value = "/discount/deleteDiscountAdmin",method=RequestMethod.POST)
    public void deleteDiscountAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            DiscountInfo discountInfo) {
        try {
            discountInfo.setDelFlg("1");
            if(discountInfoService.updateByPrimaryKeySelective(discountInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteDiscountAdmin " + e.toString());

        }
    }
    
}
