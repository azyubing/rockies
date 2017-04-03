package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.BaseMapper;

public interface CustomerMapper extends BaseMapper {

	void deleteBucthCustomer(String[] str);

	List<Map<String, Object>> getTravelPartnersByOrderNo(Map<String, Object> params);

	List<Map<String, Object>> getTravelPartners(Map<String, Object> params);

}
