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
import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.model.City;
import com.rockies.ec.model.Evaluation;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IEvaluationService;


@Controller
public class EvaluationAndConsultingController extends BaseController{

	@Autowired
	private IEvaluationService evaluationService;
	@Autowired
	private ICityService cityService;
    /**
     * 评论管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/evaluation/evaluationAdmin")
    public ModelAndView bannerAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/evaluation/evaluationAdmin","menu",menu);
    }
    /**
     * 评论列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/evaluation/evaluationAdminSearchList")
    public void bannerAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        try {
        	String customerName = request.getParameter("customerName");
        	String productName = request.getParameter("productName");
        	String productType = request.getParameter("productType");
        	String minCore = request.getParameter("minCore");
        	String maxCore = request.getParameter("maxCore");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("customerName", customerName);
        	map.put("productName", productName);
        	map.put("productType", productType);
        	map.put("minCore", minCore);
        	map.put("maxCore", maxCore);
        	
            List<Map<String,Object>> rows = evaluationService.getAllListMap(map);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total",evaluationService.getCount(map));
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
    }
    
    /**
     * 评论回复
     * @param response
     * @param request
     * @param reviewId
     * @param menu
     * @return
     */
    @RequestMapping(value = "/evaluation/evaluation")
    public ModelAndView storeConsulting(HttpServletResponse response, HttpServletRequest request,
            Integer reviewId,String menu) {
        ModelAndView retView = new ModelAndView("/evaluation/evaluation");
        retView.addObject("menu", menu);
        try {
            String retStr = "{\"data\":{\"createTime\":1439454584000,\"details\":[{\"content\":\"请问这个东西好用吗8/13\",\"createTime\":1439454584000,\"detailId\":156,\"parentDetailId\":0,\"source\":1,\"status\":1,\"title\":\"咨询\",\"type\":2,\"userAccount\":\"匿名用户\",\"userId\":77},{\"content\":\"a  b   c\",\"createTime\":1439454626000,\"detailId\":157,\"parentDetailId\":156,\"source\":15,\"status\":1,\"title\":\"\",\"type\":2,\"userAccount\":\"lyf@site\",\"userId\":260}],\"orderId\":0,\"productId\":499,\"productName\":\"SONY平板电视1\",\"reviewId\":119,\"site\":61,\"siteCode\":\"site1435231217676\",\"store\":86,\"storeCode\":\"store1435231217869\",\"userAccount\":\"匿名用户\",\"userId\":77},\"err\":0,\"msg\":\"success\"}";
            Map<String, Object> retMap = JSON.parseObject(retStr);
            retMap = (Map<String, Object>) retMap.get("data");
            Long time = (Long) retMap.get("createTime");
            String timeStr = CommonUtils.getTimeStampToDateString(time);
            retMap.put("createTime", timeStr);
            retView.addObject("cMap", retMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return retView;

    }
    
    /**
     * 选择商品评论
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/evaluation/evaluationProductAdmin")
    public ModelAndView evaluationProductAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {
        	
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
        	request.setAttribute("cityList", cityList);
        	
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("/evaluation/evaluationProductAdmin","menu",menu);
    }
    
    /**
     * 新增or修改评论
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/evaluation/saveOrUpdateEvaluation")
    public void saveOrUpdateEvaluation(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("evaluation") Evaluation evaluation) {
        try {
        	if(evaluation.getAccountnumber()==null||evaluation.getAccountnumber().isEmpty()){
        		evaluation.setAccountnumber("匿名用户");
        	}
        	if(evaluation.getId()==0){
        		evaluation.setCtuser(getAdminId(request)+"");
        		evaluation.setCtdt(new Date());
        		evaluationService.insert(evaluation);
        		response.getWriter().print("success");
        	}else{
        		evaluation.setUpdt(new Date());
        		evaluation.setUpuser(getAdminId(request)+"");
        		evaluationService.updateObject(evaluation);
        		response.getWriter().print("success");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateEvaluation fail. " + e.toString());
        }
    }
    
    /**
     * 删除评论
     * @param response
     * @param request
     * @param menu
     * @return
     */
    @RequestMapping(value = "/evaluation/deleteEvaluation")
    public void deleteEvaluation(HttpServletResponse response, HttpServletRequest request,int id) {
        try {
        	evaluationService.deleteByPrimaryKey(id);
        	response.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteEvaluation fail. " + e.toString());
        }
    }
    
}
