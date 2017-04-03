package com.rockies.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.IPayMapper;
import com.rockies.model.PayBean;
import com.rockies.services.IPayService;

@Service
public class PayService implements IPayService {
	
	@Autowired
	private IPayMapper payMapper;

	@Override
	public List<PayBean> getPayList() {
		// TODO Auto-generated method stub
		return payMapper.getPayList();
	}
	

}
