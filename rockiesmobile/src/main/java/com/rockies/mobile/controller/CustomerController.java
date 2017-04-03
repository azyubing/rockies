package com.rockies.mobile.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rockies.common.utils.CommonUtils;
import com.rockies.common.utils.WeiChatUtil;
import com.rockies.mobile.constants.Constants;
import com.rockies.mobile.model.OrderItem;
import com.rockies.mobile.utils.DBUtils;
import com.rockies.mobile.utils.Utils;
import com.rockies.model.City;
import com.rockies.model.Collection;
import com.rockies.model.EntPriceInfo;
import com.rockies.model.Option;
import com.rockies.model.PackagePriceInfo;
import com.rockies.model.RoomPriceInfo;
import com.rockies.model.RouteOrder;
import com.rockies.model.TrafficCharteredPriceInfo;
import com.rockies.model.User;
import com.rockies.model.VillaDetailPriceInfo;
import com.rockies.services.ICityService;
import com.rockies.services.ICollectionService;
import com.rockies.services.IEntPriceInfoService;
import com.rockies.services.IPackagePriceInfoService;
import com.rockies.services.IRoomPriceInfoService;
import com.rockies.services.IRouteOrderService;
import com.rockies.services.ITrafficCharteredPriceInfoService;
import com.rockies.services.IUserService;
import com.rockies.services.IVillaDetailPriceInfoService;
import com.rockies.vo.RouteOrderVO;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IRouteOrderService routeOrderService;
	@Autowired
	private ICollectionService collectionService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IPackagePriceInfoService packagePriceInfoService;
	@Autowired
	private IRoomPriceInfoService roomPriceInfoService;
	@Autowired
	private IVillaDetailPriceInfoService villaDetailPriceInfoService;
	@Autowired
	private IEntPriceInfoService entPriceInfoService;
	@Autowired
	private ITrafficCharteredPriceInfoService trafficCharteredPriceInfoService;

	@RequestMapping(value = "/mobile/loginStatus", method = RequestMethod.GET)
	public void checkLoginStatus(HttpServletResponse response, HttpServletRequest request) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().write(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			} else {
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.loginSuccess));
				return;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletResponse response, HttpServletRequest request) {
		return new ModelAndView("redirect:/index.html");
	}

	@RequestMapping(value = "/mobile/beforeWXPay", method = RequestMethod.GET)
	public ModelAndView beforeWXPay(HttpServletResponse response, HttpServletRequest request, String channel,
			double amount, String orderNo) {
		try {
			WeiChatUtil wchat = new WeiChatUtil();
			String url = "{0}?channel={1}&amount={2}&orderNo={3}";
			String callbackURL = URLEncoder.encode(MessageFormat.format(url,
					CommonUtils.getInterfaceConfigBykey("wei_beforepay_callback"), channel, amount, orderNo),
					Constants.UTF8);
			String redirectURL = wchat.getOAuth2BaseURL(CommonUtils.getInterfaceConfigBykey("wei_appid"), callbackURL);
			return new ModelAndView("redirect:" + redirectURL);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/mobile/wchatlogin", method = RequestMethod.GET)
	public ModelAndView wchatlogin(HttpServletResponse response, HttpServletRequest request, String code,
			String state) {
		String viewname = "redirect:/index.html";
		try {
			WeiChatUtil wchat = new WeiChatUtil();
			if (null == code) {
				String callbackURL = URLEncoder.encode("http://m.rockies.cn/mobile/wchatlogin.do", Constants.UTF8);
				String redirectURL = wchat.getOAuth2UserInfoURL(CommonUtils.getInterfaceConfigBykey("wei_appid"),
						callbackURL);
				return new ModelAndView("redirect:" + redirectURL);
			}
			// wei_appid=wxa4a9620e264e143f
			// wei_appsecret=d4624c36b6795d1d99dcf0547af5443d
			Map<String, Object> seMap = wchat.getOauth2Info(CommonUtils.getInterfaceConfigBykey("wei_appid"),
					CommonUtils.getInterfaceConfigBykey("wei_appsecret"), code);
			/**
			 * weixin will return data like below { "access_token" :
			 * "l9stljNDcbvu94vAoqITmkkxYJP99qrgEYUrLp0QBam1q2wSkSVaQGxt_e9DzMuaGxvQ820yXI4mOMIxekLgbsHs0K4utMMDqEdMN2fIYk8",
			 * "expires_in" : 7200, "refresh_token" :
			 * "KU_7pmubiPXVbp9ctqlczY35jrw9wB5FBXdeTL5mg-GSOiwCtZ9bNqJQ2tZIa6tFlzd8uXbpDrfZ6pNJjjnJrR8bY9rehEvuP92GaXECTkU",
			 * "openid" : "oN3LqwCq81_y9eQVVw6OgYyy0F7Q", "scope" :
			 * "snsapi_login", "unionid" : "o6ETEwExJg8VRC_vERO4iw8hdDsc" };
			 */
			if (seMap.get("access_token") == null || seMap.get("openid") == null) {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "微信登录不成功，请重新扫码登录！"));
				return new ModelAndView(viewname);
			}

			String openid = (String) seMap.get("openid");

			request.getSession().removeAttribute(Constants.USER);
			// String openid = "oTrrpwnC5qmkYsjiJOGGo7-WpP4s";
			// 如果以用微信登陆过，则直接用openid登录
			User user = userService.weixinLogin(openid);
			if (user == null) {
				String accessToken = (String) seMap.get("access_token");
				Map<String, Object> weixinUser = wchat.getUserInfo(accessToken, openid);
				/**
				 * {"openid":"oN3LqwCq81_y9eQVVw6OgYyy0F7Q","nickname":"çå¼º",
				 * "sex":1,"language":"zh_CN","city":"Dalian","province":
				 * "Liaoning", "country":"CN","headimgurl":
				 * "http://wx.qlogo.cn/mmopen/zkrJPm0JTzBvk62IaXtDUl7NPRQaLicy8oShkXSgeAbzyXFoibDZIdLs9ak4KoIg9iceOxGUSv8TzfEAm2ibuhh5YKpuSNgmXfmO/0"
				 * ,"privilege":[],"unionid":"o6ETEwExJg8VRC_vERO4iw8hdDsc"}
				 */
				// 第一次使用微信登陆
				user = new User();
				user.setOpenid(openid);
				user.setUsername(openid);
				user.setPassword(CommonUtils.covertpw((String) weixinUser.get("unionid")));
				user.setUnionid((String) weixinUser.get("unionid"));
				user.setRealName(Utils.getStringValue(weixinUser.get("nickname")));
				user.setWeiname(Utils.getStringValue(weixinUser.get("nickname")));
				user.setCountryName(Utils.getStringValue(weixinUser.get("country")));
				user.setCityName(Utils.getStringValue(weixinUser.get("city")));
				user.setProvinceName(Utils.getStringValue(weixinUser.get("province")));
				user.setPhoto((String) weixinUser.get("headimgurl"));
				user.setCtdt(new Date());
				userService.saveUser(user);
				user = userService.getUserByUserName(openid);
				user.setUsername(user.getWeiname());
			}
			request.getSession().setAttribute(Constants.USER, user);
			return new ModelAndView(viewname);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			try {
				logger.error(e.getMessage(), e);
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, e.getMessage()));
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
		return null;
	}

	/**
	 * login for username and mobile number
	 * 
	 * @param response
	 * @param request
	 * @param loginName
	 * @param pw
	 * @param validateCode
	 */
	@RequestMapping(value = "/mobile/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response, String loginName, String password) {
		try {
			if (loginName != null && !loginName.equals("")) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				if (Utils.isValid(loginName, "1[0-9]{10}", false)) {
					// 手机登陆验证
					resMap.put("tel", loginName);
					resMap.put("password", CommonUtils.covertpw(password));
				} else {
					// 用户名验证
					resMap.put("username", loginName);
					resMap.put("password", CommonUtils.covertpw(password));
				}
				User user = userService.login(resMap);
				if (user == null) {
					response.getWriter().print(
							Utils.getReturnedData(Constants.LoginFAILED, null, Constants.userNotExistOrWrongPwd));
				} else if (user.getStatus() == 0) {
					request.getSession().setAttribute(Constants.USER, user);
					response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.loginSuccess));
				} else if (user.getStatus() == 2) {
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.activateUser));
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/username", method = RequestMethod.POST)
	public void updateUserName(HttpServletRequest request, HttpServletResponse response, String loginName) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().write(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			if (loginName != null && !loginName.equals("")) {
				user.setRealName(loginName);
				userService.modifyUser(user);
				request.getSession().setAttribute(Constants.USER, user);
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.updateSuccess));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			try {
				logger.error(e.getMessage(), e);
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.updateFailed));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@RequestMapping(value = "/mobile/cellPhoneNumber", method = RequestMethod.POST)
	public void updateCellPhoneNumber(HttpServletRequest request, HttpServletResponse response, String cellPhoneNumber,
			String validateCode) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().write(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}

			if (!StringUtils.isEmpty(cellPhoneNumber) && !userService.telExit(cellPhoneNumber.trim())) {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "该手机号以被注册，请更换手机号"));
				return;
			}

			String code = (String) request.getSession().getAttribute(Constants.CheckCode);
			long currentTimeMillis = (long) request.getSession().getAttribute(Constants.CurrentTime);
			long now = System.currentTimeMillis();
			if ((currentTimeMillis + 60 * 1000) > now) {
				if (code != null && code.equalsIgnoreCase(validateCode)) {
					user.setTel(cellPhoneNumber);
					userService.modifyUser(user);
					request.getSession().setAttribute(Constants.USER, user);
					response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.updateSuccess));
					return;
				} else {
					response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.VerifyFailed));
					return;
				}
			} else {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.VerifyCodeExpired));
				return;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			try {
				logger.error(e.getMessage(), e);
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.updateFailed));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@RequestMapping(value = "/mobile/password", method = RequestMethod.POST)
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, String password) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().write(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			if (password != null && !password.equals("")) {
				user.setPassword(CommonUtils.covertpw(password));
				userService.modifyUser(user);
				request.getSession().setAttribute(Constants.USER, user);
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.updateSuccess));
			} else {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, user, Constants.ErrorPassword));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			try {
				logger.error(e.getMessage(), e);
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, Constants.updateFailed));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@RequestMapping(value = "/mobile/photo", method = RequestMethod.POST)
	public void updatePhoto(HttpServletRequest request, HttpServletResponse response, String photoUrl) {
		User user = Utils.getUserInstance(request);
		try {
			if (null == user) {
				response.getWriter().write(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			if (photoUrl != null && !photoUrl.equals("")) {
				user.setPhoto(photoUrl);
				userService.modifyUser(user);
				request.getSession().setAttribute(Constants.USER, user);
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.updateSuccess));
			} else {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, user, Constants.ErrorPhoto));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			try {
				logger.error(e.getMessage(), e);
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, user, Constants.updateFailed));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * logout, now not be used
	 * 
	 * @param request
	 * @param response
	 * @param loginName
	 */
	@RequestMapping(value = "/mobile/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response, String loginName) {
		try {
			if (loginName != null && !loginName.equals("")) {
				User user = Utils.getUserInstance(request);
				if (user == null) {
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "用户已经注销!"));
					return;
				}
				response.addHeader("Access-Control-Allow-Origin", "*");
				if (user.getUsername().equalsIgnoreCase(loginName)) {
					request.getSession().setAttribute(Constants.USER, null);
					response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, "注销成功!"));
				} else {
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "你还未登录，无法注销!"));
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 注册用户
	 * 
	 * @param response
	 * @param request
	 * @param username
	 * @param password
	 * @param telNum
	 */
	@RequestMapping(value = "/mobile/user", method = RequestMethod.POST)
	public void register(HttpServletResponse response, HttpServletRequest request, String username, String password,
			String telNum, String validateCode) {
		try {

			// 验证存在
			if (!userService.userNameExit(username.trim())) {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "用户名已存在，请更换用户名"));
				return;
			}

			if (!StringUtils.isEmpty(telNum) && !userService.telExit(telNum.trim())) {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "该手机号以被注册，请更换手机号"));
				return;
			}

			String code = (String) request.getSession().getAttribute(Constants.CheckCode);
			long currentTimeMillis = (long) request.getSession().getAttribute(Constants.CurrentTime);
			long now = System.currentTimeMillis();
			if ((currentTimeMillis + 60 * 1000) > now) {
				if (code != null && code.equalsIgnoreCase(validateCode)) {
					User user = new User();
					user.setUsername(username);
					user.setPassword(CommonUtils.covertpw(password));
					user.setTel(telNum);
					user.setCtdt(new Date());
					userService.saveUser(user);
					user = userService.getUserByUserName(username);
					request.getSession().setAttribute(Constants.USER, user);
					response.getWriter()
							.print(Utils.getReturnedData(Constants.SUCCESS, user, Constants.UserRegisteredSuccess));
					return;
				} else {
					response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.VerifyFailed));
					return;
				}
			} else {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.VerifyCodeExpired));
				return;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "系统繁忙，请稍后重试"));
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e);
			}
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param response
	 * @param request
	 * @param password
	 *            : 新密码
	 * @param telNum
	 * @param validateCode
	 */
	@RequestMapping(value = "/mobile/resetPassword", method = RequestMethod.POST)
	public void resetPassword(HttpServletResponse response, HttpServletRequest request, String telNum, String password,
			String validateCode) {
		try {
			String code = (String) request.getSession().getAttribute(Constants.CheckCode);
			long currentTimeMillis = (long) request.getSession().getAttribute(Constants.CurrentTime);
			long now = System.currentTimeMillis();
			if ((currentTimeMillis + 60 * 1000) > now) {
				if (code.equalsIgnoreCase(validateCode)) {
					User user = new User();
					user.setTel(telNum);
					user.setPassword(CommonUtils.covertpw(password));
					userService.updatePWByTel(user);
					// request.getSession().setAttribute(Constants.USER, user);
					response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, null, Constants.updateSuccess));
					return;
				} else {
					response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.VerifyFailed));
					return;
				}
			} else {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.VerifyCodeExpired));
				return;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "系统繁忙，请稍后重试"));
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e);
			}
		}
	}

	@RequestMapping(value = "/mobile/myfavoritesSearchOptions", method = RequestMethod.GET)
	public void getMyFavoriatesSearchOptions(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> returnObj = new HashMap<String, Object>();
			// 目的地
			List<Option> destOptions = new ArrayList<Option>();
			destOptions.add(new Option(Constants.AllDestinations, "全部目的地"));
			List<City> cities = cityService.getCityListAll();
			for (City city : cities) {
				destOptions.add(new Option(String.valueOf(city.getId()), city.getCityName()));
			}
			returnObj.put("destinationOptions", destOptions);
			List<Option> serviceOptions = new ArrayList<Option>();// 类型，目前没有专门的表保存这些数据，所以这里是写死的
			serviceOptions.add(new Option(Constants.AllServices, "全部"));
			serviceOptions.add(new Option(Constants.PACKAGE, "套餐"));
			serviceOptions.add(new Option(Constants.HOTEL, "酒店"));
			serviceOptions.add(new Option(Constants.ENTERTAINMENT, "娱乐"));
			serviceOptions.add(new Option(Constants.VILLA, "别墅"));
			serviceOptions.add(new Option(Constants.TRAFFIC, "交通"));
			returnObj.put("typeOptions", serviceOptions);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, returnObj, null));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 我的收藏
	 * 
	 * @param request
	 * @param response
	 * @param ptype
	 * @param page
	 * @param pageSize
	 */
	@RequestMapping(value = "/mobile/myfavorites", method = RequestMethod.GET)
	public void getMyFavorites(HttpServletRequest request, HttpServletResponse response, String ptype,
			String destination, int page, int pageSize) {
		try {
			User user = Utils.getUserInstance(request);
			Map<String, Object> params = new HashMap<>();
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			} else {
				params.put("userId", user.getId());
			}
			if (null != ptype && !"".equals(ptype) && !ptype.equals(Constants.AllServices)) {
				params.put("ptype", ptype);
			}

			if (destination != null && !"".equals(destination)) {
				if (destination.equals(Constants.AllDestinations)) {
					// nothing need to do
				} else {
					params.put("destination", destination);
				}
			}

			params.put("offset", DBUtils.page(page, pageSize));
			params.put("limit", DBUtils.pageSize(page, pageSize));
			List<Map<String, Object>> myfavorites = collectionService.selectCollectionProductListByParam(params);
			int total = collectionService.getCount(params);
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("total", total);
			ret.put("list", myfavorites);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, ret, null));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加到收藏夹， 如果是套餐和娱乐，只传pid 如果是酒店，pid和staylvl=3 如果是交通，pid，startplace和endplace
	 * 如果是别墅，pid和roomcnt=5
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param staylvl
	 *            3
	 * @param startplace
	 * @param endplace
	 * @param roomcnt
	 *            5
	 */
	@RequestMapping(value = "/mobile/myfavorites", method = RequestMethod.POST)
	public void addToMyFavorites(HttpServletRequest request, HttpServletResponse response, String pid) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			Collection favorite = new Collection();
			favorite.setUserId(user.getId());
			if (null != pid && !"".equals(pid)) {
				favorite.setPid(pid);
			}
			favorite.setUserId(user.getId());
			favorite.setCtuser(user.getUsername());
			favorite.setCtdt(new Date());

			int isPid = collectionService.selectByPid(favorite);
			String resString = null;
			if (isPid > 0) {
				resString = "您已收藏过此产品";
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, resString));
				return;
			} else {
				if (collectionService.insert(favorite)) {
					resString = "保存成功";
					response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, resString));
					return;
				} else {
					resString = "保存失败";
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, resString));
					return;
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, e.getMessage()));
			} catch (IOException e1) {

			}
		}
	}

	/**
	 * 添加到收藏夹， 如果是套餐和娱乐，只传pid 如果是酒店，pid和staylvl=3 如果是交通，pid，startplace和endplace
	 * 如果是别墅，pid和roomcnt=5
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @param staylvl
	 *            3
	 * @param startplace
	 * @param endplace
	 * @param roomcnt
	 *            5
	 */
	@RequestMapping(value = "/mobile/delmyfavorites", method = RequestMethod.POST)
	public void delMyFavorites(HttpServletRequest request, HttpServletResponse response, String pid) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			int userId = user.getId();
			Collection favorite = new Collection();
			favorite.setUserId(userId);
			if (null != pid && !"".equals(pid)) {
				favorite.setPid(pid);
			}
			String resString = "";
			if (collectionService.delete(favorite)) {
				resString = "删除成功";
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, resString));
				return;
			} else {
				resString = "删除失败";
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, resString));
				return;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, e.getMessage()));
			} catch (IOException e1) {

			}
		}
	}

	@RequestMapping(value = "/mobile/myorder", method = RequestMethod.GET)
	public void getOrderInfo(HttpServletResponse response, HttpServletRequest request, String orderNo) {
		try {
			User user = Utils.getUserInstance(request);
			if (user == null) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}

			if (orderNo != null && !orderNo.isEmpty()) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orderNo", orderNo);
				RouteOrder order = routeOrderService.getRouteOrder(params);
				response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, order, null));
				return;
			} else {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "订单号不正确，请检查后重新使用！"));
				return;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * update status for an order
	 * 
	 * @param response
	 * @param request
	 * @param status
	 */
	@RequestMapping(value = "/mobile/cancelOrder", method = RequestMethod.POST)
	public void updateOrderStatus(HttpServletResponse response, HttpServletRequest request, String orderNo) {

		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orderNo", orderNo);
			params.put("status", "5");
			routeOrderService.updateStatus(params);
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, Constants.updateSuccess));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * <c:if test="${row.status==1 }"> 待付款 </c:if> <c:if test=
	 * "${row.status==2 }"> 待处理 </c:if> <c:if test=
	 * "${row.status==3 }" > 待付尾款 </c:if> <c:if test=
	 * "${row.status==4 }" > 待出行 </c:if> <c:if test=
	 * "${row.status==5 }" > 已完成 </c:if> <c:if test=
	 * "${row.status==6 }" > 出行中 </c:if> <c:if test=
	 * "${row.status==8 }" > 支付中 </c:if> <c:if test=
	 * "${row.status==9 }" > 已退款 </c:if> /** 生成订单
	 * 
	 * @param response
	 * @param request
	 * @param orderNo
	 *            : 订单号，对于新建立的订单，可以为空
	 * @param dates
	 *            ： 日期列表，格式： “2016-8-12”， “2016-8-13”，“2017-8-14”
	 * @param pid
	 *            ： 产品ID
	 * @param pname
	 *            ： 产品类别，0：套餐，1：酒店，2：别墅，3：交通，4：娱乐
	 * @param adults
	 *            : 随行成人数
	 * @param children
	 *            ： 随行儿童
	 * @param amount
	 *            ： 总金额
	 * @param userName
	 *            ： 联系人名字，默认是登陆人名字
	 * @param telNum
	 *            ： 联系人电话，默认是登陆人电话
	 * @param email
	 *            ： 联系人邮件地址，默认是登陆人邮件地址
	 */
	@RequestMapping(value = "/mobile/myorder", method = RequestMethod.POST)
	public void createRoute(HttpServletResponse response, HttpServletRequest request, String orderNo, String time_start,
			String time_end, int time_days, double order_amount, String linkman_name, String linkman_phone,
			String remark, String pdt_id, String pdt_name, String pdt_type, String pdt_address, String pdt_pic,
			String dataArray, String order_data, String jsonString) {
		try {
			User user = Utils.getUserInstance(request);
			if (user == null) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = parser.parse(order_data).getAsJsonArray();
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				// 获取第i个数组元素
				JsonElement el = jsonArray.get(i);
				// 映射为类实例
				OrderItem orderItem = gson.fromJson(el, OrderItem.class);
				orderItems.add(orderItem);
			}

			SimpleDateFormat sdf = new SimpleDateFormat(Constants.StardardFmt);

			// Double total = 0.00;// 总价
			// Double prepaytotal = 0.00;// 总预付价格
			String ctuser = String.valueOf(user.getId());

			RouteOrderVO routeOrderVO = new RouteOrderVO();
			// 行程单
			RouteOrder routeOrder = new RouteOrder();
			if (orderNo != null && !orderNo.isEmpty()) {
				routeOrderService.deleteRoute(orderNo);
				routeOrder.setOrderNo(orderNo);
			}

			routeOrder.setCusNo(ctuser);// 客户编号
			routeOrder.setStatus("1");// 订单状态 1:预定
			// 行程开始时间
			Date startDate = sdf.parse(time_start);
			// 行程结束时间
			Date endDate = sdf.parse(time_end);
			routeOrder.setStartdate(startDate);// 行程开始日期
			routeOrder.setEnddate(endDate);// 行程结束日期
			routeOrder.setAdultCnt(1);// 成人数
			routeOrder.setChildCnt(0);// 儿童数
			// routeOrder.setAmount(order_amount);// 总价
			// routeOrder.setPrepay(order_amount);// 预付款
			routeOrder.setEvaluateflg("0");// 是否已评价 0是 1否
			routeOrder.setDelFlg("0");// 有效FLG 0:有效
			routeOrder.setBillTime(new Date());// 下单时间
			routeOrder.setCtuser(ctuser);// 创建人
			routeOrder.setCtdt(new Date()); // 创建时间
			routeOrder.setRouteJson(jsonString);
			routeOrder.setUpdt(new Date());
			routeOrderVO.setRouteOrder(routeOrder);
			// calculate price for product
			if (Constants.PACKAGE.equals(pdt_type)) {
				calculatePackagePrice(orderItems, routeOrderVO);
			} else if (Constants.VILLA.equals(pdt_type)) {
				calcalateVillaPrice(time_days, pdt_id, orderItems, routeOrderVO);
			} else if (Constants.HOTEL.equals(pdt_type)) {
				calculateHotelPrice(orderItems, routeOrderVO);
			} else if (Constants.ENTERTAINMENT.equals(pdt_type)) {
				calculateEntertainmentPrice(orderItems, routeOrderVO);
			} else if (Constants.TRAFFIC.equals(pdt_type)) {
				calculateTrafficPrice(orderItems, routeOrderVO);
			}

			routeOrder.setOrderNm(pdt_name);// 订单名称
			double amount = routeOrder.getAmount();
			if (amount == order_amount) {
				routeOrder.setOldAmount(routeOrder.getAmount());
				routeOrderService.saveRouteOrderVO(routeOrderVO);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("cusNo", user.getId());
				RouteOrder order = routeOrderService.getLatestOrderByUser(params);
				response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, order, "订单生成成功！"));
			} else {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "下单失败，数据已刷新，请重新下单或者联系系统管理员！"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "订单生成失败！"));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void calculateTrafficPrice(List<OrderItem> orderItems, RouteOrderVO routeOrderVO) {
		Double total = 0.0;// 总价
		Double prepaytotal = 0.0;// 总预付价格
		int peopleMax = 0;
		// 租车
		for (OrderItem orderItem : orderItems) {
			int number = orderItem.getNum();// 预定数量
			int id = orderItem.getId();// 项目标识
			// 价格项详情
			TrafficCharteredPriceInfo info = trafficCharteredPriceInfoService.selectTrafficCharteredPriceInfoById(id);
			BigDecimal price = info.getPrice();// 价格项价格
			BigDecimal prepay = info.getPrepay();// 价格项预付价格
			int maxpeople = info.getMaxpeople();
			Double amout = Double.parseDouble(price.toString()) * number;
			Double prepayAmount = Double.parseDouble(prepay.toString()) * number;
			total = total + amout;
			prepaytotal = prepaytotal + prepayAmount;
			// 总人数
			peopleMax = peopleMax + maxpeople * number;

		}
		routeOrderVO.getRouteOrder().setAmount(total);
		routeOrderVO.getRouteOrder().setPrepay(prepaytotal);
	}

	/**
	 * 计算酒店相关价格
	 * 
	 * @param orderItems
	 * @param routeOrderVO
	 */
	private void calculateHotelPrice(List<OrderItem> orderItems, RouteOrderVO routeOrderVO) {
		Double total = 0.0;// 总价
		Double prepaytotal = 0.0;// 总预付价格
		int peopleMax = 0;
		// 酒店
		for (OrderItem orderItem : orderItems) {
			int number = orderItem.getNum();// 预定数量
			int id = orderItem.getId();// 项目标识
			// 价格项详情
			RoomPriceInfo roomPriceInfo = roomPriceInfoService.selectRoomPriceInfoById(id);
			// String priceItem = roomPriceInfo.getPriceItem();// 价格项
			BigDecimal roomPrice = roomPriceInfo.getRoomPrice();// 预定价格
			BigDecimal prepay = roomPriceInfo.getPrepay();// 预定预付价格

			Double amout = Double.parseDouble(roomPrice.toString()) * number;
			Double prepayAmount = Double.parseDouble(prepay.toString()) * number;
			total = total + amout;
			prepaytotal = prepaytotal + prepayAmount;

			// 总人数
			peopleMax = peopleMax + roomPriceInfo.getPeopleCnt() * number;

		}
		routeOrderVO.getRouteOrder().setAmount(total);
		routeOrderVO.getRouteOrder().setPrepay(prepaytotal);
	}

	/**
	 * 计算套餐相关价格
	 * 
	 * @param orderItems
	 * @param routeOrderVO
	 */
	private void calculatePackagePrice(List<OrderItem> orderItems, RouteOrderVO routeOrderVO) {
		// 总价
		Double total = 0.0;
		// 总预付价格
		Double prepaytotal = 0.0;
		int peopleMax = 0;
		for (OrderItem orderItem : orderItems) {
			int number = orderItem.getNum();// 预定数量
			int id = orderItem.getId();// 项目标识
			// 价格项详细信息
			PackagePriceInfo packagePriceInfo = packagePriceInfoService.selectPackagePriceInfoById(id);
			// String priceItem = packagePriceInfo.getPriceItem();// 预定价格项
			BigDecimal price = packagePriceInfo.getPrice();// 预定价格
			BigDecimal prepay = packagePriceInfo.getPrepay();// 预定预付价格

			Double amout = Double.parseDouble(price.toString()) * number;
			Double prepayAmount = Double.parseDouble(prepay.toString()) * number;
			total = total + amout;
			prepaytotal = prepaytotal + prepayAmount;

			// 总人数
			peopleMax = peopleMax + packagePriceInfo.getPeopleCnt() * number;

		}
		routeOrderVO.getRouteOrder().setAmount(total);
		routeOrderVO.getRouteOrder().setPrepay(prepaytotal);
	}

	/**
	 * 计算娱乐相关价格
	 * 
	 * @param time_days
	 * @param pdt_id
	 * @param orderItems
	 * @param routeOrderVO
	 */
	private void calculateEntertainmentPrice(List<OrderItem> orderItems, RouteOrderVO routeOrderVO) {
		Double total = 0.0;// 总价
		Double prepaytotal = 0.0;// 总预付价格
		int peopleMax = 0;
		for (OrderItem orderItem : orderItems) {
			int number = orderItem.getNum();// 预定数量
			int id = orderItem.getId();// 项目标识
			EntPriceInfo entPriceInfo = entPriceInfoService.selectEntPriceInfoById(id);
			BigDecimal price = entPriceInfo.getPrice();// 预定价格
			BigDecimal prepay = entPriceInfo.getPrepay();// 预定预付价格

			Double amout = Double.parseDouble(price.toString()) * number;
			Double prepayAmount = Double.parseDouble(prepay.toString()) * number;
			total = total + amout;
			prepaytotal = prepaytotal + prepayAmount;
			peopleMax = peopleMax + (entPriceInfo.getPeopleCnt() * number);
		}
		routeOrderVO.getRouteOrder().setAdultCnt(peopleMax);
		routeOrderVO.getRouteOrder().setAmount(total);
		routeOrderVO.getRouteOrder().setPrepay(prepaytotal);
	}

	/**
	 * 计算别墅相关价格
	 * 
	 * @param time_days
	 * @param pdt_id
	 * @param orderItems
	 * @param routeOrderVO
	 */
	private void calcalateVillaPrice(int time_days, String pdt_id, List<OrderItem> orderItems,
			RouteOrderVO routeOrderVO) {
		Double total = 0.0;// 总价
		Double prepaytotal = 0.0;// 总预付价格
		int peopleMax = 0;
		// 产品pid
		Map<String, Object> param = new HashMap<>(3);
		param.put("pid", pdt_id);
		// 别墅属性
		// VillaInfo row =
		// villaInfoService.selectVillaInfoByParam(param);
		// 别墅价格项
		for (OrderItem orderItem : orderItems) {
			int number = orderItem.getNum();// 预定数量
			int id = orderItem.getId();// 项目标识
			int extrabed_number = orderItem.getExtrabedNum();// 加床数量
			VillaDetailPriceInfo villaDetailPriceInfo = villaDetailPriceInfoService.selectVillaDetailPriceInfoById(id);
			BigDecimal prepay = villaDetailPriceInfo.getPrepay();// 预定预付价格
			BigDecimal roomPrice = villaDetailPriceInfo.getRoomPrice();// 预定价格
			BigDecimal addPrice = villaDetailPriceInfo.getAddPrice();// 预定加床价格
			if (addPrice == null) {
				addPrice = new BigDecimal(0);
			}

			Double amout = 0.00;
			Double prepayAmount = 0.00;
			amout = time_days * (number * Double.parseDouble(roomPrice.toString())
					+ extrabed_number * Double.parseDouble(addPrice.toString()));
			prepayAmount = time_days * (Double.parseDouble(prepay.toString()) * number);
			total = total + amout;
			prepaytotal = prepaytotal + prepayAmount;
			peopleMax = peopleMax + (villaDetailPriceInfo.getPeopleCnt() * number);
		}
		routeOrderVO.getRouteOrder().setAdultCnt(peopleMax);
		routeOrderVO.getRouteOrder().setAmount(total);
		routeOrderVO.getRouteOrder().setPrepay(prepaytotal);
	}
}
