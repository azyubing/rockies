package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ISuppliersContractorMapper;
import com.rockies.ec.model.SuppliersContractorBean;
import com.rockies.ec.services.ISuppliersContractorService;

/**
 * 
 * @author hyh
 *
 */
@Service
public class SuppliersContractorService implements ISuppliersContractorService {

	@Autowired
	private ISuppliersContractorMapper suppliersContractorMapper;
	
	@Override
	public void save(SuppliersContractorBean suppliersContractorBean) {
		suppliersContractorMapper.save(suppliersContractorBean);
	}

	@Override
	public void update(SuppliersContractorBean suppliersContractorBean) {
		suppliersContractorMapper.update(suppliersContractorBean);
	}

	@Override
	public List<Map<String, Object>> getSuppliersContractorBeanList(Map map) {
		return suppliersContractorMapper.getSuppliersContractorBeanList(map);
	}

	@Override
	public int getCount(Map map) {
		return suppliersContractorMapper.getCount(map);
	}

	@Override
	public SuppliersContractorBean getSuppliersContractor(int cid) {
		return suppliersContractorMapper.getSuppliersContractor(cid);
	}

	@Override
	public void deleteByCid(int cid) {
		suppliersContractorMapper.deleteByCid(cid);
	}

	@Override
	public SuppliersContractorBean getSuppliersContractorBySid(long sid) {
		return suppliersContractorMapper.getSuppliersContractorBySid(sid);
	}

}
