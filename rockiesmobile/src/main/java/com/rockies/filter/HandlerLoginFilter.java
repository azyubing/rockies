package com.rockies.filter;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerLoginFilter implements HandlerInterceptor {

    private List<String> excludedUrls;
    
    private List<String> includedUrls;
    
    private List<String> rememberUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }


    public void setIncludedUrls(List<String> includedUrls) {
		this.includedUrls = includedUrls;
	}
    

	public void setRememberUrls(List<String> rememberUrls) {
		this.rememberUrls = rememberUrls;
	}


	@Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2,
            ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2)
            throws Exception {
//        String contextPath = req.getContextPath();
//        String requestURI = req.getRequestURI();
//        
//        String nextUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getRequestURI(); 
//       	if(req.getServerPort() == 80){
//       		nextUrl = req.getScheme()+"://"+req.getServerName()+req.getRequestURI(); 
//       	}
//       	if(req.getQueryString() != null && !"".equals(req.getQueryString())){
//       		nextUrl = nextUrl + "?"+req.getQueryString();
//       	}
//       	
//       	if(rememberUrls != null){
//	        for (String url : rememberUrls) {
//	            if (requestURI.endsWith(url)) {
//	            	req.setAttribute("nowUrl", URLEncoder.encode(nextUrl, "utf-8"));
//	            	break;
//	            }
//	        }
//        }
//
//        if(excludedUrls != null){
//	        for (String url : excludedUrls) {
//	            if (requestURI.endsWith(url)) {
//	                return true;
//	            }
//	        }
//        }
//        
//        if(includedUrls!=null){
//        	boolean fillter = true;
//        	for (String url : includedUrls) {
//	            if (requestURI.endsWith(url)) {
//	            	fillter = false;
//	            	break;
//	            }
//	        }
////        	if(fillter){
////        		return true;
////        	}
//        }
//
        HttpSession session = req.getSession(true);
         //从session 里面获取用户名的信息
        Object obj = session.getAttribute("loginUser");
//         //判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
//        if (obj == null || "".equals(obj.toString())) {
//            //res.sendRedirect(contextPath + "/tologin.html?nextUrl="+URLEncoder.encode(nextUrl, "utf-8"));
//        	res.sendRedirect(contextPath + "/home.html");
//            return false;
//        }
        return true;
    }
}
