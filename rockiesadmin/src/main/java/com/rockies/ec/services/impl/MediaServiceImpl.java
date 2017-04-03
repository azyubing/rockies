package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.MediaMapper;
import com.rockies.ec.services.IMediaService;

@Service
public class MediaServiceImpl implements IMediaService {

	@Autowired
	private MediaMapper mediaMapper;
	
	@Override
	public int insert(Object o) {
		// TODO Auto-generated method stub
		return mediaMapper.insert(o);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		mediaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		// TODO Auto-generated method stub
		return mediaMapper.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		// TODO Auto-generated method stub
		return mediaMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		// TODO Auto-generated method stub
		return mediaMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return mediaMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		// TODO Auto-generated method stub
		return mediaMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		// TODO Auto-generated method stub
		mediaMapper.updateObject(o);
	}

}
