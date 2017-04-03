package com.rockies.ec.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.rockies.ec.common.utils.Account;
import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.EmailUtil;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.vo.LoginInfoVO;

abstract class BaseController {

    @InitBinder  
    protected void initBinder(HttpServletRequest request,  
            ServletRequestDataBinder binder) throws Exception {  
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
            CustomDateEditor editor = new CustomDateEditor(df, false);  
            binder.registerCustomEditor(Date.class, editor);  
    }
    
    protected boolean getReturnErr(HttpServletResponse response, HttpServletRequest request,
            Map<String, Object> resMap) {
        boolean bool = false;
        Integer err = Integer.valueOf(resMap.get("err").toString());
        if (err > 0) {
            try {
                bool = true;
                response.getWriter().print(JSON.toJSONString(resMap, JSONProxy.SYSTEM_FEATURE));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }

    /**
     * 发送邮件
     * 
     * @param recevierEmail
     * @param siteAndStoreAdminInfo
     * @param err
     * @param isDefault
     */
    protected void sendEmail(String recevierEmail,
            Map<String, Map<String, Object>> siteAndStoreAdminInfo, int err, int isDefault,
            String rejectReason) {
        Map<String, Object> siteInfoMap = siteAndStoreAdminInfo.get("site");
        Map<String, Object> storeInfoMap = siteAndStoreAdminInfo.get("store");

        String adminSystemUrl = CommonUtils.getIPConfigBykey("testAddress");
        if (err == 0) {
            String title = "您的审核已经通过";
            String content = "<p>您的审核已经通过</p>";

            if (isDefault == 1) {
                content +=
                        "<p>商圈前端网站地址:" + siteInfoMap.get("url").toString() + "</p><p>管理后台网站地址:"
                                + adminSystemUrl + "</p>";
                content +=
                        "<p>商圈管理员账号:" + siteInfoMap.get("username").toString() + "</p><p>商圈管理员密码:"
                                + siteInfoMap.get("adminPasswd").toString() + "(默认密码,请尽快修改)</p>";
            }
            content +=
                    "<p>商户前端网站地址:" + storeInfoMap.get("url").toString() + "</p><p>管理后台网站地址:"
                            + adminSystemUrl + "</p>";
            content +=
                    "<p>商户管理员账号:" + storeInfoMap.get("username").toString() + "</p><p>商户管理员密码:"
                            + storeInfoMap.get("adminPasswd").toString() + "(默认密码,请尽快修改)</p>";

            EmailUtil.sendEmail(recevierEmail, title, content);
        } else {
            String title = "您的审核未通过";
            String content = "<p>您的审核未通过,请联系商圈管理员</p><br>原因如下：<br><p>" + rejectReason + "</p>";
            EmailUtil.sendEmail(recevierEmail, title, content);
        }
    }

    public Account getAccount(HttpServletRequest request) {
        return (Account) request.getSession().getAttribute("loginAccount");
    }

    public boolean isLogin(HttpServletRequest request) {
        return request.getSession().getAttribute("user_loginInfoVO") != null;
    }

    public int getAdminId(HttpServletRequest request) {
    	LoginInfoVO loginInfoVO = (LoginInfoVO) request.getSession().getAttribute("user_loginInfoVO");
    	return loginInfoVO.getLoginInfo().getLid();
	}
    /**
     * @param request
     * @return login administrator's site
     */
    @SuppressWarnings("unchecked")
    protected int getSite(HttpServletRequest request) {
        Map<String, Object> logInfoMap =
                (Map<String, Object>) request.getSession().getAttribute("loginInfo");
        Integer site = Integer.valueOf(logInfoMap.get("site").toString());
        return site;
    }

    /**
     *
     * @param request
     * @return login administrator's role
     */
    @SuppressWarnings("unchecked")
    protected int getRole(HttpServletRequest request) {
        Map<String, Object> logInfoMap =
                (Map<String, Object>) request.getSession().getAttribute("loginInfo");
        Integer role = Integer.valueOf(logInfoMap.get("role").toString());
        return role;
    }

    /**
     *
     * @param request
     * @return login administrator's username
     */
    @SuppressWarnings("unchecked")
    protected String getUsername(HttpServletRequest request) {
        Map<String, Object> logInfoMap =
                (Map<String, Object>) request.getSession().getAttribute("loginInfo");
        String username = logInfoMap.get("username").toString();
        return username;
    }

    /**
     *
     * @param request
     * @return login administrator's store
     */
    @SuppressWarnings("unchecked")
    protected int getStore(HttpServletRequest request) {
        Map<String, Object> logInfoMap =
                (Map<String, Object>) request.getSession().getAttribute("loginInfo");
        Integer store = Integer.valueOf(logInfoMap.get("store").toString());
        return store;
    }

    /**
     * @param request
     * @return login administrator's role type
     */
    protected String getRoleType(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("loginAccount");
        Preconditions.checkArgument(account != null);
        return account.getRoleType();
    }

    protected String processInvalidParam(HttpServletRequest request) {
        return null;
    }

    protected static Integer getPn(Integer pn) {
        return pn == null ? 1 : pn;
    }

    protected static Integer getPs(Integer ps) {
        return ps == null ? 10 : ps;
    }

    protected int getPn(Integer offset, Integer limit) {
        int pn = null == offset ? 1 : offset / (limit == null ? 10 : limit) + 1;
        return pn;
    }

    protected static int getPs(Integer offset, Integer limit) {
        int ps = null == limit ? 10 : limit;
        return ps;
    }

    protected final static Logger logger = LoggerFactory.getLogger("controller");
}
