package com.rockies.mobile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.rockies.common.utils.CommonUtils;
import com.rockies.common.utils.FileUpAndDown;
import com.rockies.common.utils.mail.MailSender;
import com.rockies.mobile.constants.Constants;
import com.rockies.mobile.utils.Utils;
import com.rockies.model.MessageHistory;
import com.rockies.model.RouteOrder;
import com.rockies.model.User;
import com.rockies.services.IMessageHistoryService;
import com.rockies.services.IRouteOrderService;
import com.rockies.sms.SmsClientSend;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import com.pingplusplus.util.WxpubOAuth;

@Controller
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	private IRouteOrderService routeOrderService;
	@Autowired
	private IMessageHistoryService emailService;

	@RequestMapping(value = "/mobile/paySuccess")
	public ModelAndView paySuccess(HttpServletResponse response, HttpServletRequest request) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().write(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return null;
			} else {
				Object chargeObj = request.getSession().getAttribute(Constants.CHARGE_OBJ);
				if (chargeObj == null) {
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "支付失败，未找到支付凭证！"));
					return null;
				} else {
					JSONObject jsonObject = JSONObject.parseObject(String.valueOf(chargeObj));
					String id = jsonObject.getString("id");
					Pingpp.apiKey = CommonUtils.getInterfaceConfigBykey("pingpp_apikey");
					Charge charge = Charge.retrieve(id);
					if (charge.getPaid()) {
						String ping_orderid = charge.getId();
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("ping_orderid", ping_orderid);
						routeOrderService.updateRoutePaySuccess(param);
					}
					return new ModelAndView("redirect:/orderOver.html");
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (InvalidRequestException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (APIConnectionException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (APIException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (ChannelException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/img/uploadFile")
	public void uploadFileList(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr,
			@RequestParam("uploadList") MultipartFile[] fileList) {
		try {
			List<String> imgUrlList = new ArrayList<String>(1);
			for (int i = 0; i < fileList.length; i++) {
				MultipartFile file = fileList[i];
				String fileName = file.getOriginalFilename();

				if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".png")
						&& !fileName.toLowerCase().endsWith(".jpeg")) {
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "对不起，图片格式只能为png和jpg！！！"));
					return;
				}
				if (file.getSize() > 1048576) {
					response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "对不起，上传图片不要超过1兆！！！"));
					return;
				}
				String resString = FileUpAndDown.fileUpload(file, fileName);
				imgUrlList.add(resString);
			}
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, imgUrlList, "图片上传成功！"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "图片上传失败！"));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, e.getMessage()));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@RequestMapping(value = "/mobile/sendTelVerifyCode", method = RequestMethod.POST)
	public void sendCodeByTel(HttpServletResponse response, HttpServletRequest request, String tel) {
		try {
			String code = String.valueOf(Math.random() * 9000 + 1000).substring(0, 4);
			String sendMsg = CommonUtils.getMsgTemplateBykey("verif_code_phone");
			SmsClientSend.sendSms(tel, MessageFormat.format(sendMsg, code));
			request.getSession().setAttribute(Constants.CheckCode, code);
			request.getSession().setAttribute(Constants.CurrentTime, System.currentTimeMillis());
			request.getSession().setAttribute(Constants.TelNumber, tel);
			response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, null, Constants.SendMsgSuceess));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.SendMsgFailed));
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
	}

	@RequestMapping(value = "/mobile/verifyCode", method = RequestMethod.POST)
	public void verifyCode(HttpServletResponse response, HttpServletRequest request, String checkCode) {

		try {
			String code = (String) request.getSession().getAttribute(Constants.CheckCode);
			long currentTimeMillis = (long) request.getSession().getAttribute(Constants.CurrentTime);
			long now = System.currentTimeMillis();
			if ((currentTimeMillis + 60 * 1000) > now) {
				if (checkCode.equalsIgnoreCase(code)) {
					response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, null, Constants.VerifySuccess));
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
		}
	}

	/**
	 * 
	 * @param response
	 * @param request
	 * @param year:
	 *            出行年份
	 * @param month：
	 *            出行月份
	 * @param targetCity:
	 *            目标城市
	 * @param contract：
	 *            联系方式
	 * @param days：
	 *            出行天数
	 * @param personCount：
	 *            出行人数
	 * @param budget：
	 *            预算
	 * @param audltCount：
	 *            成人数
	 * @param comments
	 */
	@RequestMapping(value = "/mobile/sendRequestMail", method = RequestMethod.POST)
	public void sendRequestMail(HttpServletResponse response, HttpServletRequest request, String year, String month,
			String targetCity, String contract, String days, String personCount, String budget, String audltCount,
			String comments) {
		try {
			if (targetCity == null || "".equals(targetCity)) {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "目标城市不能为空！"));
				return;
			} else if (contract == null || "".equals(contract)) {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "联系方式不能为空！"));
				return;
			}
			String code = "";
			code += "<p>目的地城市：" + targetCity + "</p>";
			code += "<p>出发日期：" + year + "-" + month + "</p>";
			code += "<p>游玩天数：" + days + "</p>";
			code += "<p>游玩人数：" + personCount + "</p>";
			code += "<p>其中：儿童 " + audltCount + "</p>";
			code += "<p>(0-12岁)</p>";
			code += "<p>" + comments + "</p>";
			code += "<p>预算：" + budget + "</p>";
			code += "<p>联系方式：" + contract + "</p>";
			if (MailSender.send_custom_mail(code)) {
				MessageHistory mh = new MessageHistory();
				String user = CommonUtils.getInterfaceConfigBykey("auth.user");
				mh.setFrom(user);
				mh.setTo(user);
				mh.setMessage(code);
				mh.setSendTime(new Date());
				mh.setType(Constants.CUSTOMER);
				emailService.save(mh);
				response.getWriter().print(
						Utils.getReturnedData(Constants.SUCCESS, null, "小主，您的定制需求已下达成功，<br>贴心的小麦会马上与您联系，请小主耐心等候哦"));
			} else {
				response.getWriter().print(Utils.getReturnedData(Constants.FAILED, null, "您的请求未能发送成功，请重新提交！"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	// @RequestMapping(value = "/mobile/pay", method = RequestMethod.POST)
	// public void requestpay(HttpServletResponse response, HttpServletRequest
	// request, String channel, double amount,
	// String orderNo) {
	// try {
	// Map<String, Object> param = new HashMap<>();
	// param.put("orderNo", orderNo);
	// RouteOrder routeOrder =
	// routeOrderService.getMyPayRouteOrderByOrderNo(param);
	// Long amountDecimal = new Double(routeOrder.getAmount() *
	// 100).longValue();
	// String amountDecimalStr = amountDecimal.toString();
	// if (routeOrder.getAmount() == amount) {
	// Pingpp.apiKey = CommonUtils.getInterfaceConfigBykey("pingpp_apikey");
	// Map<String, Object> chargeMap = new HashMap<String, Object>();
	// chargeMap.put("amount", Long.parseLong(amountDecimalStr));
	// chargeMap.put("currency", Constants.CNY);
	// chargeMap.put("subject", routeOrder.getOrderNm());
	// DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	// String body = "行程日期：" + fmt.format(routeOrder.getStartdate()) + "~"
	// + fmt.format(routeOrder.getEnddate());
	// chargeMap.put("body", body);
	// chargeMap.put("order_no", orderNo);
	// chargeMap.put("channel", channel);
	// chargeMap.put("client_ip", "127.0.0.1");
	// Map<String, String> app = new HashMap<String, String>();
	// app.put("id", CommonUtils.getInterfaceConfigBykey("pingpp_appid"));
	// chargeMap.put("app", app);
	//
	// if ("alipay_pc_direct".equals(channel)) {
	// // ali pay
	// Map<String, String> extraMap = new HashMap<String, String>();
	// extraMap.put("success_url",
	// CommonUtils.getInterfaceConfigBykey("pingpp_success_url") + orderNo);
	// chargeMap.put("extra", extraMap);
	// } else if ("wx_pub_qr".equals(channel)) {
	// // Weixin pay
	// Map<String, String> extraMap = new HashMap<String, String>();
	// extraMap.put("product_id", orderNo);
	// // extraMap.put("notify_url",
	// // CommonUtils.getInterfaceConfigBykey("pingpp_success_url"));
	// chargeMap.put("extra", extraMap);
	// }
	// try {
	// // 发起交易请求
	// Charge charge = Charge.create(chargeMap);
	// logger.info(charge.toString());
	//
	// // 保存ping++ 生成的支付单号
	// Map<String, Object> param2 = new HashMap<String, Object>();
	// param2.put("ping_orderid", charge.getId());
	// param2.put("orderno", orderNo);
	//
	// routeOrderService.updateRouteStartPay(param2);
	// if ("wx_pub_qr".equals(channel)) {
	// Map<String, Object> qr = charge.getCredential();
	// qr.put("id", charge.getId());
	// // response.getWriter().print(JSON.toJSONString(qr,
	// // JSONProxy.SYSTEM_FEATURE));
	// response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, qr,
	// null));
	// return;
	// }
	// // response.getWriter().print(charge.toString());
	// response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS,
	// charge, null));
	// } catch (ChannelException e) {
	// if (e.getMessage().indexOf(Constants.ErrorPrefix) != -1) {
	// // response.getWriter().print("charge_order_no_used");
	// response.getWriter().write(Utils.getReturnedData(Constants.ErrorPrefix,
	// null, e.getMessage()));
	// }
	// logger.error(e.getMessage(), e);
	// } catch (PingppException e) {
	// logger.error(e.getMessage(), e);
	// }
	// } else {
	// response.getWriter().print("error");
	// response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null,
	// "支付金额和订单金额不一致，请检查后重新支付！"));
	// }
	// } catch (Exception e) {
	// logger.error(e.getMessage(), e);
	// }
	// }

	/**
	 * 微信登录callback
	 * 
	 * @param response
	 * @param request
	 * @return ModelAndView
	 */
	// @RequestMapping(value = "/mobile/wchatlogin")
	// public void wchatlogin(HttpServletResponse response, HttpServletRequest
	// request, String code, String state) {
	// try {
	// WeiChatUtil wchat = new WeiChatUtil();
	// if (null == code) {
	// String callbackURL =
	// URLEncoder.encode(CommonUtils.getInterfaceConfigBykey("wei_login_callback"),
	// Constants.UTF8);
	// String redirectURL =
	// wchat.getOAuth2UserInfoURL(CommonUtils.getInterfaceConfigBykey("wei_appid"),
	// callbackURL);
	// response.sendRedirect(redirectURL);
	// return;
	// }
	// // wei_appid=wxa4a9620e264e143f
	// // wei_appsecret=d4624c36b6795d1d99dcf0547af5443d
	// Map<String, Object> seMap =
	// wchat.getOauth2Info(CommonUtils.getInterfaceConfigBykey("wei_appid"),
	// CommonUtils.getInterfaceConfigBykey("wei_appsecret"), code);
	//
	// logger.error("+++++++++++++++++++++++++++++" +
	// Utils.convertObjectToJson(seMap));
	//
	// if (seMap.get("access_token") == null || seMap.get("openid") == null) {
	// response.getWriter()
	// .write(Utils.getReturnedData(Constants.LoginFAILED, null,
	// Constants.userNotExistOrWrongPwd));
	// return;
	// }
	//
	// String openid = (String) seMap.get("openid");
	//
	// request.getSession().removeAttribute("loginUser");
	// // String openid = "oTrrpwnC5qmkYsjiJOGGo7-WpP4s";
	// // 如果以用微信登陆过，则直接用openid登录
	// User user = userService.weixinLogin(openid);
	// if (user != null) {
	// if (StringUtils.isEmpty(user.getTel())) {
	// request.setAttribute("weiid", openid);
	// // request.setAttribute("nextUrl", nextUrl);
	// // return new ModelAndView("inputTel");
	// }
	// user.setUsername(user.getWeiname());
	// request.getSession().setAttribute(Constants.USER, user);
	// }
	//
	// // 如果没有登陆过，获取微信接口用户信息
	// Map<String, Object> userMap = wchat.getUserInfo((String)
	// seMap.get("access_token"), openid);
	//
	// logger.error("+++++++++++++++++++++++++++++" +
	// Utils.convertObjectToJson(userMap));
	//
	// user = new User();
	// user.setUsername(openid);
	// user.setPassword(CommonUtils.covertpw(UUID.randomUUID().toString()));//
	// 设定默认密码
	// user.setStatus(0);
	// user.setOpenid(openid);
	// user.setUnionid((String) userMap.get("unionid"));
	// user.setPhoto(wchat.saveToFile((String) userMap.get("headimgurl")));
	// user.setWeiname((String) userMap.get("nickname"));
	//
	// userService.saveUser(user);
	//
	// user = userService.weixinLogin(openid);
	// user.setUsername(user.getWeiname());
	// } catch (IOException e) {
	// logger.error(e.getMessage(), e);
	// throw new RuntimeException(e);
	// }
	// }

	/**
	 * ping++ 支付后回调
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/paynotify")
	public void paynotify(HttpServletResponse response, HttpServletRequest request, @RequestBody String code) {
		try {
			request.setCharacterEncoding("UTF8");
			// 获取头部所有信息
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				System.out.println(key + " " + value);
			}
			// 获得 http body 内容
			BufferedReader reader = request.getReader();
			StringBuffer buffer = new StringBuffer();
			String string;
			while ((string = reader.readLine()) != null) {
				buffer.append(string);
			}
			reader.close();

			// 解析异步通知数据
			Event event = Webhooks.eventParse(buffer.toString());
			logger.info(buffer.toString());
			logger.info("callback ping++:" + event.getType() + ":" + event.getId());
			if ("charge.succeeded".equals(event.getType())) {
				String ping_orderid = event.getId();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("ping_orderid", ping_orderid);
				routeOrderService.updateRoutePaySuccess(param);
				response.setStatus(200);
			} else if ("refund.succeeded".equals(event.getType())) {
				String ping_orderid = event.getId();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("ping_orderid", ping_orderid);
				routeOrderService.updateRouteRefundSuccess(param);
				response.setStatus(200);
			} else {
				response.setStatus(500);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/afterCharging")
	public void afterCharging(HttpServletResponse response, HttpServletRequest request) {
		try {
			logger.error((String) request.getSession().getAttribute(Constants.CHARGE_OBJ));
			response.getWriter().print(request.getSession().getAttribute(Constants.CHARGE_OBJ));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 支付价格
	 * 
	 * @param response
	 * @param request
	 * @param channel：支付通道
	 * @param orderNo：
	 *            订单编号
	 * @param amount：
	 *            订单价格
	 */
	@RequestMapping(value = "/requestpay")
	public ModelAndView requestpay(HttpServletResponse response, HttpServletRequest request, String channel,
			double amount, String orderNo, String code) {
		// logger.error("++++++++++++++++++++channel = " + channel + ";amount =
		// " + amount + "; orderNo=" + orderNo + "; code = " + code);
		try {
			DateFormat fmt = new SimpleDateFormat(Constants.StardardFmt);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderNo", orderNo);
			RouteOrder routeOrder = routeOrderService.getMyPayRouteOrderByOrderNo(param);
			double amountDB = routeOrder.getAmount();
			if (amountDB == amount) {
				Pingpp.apiKey = CommonUtils.getInterfaceConfigBykey("pingpp_apikey");
				Charge charge = null;
				Map<String, Object> chargeMap = new HashMap<String, Object>();
				Long amountDecimal = new Double(routeOrder.getAmount() * 100).longValue();
				chargeMap.put("amount", amountDecimal);
				chargeMap.put("currency", "cny");
				chargeMap.put("subject", routeOrder.getOrderNm());
				String body = "行程日期：" + fmt.format(routeOrder.getStartdate()) + "~"
						+ fmt.format(routeOrder.getEnddate());
				chargeMap.put("body", body);
				//orderNo = new Date().getTime() + Utils.randomString(7);
				chargeMap.put("order_no", orderNo);// 推荐使用 8-20
													// 位，要求数字或字母，不允许其他字符
				chargeMap.put("channel", channel);// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
				chargeMap.put("client_ip", "127.0.0.1"); // 发起支付请求客户端的 IP 地址，格式为
															// IPV4，如: 127.0.0.1
				Map<String, String> app = new HashMap<String, String>();
				app.put("id", CommonUtils.getInterfaceConfigBykey("pingpp_appid"));
				chargeMap.put("app", app);
				Map<String, String> extraMap = new HashMap<String, String>();

				if (Constants.ALIPAY.equals(channel)) {
					extraMap.put("success_url", CommonUtils.getInterfaceConfigBykey("pingpp_success_url"));
				} else if (Constants.WX_PUB.equals(channel)) {
					if (null != code) {
						String openid = WxpubOAuth.getOpenId(CommonUtils.getInterfaceConfigBykey("wei_appid"),
								CommonUtils.getInterfaceConfigBykey("wei_appsecret"), code);
						extraMap.put("open_id", openid);
					} else {
						logger.error("+++++++++++++++++++++++++++++++++++++++++++Get openID error!");
					}
					// extraMap.put("product_id", orderNo);
					// extraMap.put("notify_url",
					// CommonUtils.getInterfaceConfigBykey("pingpp_success_url"));
				}
				chargeMap.put("extra", extraMap);
				try {
					// 发起交易请求
					charge = Charge.create(chargeMap);
					request.getSession().setAttribute(Constants.CHARGE_OBJ, charge.toString());
					String ticket = WxpubOAuth.getJsapiTicket(CommonUtils.getInterfaceConfigBykey("wei_appid"),
							CommonUtils.getInterfaceConfigBykey("wei_appsecret"));
					String signature = WxpubOAuth.getSignature(charge.toString(), ticket,
							CommonUtils.getInterfaceConfigBykey("pingpp_success_url") + orderNo);
					request.getSession().setAttribute(Constants.SINGATURE, signature);
					// 保存ping++ 生成的支付单号
					Map<String, Object> param2 = new HashMap<String, Object>();
					param2.put("ping_orderid", charge.getId());
					param2.put("orderno", orderNo);
					routeOrderService.updateRouteStartPay(param2);

					// Map<String, Object> result = new HashMap<String,
					// Object>();
					// result.put("charge", charge.toString());
					// response.getWriter().print(charge.toString());

					if (Constants.ALIPAY.equals(channel)) {
						response.getWriter().println(charge.toString());
						return null;
					} else if (Constants.WX_PUB.equals(channel)) {
						return new ModelAndView("redirect:/orderPay.html?orderNo=" + orderNo + "&chargeData=true");
					}
					response.getWriter().println(Utils.getReturnedData(Constants.FAILED, null, "支付失败！"));
					return null;
				} catch (ChannelException e) {
					if (e.getMessage().indexOf(Constants.CHARGE_ORDER_NO_USED) != -1) {
						response.getWriter()
								.write(Utils.getReturnedData(Constants.CHARGE_ORDER_NO_USED, null, e.getMessage()));
					} else {
						response.getWriter()
								.write(Utils.getReturnedData(Constants.CHARGE_ORDER_NO_USED, null, e.getMessage()));
					}
					logger.error(e.getMessage(), e);

				} catch (PingppException e) {
					logger.error(e.getMessage(), e);
					response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, e.getMessage()));
				}
			} else {
				response.getWriter()
						.print(Utils.getReturnedData(Constants.FAILED, null, "支付失败，支付的价格和数据库中价格不相同，请联系管理员！"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, "支付失败，请联系网站管理员！"));
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
		return null;
	}

	/**
	 * 生成二维码图片并直接以流的形式输出到页面
	 * 
	 * @param code_url
	 * @param response
	 */
	@RequestMapping("/qr_code")
	@ResponseBody
	public void getQRCode(HttpServletResponse response, HttpServletRequest request, String code_url) {
		CommonUtils.encodeQrcode(code_url, response);
	}

	/**
	 * 请求支付
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkpay")
	public void checkpay(HttpServletResponse response, HttpServletRequest request, String id) {
		try {

			Pingpp.apiKey = CommonUtils.getInterfaceConfigBykey("pingpp_apikey");

			Charge charge = Charge.retrieve(id);

			if (charge.getPaid()) {
				String ping_orderid = charge.getId();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("ping_orderid", ping_orderid);
				routeOrderService.updateRoutePaySuccess(param);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("payStatus", charge.getPaid() ? "true" : "false");
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, result, null));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 支付成功页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/paystatus")
	public void paysuccess(HttpServletResponse response, HttpServletRequest request, String orderNo) {
		try {
			Map<String, Object> param1 = new HashMap<>();
			param1.put("orderNo", orderNo);
			RouteOrder routeOrder = routeOrderService.getRouteOrder(param1);

			request.setAttribute("orderNo", orderNo);
			request.setAttribute("amount", routeOrder.getAmount());
			request.setAttribute("paysts", "ok");

			if ("2".equals(routeOrder.getStatus())) {
				response.getWriter().write(Utils.getReturnedData(Constants.PAID, null, "您已付过所有款项，请等待系统确认！"));
				return;
			}

			Pingpp.apiKey = CommonUtils.getInterfaceConfigBykey("pingpp_apikey");

			if (StringUtils.isEmpty(routeOrder.getPingOrderNo())) {
				throw new Exception("success but no ping_orderno");
			}

			Charge charge = Charge.retrieve(routeOrder.getPingOrderNo());

			if (charge.getPaid()) {
				String ping_orderid = charge.getId();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("ping_orderid", ping_orderid);
				routeOrderService.updateRoutePaySuccess(param);
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("ping_orderid", charge.getId());
				routeOrderService.updateRoutePaying(param);
				request.setAttribute("paysts", "ng");
			}
			response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, null, Constants.PaySuccess));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				response.getWriter().write(Utils.getReturnedData(Constants.FAILED, null, Constants.PayFailed));
			} catch (IOException e1) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
