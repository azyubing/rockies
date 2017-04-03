package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.MediaTagMapper;
import com.rockies.ec.services.IMediaTagService;

@Service
public class MediaTagServiceImpl implements IMediaTagService {

	@Autowired
	private MediaTagMapper mediaTagMapper; 
	@Override
	public int insert(Object o) {
		// TODO Auto-generated method stub
		return mediaTagMapper.insert(o);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		mediaTagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		// TODO Auto-generated method stub
		return mediaTagMapper.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		// TODO Auto-generated method stub
		return mediaTagMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		// TODO Auto-generated method stub
		return mediaTagMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return mediaTagMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		// TODO Auto-generated method stub
		return mediaTagMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		// TODO Auto-generated method stub
		mediaTagMapper.updateObject(o);
	}

}
