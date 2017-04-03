package com.rockies.ec.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ICommonMapper;
import com.rockies.ec.services.ICommonService;

@Service
public class CommonServiceImpl implements ICommonService {

	@Autowired
	private ICommonMapper commonMapper;

	@Override
	public List<String> getAutoCompleteList(String tblnm, String colnm) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("tblnm",tblnm);
		param.put("colnm", colnm);
		return commonMapper.getAutoCompleteList(param);
	}
	

}
