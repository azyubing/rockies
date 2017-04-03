package com.rockies.mobile.constants;

public class Constants {
//reg
	public static final String phoneNumberPattern = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	//message
	public static final String userNotExistOrWrongPwd = "用户不存在或用户名密码错误";
	public static final String activateUser = "请先激活该用户";
	public static final String SystemError = "系统异常,请联系管理员或者网站工作人员!";
	public static final String loginSuccess = "成功登陆!";
	public static final String updateSuccess = "修改成功!";
	public static final String updateFailed = "修改失败!";
	
	public static final String ErrorPhoneNumber = "手机号码无效或者为空!";
	public static final String ErrorPassword = "无效的密码或者密码为空!";
	public static final String ErrorPhoto = "无效的照片地址!";
	public static final String ErrorUser = "用户未登录或者登陆已过期，请重新登陆!";
	
	//status
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	
	public static final String USER = "user";

	public static final String HotServices = "hotService";
	public static final String AllServices = "allServices";
	public static final String HotDesintations = "hotDesinations";
	public static final String AllDestinations = "allDesinations";
	public static final String Default = "default";
	public static final String TopSales = "topSales";
	public static final String TopAttention = "topAttention";
	public static final String PriceDesc = "priceDesc";
	public static final String PriceAsc = "priceAsc";
	public static final String AllDate = "allDate";
	public static final String OrderDate = "orderDate";
	public static final String SetoutDate = "setoutDate";

	public static final String AllTheme = "allThemes";

	public static final String ErrorComment = "您未订购次产品，不能填写这个产品的评论！";

	//验证码
	public static final String CheckCode = "checkCode";
	public static final String CurrentTime = "currentTimeMillis";

	public static final String VerifySuccess = "验证码正确！";
	public static final String VerifyFailed = "不正确的验证码！";
	public static final String VerifyCodeExpired = "验证码过期，请重新发送验证码！";
	public static final String TelNumber = "TelNum";
	public static final String InvalidTelNumber = "无效的手机号码，请重新发送验证码！";

	public static final String CNY = "cny";//人民币

	public static final String ErrorPrefix = "charge_order_no_used";

	public static final String SendMsgSuceess = "短信发送成功！";
	public static final String SendMsgFailed = "短信发送失败！";

	public static final String UserRegisteredSuccess = "用户注册成功！";

	public static final String LoginFAILED = "LoginFailed";

	public static final String SaveSuccess = "保存成功！";

	public static final String delSuccess = "删除成功！";

	public static final String StardardFmt = "yyyy-MM-dd";

	public static final String PaySuccess = "支付成功！";
	public static final String PayFailed = "支付失败！";

	public static final String PACKAGE = "0";
	public static final String HOTEL = "1";
	public static final String VILLA = "2";
	public static final String TRAFFIC = "3";
	public static final String ENTERTAINMENT = "4";
	
	public static final String CHARGE_ORDER_NO_USED = "charge_order_no_used";

	public static final String PAID = "paid";

	public static final String UTF8 = "UTF-8";

	public static final String WX_PUB_QR = "wx_pub_qr";

	public static final String CUSTOMER = "CUSTOMER";

	public static final String ISO8859_1 = "ISO8859_1";
	
	//payment type
	public static final String WX_PUB = "wx_pub";
	public static final String ALIPAY = "alipay_wap";//"alipay_wap";
	public static final String ALIPAY_PC_DIRECT = "alipay_pc_direct";

	public static final String CHARGE_OBJ = "ChargeJSONObj";
	public static final String SINGATURE = "signature";
			
}
