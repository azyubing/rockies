package com.rockies.mobile.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rockies.mobile.constants.Constants;
import com.rockies.mobile.utils.Utils;
import com.rockies.model.BookCusInfo;
import com.rockies.model.Customer;
import com.rockies.model.User;
import com.rockies.services.IBookCusInfoService;
import com.rockies.services.ICustomerService;

/**
 * 同行伙伴管理
 * 
 * @author Gordon
 * 
 */
@Controller
public class TravelPartner {
	private static final Logger logger = LoggerFactory.getLogger(TravelPartner.class);
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IBookCusInfoService bookCustInfoService;

	// /**
	// * 到新增客户页面
	// *
	// * @param response
	// * @param request
	// * @param menu
	// * @return
	// */
	// @RequestMapping(value = "/mobile/TravelPartner", method = RequestMethod.POST)
	// public ModelAndView addCustomer(HttpServletResponse response, HttpServletRequest request) {
	// try {
	// Map map = new HashMap();
	// map.put("parentId", 0);
	// List<City> cityList = cityService.getCityList(map);
	// request.setAttribute("cityList", cityList);
	// } catch (Exception e) {
	// e.printStackTrace();
	// if (null != e.getMessage()) {
	// System.out.println(e.toString());
	// }
	// logger.info("login fail. " + e.toString());
	// }
	// return new ModelAndView("/customer/addCustomer", "menu", menu);
	// }
	//

	/**
	 * 保存或者更新随行人信息
	 * 
	 * @param response
	 * @param request
	 * @param realName
	 *            ： 随行人真实姓名
	 * @param customerName
	 *            ： 真实姓名拼音
	 * @param cardType
	 *            ：证件类型 <option value="1" <c:if test="${orderCusInfo.cardType=='1' }">selected="selected"</c:if>>身份证</option> <option value="2" <c:if
	 *            test="${orderCusInfo.cardType=='2' }">selected="selected"</c:if>>护照</option> <option value="3" <c:if test="${orderCusInfo.cardType=='3' }">selected="selected"</c:if>>军人证</option>
	 *            <option value="4" <c:if test="${orderCusInfo.cardType=='4' }">selected="selected"</c:if>>回乡证</option> <option value="5" <c:if
	 *            test="${orderCusInfo.cardType=='5' }">selected="selected"</c:if>>台胞证</option> <option value="6" <c:if test="${orderCusInfo.cardType=='6' }">selected="selected"</c:if>>港澳通行证</option>
	 *            <option value="7" <c:if test="${orderCusInfo.cardType=='7' }">selected="selected"</c:if>>户口簿</option> <option value="8" <c:if
	 *            test="${orderCusInfo.cardType=='8' }">selected="selected"</c:if>>出生证明</option> <option value="9" <c:if test="${orderCusInfo.cardType=='9' }">selected="selected"</c:if>>其他</option>
	 * @param birthday
	 *            ：出生日期
	 * @param cardNumber
	 *            ：证件号码
	 */
	@RequestMapping(value = "/mobile/saveTravelPartner", method = RequestMethod.POST)
	public void saveTravelPartner(HttpServletResponse response, HttpServletRequest request, int id, String orderNo, String realName, String customerName, String cardType, String birthday,
			String cardNumber) {
		try {
			User user = Utils.getUserInstance(request);
			if (null == user) {
				response.getWriter().print(Utils.getReturnedData(Constants.LoginFAILED, null, Constants.ErrorUser));
				return;
			}
			Customer customer = new Customer();
			customer.setUserId(user.getId());
			customer.setCtuser(String.valueOf(user.getId()));
			customer.setCtdt(new Date());
			customer.setRealName(realName);
			customer.setCustomerName(customerName);
			customer.setCardType(cardType);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			customer.setBirthday(df.parse(birthday));
			customer.setCardNumber(cardNumber);
			if (id == 0) {
				//new travcel partner
				customerService.insert(customer);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("cardNumber", cardNumber);
				List<Map<String, Object>> travelPartners = customerService.getTravelPartners(params);
				if (travelPartners.size() > 0) {
					String cusNo = String.valueOf(travelPartners.get(0).get("id"));
					BookCusInfo bookCusInfo = new BookCusInfo();
					bookCusInfo.setCtdt(new Date());
					bookCusInfo.setCtuser(String.valueOf(user.getId()));
					bookCusInfo.setOrderNo(orderNo);
					bookCusInfo.setCusNo(cusNo);
					bookCustInfoService.saveBookCusInfo(bookCusInfo);
				}

			} else {
				//update travel partner
				customer.setUpdt(new Date());
				customer.setUpuser(String.valueOf(user.getId()));
				customer.setUserId(user.getId());
				customer.setId(id);
				customerService.updateObject(customer);
			}
			response.getWriter().print(Utils.getReturnedData(Constants.SUCCESS, null, Constants.SaveSuccess));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	//
	// /**
	// * 修改客户
	// *
	// * @param response
	// * @param request
	// * @param menu
	// * @return
	// */
	// @RequestMapping(value = "/customer/updateCustomer")
	// public void updateCustomer(HttpServletResponse response, HttpServletRequest request,
	// @ModelAttribute("customer") Customer customer) {
	// try {
	// customer.setUpdt(new Date());
	// customer.setUpuser(getAdminId(request) + "");
	// customerService.updateObject(customer);
	// response.getWriter().print("success");
	// } catch (Exception e) {
	// e.printStackTrace();
	// if (null != e.getMessage()) {
	// System.out.println(e.toString());
	// }
	// logger.info("updateCustomer fail. " + e.toString());
	// }
	// }
	//
	// /**
	// * 删除客户
	// *
	// * @param response
	// * @param request
	// * @param menu
	// * @return
	// */

	@RequestMapping(value = "/mobile/TravelPartner", method = RequestMethod.GET)
	public void getTravelPartners(HttpServletResponse response, HttpServletRequest request, String orderNo) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orderNo", orderNo);
			List<Map<String, Object>> travelPartners = customerService.getTravelPartnersByOrderNo(params);
			response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, travelPartners, null));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/mobile/deleteTravelPartner", method = RequestMethod.POST)
	public void deleteTravelPartner(HttpServletResponse response, HttpServletRequest request, String orderNo, int id) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("cusNo", id);
			param.put("orderNo", orderNo);
			bookCustInfoService.deleteBookCusInfoByParams(param);
			customerService.deleteByPrimaryKey(id);
			response.getWriter().write(Utils.getReturnedData(Constants.SUCCESS, null, Constants.delSuccess));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
