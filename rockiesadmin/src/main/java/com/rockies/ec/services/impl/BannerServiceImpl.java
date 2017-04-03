package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.BannerMapper;
import com.rockies.ec.services.IBannerService;

@Service
public class BannerServiceImpl implements IBannerService {

	@Autowired
	private BannerMapper bannerMapper;
	@Override
	public int insert(Object o) {
		bannerMapper.insert(o);
		return 0;
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		bannerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		return bannerMapper.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		return bannerMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return bannerMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		return bannerMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		return bannerMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		bannerMapper.updateObject(o);
	}

}
