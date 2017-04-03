package com.rockies.mapper;

import java.util.List;

import com.rockies.model.PayBean;

public interface IPayMapper {
	/**
	 * 获取所有的 付款方式集合
	 * @return list
	 */
	public List<PayBean> getPayList();
}
