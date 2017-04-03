package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.services.base.BaseService;

public interface ICustomerService extends BaseService {

	void deleteBucthCustomer(String[] str);

	List<Map<String, Object>> getTravelPartnersByOrderNo(Map<String, Object> params);

	List<Map<String, Object>> getTravelPartners(Map<String, Object> params);

}
