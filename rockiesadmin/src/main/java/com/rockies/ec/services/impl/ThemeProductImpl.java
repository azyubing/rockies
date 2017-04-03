package com.rockies.ec.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.ThemeCatalogMapper;
import com.rockies.ec.mapper.ThemeProductMapper;
import com.rockies.ec.model.ThemeCatalog;
import com.rockies.ec.model.ThemeProduct;
import com.rockies.ec.services.IThemeProductService;

@Service
public class ThemeProductImpl implements IThemeProductService {

	@Autowired
	private ThemeProductMapper themeProductMapper;
	
	@Autowired
	private ThemeCatalogMapper themeCatalogMapper;
	
	@Override
	public int insert(Object o) {
		// TODO Auto-generated method stub
		return themeProductMapper.insert(o);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		themeProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		// TODO Auto-generated method stub
		return themeProductMapper.getAllList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> getAllListByMap(Map map) {
		// TODO Auto-generated method stub
		return themeProductMapper.getAllListByMap(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		// TODO Auto-generated method stub
		return themeProductMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return themeProductMapper.getOneByPrimaryKey(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public long getCount(Map map) {
		// TODO Auto-generated method stub
		return themeProductMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		// TODO Auto-generated method stub
		themeProductMapper.updateObject(o);
	}

	@Override
	public void saveListThemeProduct(String productIds, String themeCatalogId,int userId,int themeId) {
		String[] ids = productIds.split(",");
		for(String id :ids){
			ThemeProduct t = new ThemeProduct();
			t.setCtdt(new Date());
			t.setCtuser(userId+"");
			t.setProductId(id);
			t.setThemeCatalogId(Integer.parseInt(themeCatalogId));
			t.setIsHomeShow(1);
			t.setThemeId(themeId);
			this.insert(t);
		}
		
		
		ThemeCatalog themeCatalog = (ThemeCatalog) themeCatalogMapper.getOneByPrimaryKey(Integer.parseInt(themeCatalogId));
		themeCatalog.setHasProduct(0);
		themeCatalogMapper.updateObject(themeCatalog);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkProductExist(Map map) {
		int count = themeProductMapper.checkProductExist(map);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public void deleteThemeProduct(int id, int themeCatalogId) {
		themeProductMapper.deleteByPrimaryKey(id);
		int count = themeProductMapper.checkHasProduct(themeCatalogId);
		if(count==0){
			ThemeCatalog themeCatalog = (ThemeCatalog) themeCatalogMapper.getOneByPrimaryKey(themeCatalogId);
			themeCatalog.setHasProduct(1);
			themeCatalogMapper.updateObject(themeCatalog);
		}
	}

}
