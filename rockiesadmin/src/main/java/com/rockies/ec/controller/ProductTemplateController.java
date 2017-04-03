package com.rockies.ec.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.services.IProductTemplateService;

@Controller
public class ProductTemplateController extends BaseController{

    @Autowired IProductTemplateService productTemplateService;
    
    /**
     * 产品描述查询
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/template/productTemplateSearch")
    public void ProductTemplateSearch(HttpServletResponse response, HttpServletRequest request,
            String ptypeCode,String ptype) {
        
        try {
            Map<String,Object> param = new HashMap<>(2);
            param.put("ptypeCode", ptypeCode);
            param.put("ptype", ptype);
            
            Map<String,Object> row = productTemplateService.selectByIdAndPtype(param);
            response.getWriter().print(JSON.toJSONString(row, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("ProductTemplateSearch fail. " + e.toString());
        }
    }
}
