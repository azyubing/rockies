package com.rockies.ec.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.model.LoginInfo;
import com.rockies.ec.model.RoleInfo;
import com.rockies.ec.model.RolePermission;
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.services.ICustomerService;
import com.rockies.ec.services.ILoginInfoService;
import com.rockies.ec.services.IPermissionInfoService;
import com.rockies.ec.services.IRoleInfoService;
import com.rockies.ec.services.IRolePermissionService;
import com.rockies.ec.services.IRouteOrderService;
import com.rockies.ec.services.ISuppliersService;
import com.rockies.ec.vo.LoginInfoVO;
import com.rockies.ec.vo.PermissionInfoVO;


@Controller
public class AuthcController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthcController.class);

    @Autowired
    private IRoleInfoService roleInfoService;
    @Autowired
    private ILoginInfoService loginInfoService;
    
    @Autowired
    private ISuppliersService suppliersService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IRouteOrderService routeOrderService;
    @Autowired
    private IPermissionInfoService permissionInfoService;
    @Autowired
    private IRolePermissionService rolePermissionService;
    /**
     * 未完成的页面,尽请期待
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/await")
    public ModelAndView await(HttpServletResponse response, HttpServletRequest request) {

        return new ModelAndView("await");
    }

    /**
     * 后台首页
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletResponse response, HttpServletRequest request) {
        try {
        	int supplierCount = suppliersService.getCount(null);
        	long customerCount = customerService.getCount(null);
        	long orderCompleteCount = routeOrderService.getRouteStatusCount("5");
        	long orderDealCount = routeOrderService.getRouteStatusCount("2");
        	request.setAttribute("supplierCount", supplierCount);
        	request.setAttribute("customerCount", customerCount);
        	request.setAttribute("orderCompleteCount", orderCompleteCount);
        	request.setAttribute("orderDealCount", orderDealCount);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("home fail. " + e.toString());
        }
        return new ModelAndView("home");
    }

    /**
     * 到登入页面
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/tologin")
    public ModelAndView toLogin(HttpServletResponse response, HttpServletRequest request) {
        try {

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("login");
    }
    
	/**
	 * 到登入页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/loginOut")
	public ModelAndView loginOut(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Enumeration em = request.getSession().getAttributeNames();
			while (em.hasMoreElements()) {
				request.getSession().removeAttribute(em.nextElement().toString());
			}
		} catch (Exception e) {
			if (null != e.getMessage()) {
				System.out.println(e.toString());
			}
			logger.info("login fail. " + e.toString());
		}
		return new ModelAndView("redirect:/tologin.html");
	}

    /**
     * 用户登录
     * 
     * @param loginName
     * @param password
     * @param response
     * @param request
     * @return
     */
    @SuppressWarnings({ "unused" })
	@RequestMapping(value = "/login")
    public ModelAndView login(String loginName, String password,HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
        	logger.info("loginName: " + loginName + " login to system");
        	if(!CommonUtils.isNullOrEmpty(loginName)&&!CommonUtils.isNullOrEmpty(password)){
        		resMap.put("loginName", loginName);
        		resMap.put("password", CommonUtils.covertpw(password));
        		boolean bool = loginInfoService.login(resMap);
        		if(!bool){//用户名密码错误
        			resMap = new HashMap<>(1);
            		resMap.put("err", 500001);
            		return new ModelAndView("login", "resMap", resMap);
        		}else{
        			LoginInfoVO loginInfoVO = loginInfoService.getLoginInfo(resMap);
        			List<PermissionInfoVO> permissionInfoVOList = permissionInfoService.loginRolePermission(loginInfoVO.getLoginInfo().getLid(),loginInfoVO.getRoleId());
        			loginInfoVO.setPermissionInfoVOList(permissionInfoVOList);
        			request.getSession().setAttribute("user_loginInfoVO", loginInfoVO);
        		}
        	}else{//用户名或密码不能为空
        		resMap = new HashMap<>(1);
        		resMap.put("err", 500010);
        		return new ModelAndView("login", "resMap", resMap);
        		
        	}
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("login fail. " + e.toString());
        }
        return new ModelAndView("redirect:home.html");
    }
    
    /**
     * 后台用户管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/userAdmin")
    public ModelAndView userAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {
        	List<RoleInfo> roleInfoList = roleInfoService.getAllList();
        	List<SuppliersBean> suppliersBeanList = suppliersService.getAllList();        	
        	request.setAttribute("suppliersBeanList", suppliersBeanList);
        	request.setAttribute("roleInfoList", roleInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("userAdmin fail. " + e.toString());
        }
        return new ModelAndView("/user/userAdmin","menu",menu);
    }
    
    /**
     * 用户列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/user/userAdminSearchList")
    public void userAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        try {
        	String loginName = request.getParameter("loginName");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("loginName", loginName);
            List<Map<String,Object>> rows = loginInfoService.getAllListMap(map);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", rows.size());
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("userAdminSearchList fail. " + e.toString());
        }
    }
    
    /**
     * 新增or修改用户
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
	@RequestMapping(value = "/user/saveOrUpdateuserAdmin")
    public void saveOrUpdateuserAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("loginInfoVO")LoginInfoVO loginInfoVO ) {
        try {
        	if(loginInfoVO.getLoginInfo().getLid()==0){
        		String password = loginInfoVO.getLoginInfo().getPassword();
        		String md5password = CommonUtils.covertpw(password);
        		loginInfoVO.getLoginInfo().setPassword(md5password);
        		loginInfoVO.getLoginInfo().setCtdt(new Date());
        		loginInfoVO.getLoginInfo().setCtuser(getAdminId(request)+"");
        		loginInfoService.saveLoginInfo(loginInfoVO);
        		response.getWriter().print("success");
        	}else{
        		loginInfoVO.getLoginInfo().setUpdt(new Date());
        		loginInfoVO.getLoginInfo().setUpuser(getAdminId(request)+"");
        		loginInfoService.updateLoginInfo(loginInfoVO);
        		response.getWriter().print("success");
        	}

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateuserAdmin fail. " + e.toString());
        }
    }
	
	/**
	 * 修改密码
	 * 
	 * @param response
	 * @param request
	 * @param loginInfo
	 */
	@RequestMapping(value = "/user/updatePassword")
	public void updatePassword(HttpServletResponse response,HttpServletRequest request,@ModelAttribute("loginInfo") LoginInfo loginInfo) {
		try {
			loginInfo.setPassword(CommonUtils.covertpw(loginInfo.getPassword()));
			loginInfo.setUpdt(new Date());
			loginInfo.setUpuser(getAdminId(request) + "");
			loginInfoService.updateObject(loginInfo);
			response.getWriter().print("success");

		} catch (Exception e) {
			if (null != e.getMessage()) {
				System.out.println(e.toString());
			}
			logger.info("updatePassword fail. " + e.toString());
		}
	}
	
	
	
	/**
	 * 删除用户
	 * @param response
	 * @param request
	 * @param lid
	 */
	@RequestMapping(value = "/user/checkuserAdmin")
    public void checkuserAdmin(HttpServletResponse response, HttpServletRequest request,String loginName) {
        try {
        	boolean bool = loginInfoService.checkLoginName(loginName);
        	response.getWriter().print(bool+"");
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("checkuserAdmin fail. " + e.toString());
        }
    }
	
	/**
	 * 删除用户
	 * @param response
	 * @param request
	 * @param lid
	 */
	@RequestMapping(value = "/user/deleteuserAdmin")
    public void deleteuserAdmin(HttpServletResponse response, HttpServletRequest request,int lid) {
        try {
        	loginInfoService.deleteByPrimaryKey(lid);
        	response.getWriter().print("success");
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteuserAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 角色管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/role/roleAdmin")
    public ModelAndView roleAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("roleAdmin fail. " + e.toString());
        }
        return new ModelAndView("/role/roleAdmin","menu",menu);
    }
    /**
     * 角色列表
     * @param response
     * @param request
     * @param offset
     * @param limit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/role/roleAdminSearchList")
    public void roleAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            Integer offset, Integer limit) {
        
        try {
        	String roleName = request.getParameter("roleName");
        	Map map = new HashMap();
			map.put("offset", offset);
        	map.put("limit", limit);
        	map.put("roleName", roleName);
            List<Map<String,Object>> rows = roleInfoService.getRoleInfoList(map);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", rows.size());
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("roleAdminSearchList fail. " + e.toString());
        }
    }
    /**
     * 增加或修改角色
     * @param response
     * @param request
     * @param roleInfo
     */
	@RequestMapping(value = "/role/saveOrUpdateroleAdmin")
    public void saveOrUpdateroleAdmin(HttpServletResponse response, HttpServletRequest request,@ModelAttribute("roleInfo") RoleInfo roleInfo) {
        try {
        	if(roleInfo.getRoleId()==null){
        		roleInfo.setCtdt(new Date());
        		roleInfo.setCtuser(getAdminId(request)+"");
        		roleInfoService.saveRoleInfo(roleInfo);
        		response.getWriter().print("success");
        	}else{
        		roleInfo.setUpuser(getAdminId(request)+"");
        		roleInfo.setUpdt(new Date());
        		roleInfoService.updateRoleInfo(roleInfo);
        		response.getWriter().print("success");
        	}

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveOrUpdateroleAdmin fail. " + e.toString());
        }
    }
	
	/**
     * 删除角色
     * @param response
     * @param request
     * @param roleInfo
     */
	@RequestMapping(value = "/role/deleteroleAdmin")
    public void deleteroleAdmin(HttpServletResponse response, HttpServletRequest request,String roleId) {
        try {
        	roleInfoService.deleteRoleInfo(roleId);
    		response.getWriter().print("success");

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("deleteroleAdmin fail. " + e.toString());
        }
    }
    
    /**
     * 权限配置
     * @param response
     * @param request
     * @param role
     * @param roleName
     * @param roleType
     * @return
     */
    @RequestMapping(value = "/role/roleMenuList")
    public ModelAndView roleMenuList(HttpServletResponse response, HttpServletRequest request,String roleId,String menu) {
        ModelAndView retView = new ModelAndView("/role/roleMenuList");
        //角色
        RoleInfo roleInfo = roleInfoService.getRoleInfo(roleId);
        //权限菜单
        List<PermissionInfoVO> permissionInfoVOList = permissionInfoService.getTwoMenu();
        //拥有的权限
       List<RolePermission> rolePermissionList = rolePermissionService.getRolePermissionList(roleId);
        
        retView.addObject("roleInfo", roleInfo);
        retView.addObject("permissionInfoVOList", permissionInfoVOList);
        retView.addObject("rolePermissionList", rolePermissionList);
        retView.addObject("menu", menu);
        return retView;
    }

    /**
     * 添加权限
     * @param response
     * @param request
     * @param roleInfo
     */
	@RequestMapping(value = "/role/saveRolePermission")
    public void saveRolePermission(HttpServletResponse response, HttpServletRequest request,String rolePermissionList) {
        try {
        	String[] rolePermissionStr = rolePermissionList.split("=");
        	List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        	for(String s:rolePermissionStr){
        		RolePermission rolePermission = new RolePermission();
        		String[] str = s.split(",");
        		rolePermission.setPid(str[0]);
        		rolePermission.setIsCheck(Integer.parseInt(str[1]));
        		rolePermission.setRoleId(str[2]);
        		rolePermissions.add(rolePermission);
        	}
        	rolePermissionService.saveRolePermission(rolePermissions);
        	response.getWriter().print("success");
        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.info("saveRolePermission fail. " + e.toString());
        }
    }
    
}
