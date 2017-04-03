package com.rockies.ec.services;

import java.util.List;

import com.rockies.ec.model.CountryBean;
import com.rockies.ec.model.PayBean;

public interface IPayService {
	/**
	 * 获取所有的 付款方式集合
	 * @return list
	 */
	public List<PayBean> getPayList();
}
