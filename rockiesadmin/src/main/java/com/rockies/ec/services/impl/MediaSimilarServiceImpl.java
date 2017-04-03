package com.rockies.ec.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.MediaSimilarMapper;
import com.rockies.ec.model.MediaSimilar;
import com.rockies.ec.services.IMediaSimilarService;

@Service
public class MediaSimilarServiceImpl implements IMediaSimilarService {

	@Autowired
	private MediaSimilarMapper mediaSimilarMapper;
	
	@Override
	public int insert(Object o) {
		return mediaSimilarMapper.insert(o);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		mediaSimilarMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		return mediaSimilarMapper.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		return mediaSimilarMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return mediaSimilarMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		return mediaSimilarMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		return mediaSimilarMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		mediaSimilarMapper.updateObject(o);
	}

	@Override
	public boolean checkProductExist(Map map) {
		int count = mediaSimilarMapper.checkProductExist(map);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public void saveListMediaSimilarProduct(String productIds, int mediaId,int userId) {
		String[] ids = productIds.split(",");
		for(String id :ids){
			MediaSimilar m = new MediaSimilar();
			m.setCtdt(new Date());
			m.setCtuser(userId+"");
			m.setProductId(id);
			m.setMediaId(mediaId);
			this.insert(m);
		}
		
	}

	@Override
	public List<Map<String, Object>> getAllProductList(int id) {
		return mediaSimilarMapper.getAllProductList(id);
	}

	@Override
	public int getMediaSimilarCount(int id) {
		return mediaSimilarMapper.getMediaSimilarCount(id);
	}

}
