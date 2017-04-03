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
import com.rockies.ec.model.PayBean;
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.model.SuppliersContractorBean;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IPayService;
import com.rockies.ec.services.ISuppliersContractorService;
import com.rockies.ec.services.ISuppliersService;
import com.rockies.ec.vo.SuppliersVO;

@Controller
public class SuppliersController extends BaseController{
    
	@Autowired
	private ICityService cityService;
	@Autowired
	private IPayService payService;
	@Autowired
	private ISuppliersService suppliersService;
	@Autowired
	private ISuppliersContractorService suppliersContractorService;
	
	 /**
     * 供应商管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/supplier/contractorAdmin")
    public ModelAndView contractorAdmin(HttpServletResponse response, HttpServletRequest request,String menu,String sid) {
        try {
			SuppliersVO suppliersBean = suppliersService.getSuppliersVO(Integer.parseInt(sid));
			request.setAttribute("supplier", suppliersBean);
        	request.setAttribute("menu", menu);
        	request.setAttribute("sid", sid);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("contractorAdmin fail. " + e.toString());
        }
        return new ModelAndView("/supplier/contractorAdmin");
    }
	 /**
     * 供应商联系人列表
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/supplier/suppliersContractorSearch")
    public void suppliersContractorSearch(HttpServletResponse response, HttpServletRequest request,String menu,Integer offset, Integer limit) {
         
         try {
        	 int sid = Integer.parseInt(request.getParameter("sid"));
			String c_name = request.getParameter("c_name");
			Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("c_name", c_name);
			map.put("sid", sid);
			List<Map<String, Object>> rows = suppliersContractorService.getSuppliersContractorBeanList(map);
			int total = suppliersContractorService.getCount(map);
			Map<String, Object> reslutMap = new HashMap<>();
			reslutMap.put("rows", rows);
			reslutMap.put("total", total);
			response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("suppliersContractorSearch fail. " + e.toString());
        }
    }
    /**
     * 添加or修改联系人列表
     * @param response
     * @param request
     * @param menu
     * @return
     */
	@RequestMapping(value = "/supplier/saveOrUpdateSuppliersContractor")
    public void saveOrUpdateSuppliersContractor(HttpServletResponse response, HttpServletRequest request,String menu,String sid,@ModelAttribute("suppliersContractorBean") SuppliersContractorBean suppliersContractorBean) {
		try {
         	if(suppliersContractorBean.getCid()==0){
         		suppliersContractorBean.setCtdt(new Date());
         		suppliersContractorBean.setCtuser(getAdminId(request)+"");
         		suppliersContractorService.save(suppliersContractorBean);
         		response.getWriter().print("success");
         	}else{
         		suppliersContractorBean.setUpuser(getAdminId(request)+"");
         		suppliersContractorBean.setUpdt(new Date());
         		suppliersContractorService.update(suppliersContractorBean);
         		response.getWriter().print("success");
         	}
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateSuppliersContractor fail. " + e.toString());
        }
    }
	
	/**
     * 编辑联系人
     * @param response
     * @param request
     * @param menu
     * @return
     */
	@RequestMapping(value = "/supplier/editSuppliersContractor")
    public void editSuppliersContractor(HttpServletResponse response, HttpServletRequest request,String menu) {
         try {
        	 String cid = request.getParameter("cid");
        	 if(cid!=null&&!cid.isEmpty()){
        		SuppliersContractorBean suppliersContractorBean = suppliersContractorService.getSuppliersContractor(Integer.parseInt(cid));
     			response.getWriter().print(JSON.toJSON(suppliersContractorBean).toString());
        	 }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("editSuppliersContractor fail. " + e.toString());
        }
    }
	
	/**
     * b编辑联系人
     * @param response
     * @param request
     * @param menu
     * @return
     */
	@RequestMapping(value = "/supplier/deleteSuppliersContractor")
    public void deleteSuppliersContractor(HttpServletResponse response, HttpServletRequest request,String menu) {
         try {
        	 String cid = request.getParameter("cid");
        	 suppliersContractorService.deleteByCid(Integer.parseInt(cid));
        	 response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("editSuppliersContractor fail. " + e.toString());
        }
    }
	
    /**
     * 供应商管理
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/supplier/supplierAdmin")
    public ModelAndView supplierAdmin(HttpServletResponse response, HttpServletRequest request,String menu) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("supplierAdmin fail. " + e.toString());
        }
        return new ModelAndView("/supplier/supplierAdmin","menu",menu);
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/supplier/supplierAdminSearchList")
    public void supplierAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        
        try {
        	String s_name = request.getParameter("s_name");
        	Map map = new HashMap();
        	map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("s_name", s_name);
            List<Map<String,Object>> rows = suppliersService.getSuppliersBeanList(map);
            int total = suppliersService.getCount(map);
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("supplierAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 新增供应商
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/supplier/addSupplier")
    public ModelAndView addSupplier(HttpServletResponse response, HttpServletRequest request,String menu) {
        try {
        	String sid = request.getParameter("sid");
        	if(sid!=null){
        		SuppliersVO suppliersBean = suppliersService.getSuppliersVO(Integer.parseInt(sid));
        		request.setAttribute("supplier", suppliersBean);
        	}
        	//国家
        	List<City> cityList = cityService.getCityTwo();
        	List<PayBean> payList = payService.getPayList();
        	
        	request.setAttribute("cityList", cityList);
        	request.setAttribute("payList", payList);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("addSupplier page fail. " + e.toString());
        }
        return new ModelAndView("/supplier/addSupplier","menu",menu);
    }
    
    /**
     * 保存或修改供应商
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/supplier/addOrUpdateSupplier")
    public ModelAndView addOrUpdateSupplier(HttpServletResponse response, HttpServletRequest request,String menu,@ModelAttribute("suppliersBean") SuppliersBean suppliersBean) {
        try {
        	if(suppliersBean.getSid()==0){
        		suppliersBean.setCtdt(new Date());
        		suppliersBean.setCtuser(getAdminId(request)+"");
        		suppliersService.save(suppliersBean);
        	}else {
        		suppliersBean.setUpdt(new Date());
        		suppliersBean.setUpuser(getAdminId(request)+"");
        		suppliersService.update(suppliersBean);
			}
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("addOrUpdateSupplier fail. " + e.toString());
        }
        return new ModelAndView("redirect:/supplier/supplierAdmin.html","menu",menu);
    }
    

}
