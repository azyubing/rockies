package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.ThemeMapper;
import com.rockies.services.IThemeService;

@Service
public class ThemeServiceImpl implements IThemeService {

	@Autowired
	private ThemeMapper themeMapper;
	@Override
	public int insert(Object o) {
		return themeMapper.insert(o);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		themeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		return themeMapper.getAllList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return themeMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		return themeMapper.getOneByPrimaryKey(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public long getCount(Map map) {
		return themeMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		themeMapper.updateObject(o);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> getAllListByMap(Map map) {
		return themeMapper.getAllListByMap(map);
	}

    @Override
    public List<Map<String, Object>> getAllListMapByIsHome() {
        return themeMapper.getAllListMapByIsHome();
    }

    @Override
    public List<Map<String, Object>> getAllListMapByIsMore() {
        return themeMapper.getAllListMapByIsMore();
    }

	@Override
	public List<Map<String, Object>> getAllListMapByIsMore2() {
		
		return themeMapper.getAllListMapByIsMore2();
	}

	@Override
	public List<Map<String, Object>> getThemeDetailListMap() {
		return themeMapper.getThemeDetailListMap();
	}

	@Override
	public void updateIcon(Map<String, Object> params) {
		themeMapper.updateIcon(params);
	}



}
