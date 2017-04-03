package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ThemeCatalogMapper;
import com.rockies.ec.services.IThemeCatalogService;
import com.rockies.ec.vo.ThemeCatalogVO;

@Service
public class ThemeCatalogServiceImpl implements IThemeCatalogService {
	
	@Autowired
	private ThemeCatalogMapper themeCatalog;
	
	@Override
	public int insert(Object o) {
		// TODO Auto-generated method stub
		return themeCatalog.insert(o);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		themeCatalog.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		// TODO Auto-generated method stub
		return themeCatalog.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		// TODO Auto-generated method stub
		return themeCatalog.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		// TODO Auto-generated method stub
		return themeCatalog.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return themeCatalog.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		// TODO Auto-generated method stub
		return themeCatalog.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		// TODO Auto-generated method stub
		themeCatalog.updateObject(o);
	}

	@Override
	public ThemeCatalogVO getThemeCatalogVO(int id) {
		// TODO Auto-generated method stub
		return themeCatalog.getThemeCatalogVO(id);
	}

	@Override
	public List<Map<String, Object>> getAllProductList(int id) {
		// TODO Auto-generated method stub
		return themeCatalog.getAllProductList(id);
	}



	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> themeProductAdminSearchList(Map param) {
		// TODO Auto-generated method stub
		return themeCatalog.themeProductAdminSearchList(param);
	}

	@Override
	public int getAllProductCount(Map param) {
		// TODO Auto-generated method stub
		return themeCatalog.getAllProductCount(param);
	}

}
