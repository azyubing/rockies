package com.rockies.ec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.services.IProductInfoService;

@Controller
public class ProductController extends BaseController{
    
    @Autowired IProductInfoService productInfoService;

    /**
     * 商品列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/product/productAdminSearchList")
    public void productAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pname,
            String supplier_no,
            String ptype,
            String country,
            String continent,
            Integer distinctFlg,
            Long themeCatalogId,
            Long themeId,
            Integer offset, 
            Integer limit
            ) {
        
        try {

            Map<String,Object> param = new HashMap<>();
            param.put("pname", pname);
            param.put("supplier_no", supplier_no);
            param.put("ptype", ptype);
            param.put("country", country);
            param.put("continent", continent);
            param.put("distinctFlg", null==distinctFlg||distinctFlg==0?null:distinctFlg);
            param.put("themeCatalogId", themeCatalogId);
            param.put("themeId", themeId);
            param.put("offset", offset);            
            param.put("limit", limit);
            
            int total = productInfoService.getCount(param);
            List<Map<String,Object>> rows = productInfoService.selectProductInfoListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("productAdminSearchList fail. " + e.toString());
        }
    }
    
    
}
