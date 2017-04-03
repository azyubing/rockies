package com.rockies.ec.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ISuppliersMapper;
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.services.ISuppliersService;
import com.rockies.ec.vo.SuppliersVO;

@Service
public class SuppliersService implements ISuppliersService {

	@Autowired
	private ISuppliersMapper suppliersMapper;
	
	@Override
	public void save(SuppliersBean suppliersBean) {
		suppliersBean.setCtdt(new Date());
		suppliersMapper.save(suppliersBean);
	}

	@Override
	public void update(SuppliersBean suppliersBean) {
		suppliersBean.setUpdt(new Date());
		suppliersMapper.update(suppliersBean);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> getSuppliersBeanList(Map map) {
		return suppliersMapper.getSuppliersBeanList(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getCount(Map map) {
		return suppliersMapper.getCount(map);
	}

	@Override
	public SuppliersVO getSuppliersVO(int sid) {
		// TODO Auto-generated method stub
		return suppliersMapper.getSuppliersVO(sid);
	}

	@Override
	public List<SuppliersBean> getAllList() {
		// TODO Auto-generated method stub
		return suppliersMapper.getAllList();
	}

}
