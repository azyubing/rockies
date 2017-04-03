package com.rockies.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.MediaMapper;
import com.rockies.model.Media;
import com.rockies.services.IMediaService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

	@Override
	public Media mediaDetail(Integer vid) {
		Media media = (Media) mediaMapper.getOneByPrimaryKey(vid);
		List tags = mediaMapper.getMediaTags(vid);
		List similars = mediaMapper.getMediaSimilars(vid);
		if(similars.size()>3){
			similars = similars.subList(0, 3);
		}
		for (int i = 0; i < similars.size(); i++) {
			Map map = (Map) similars.get(i);
			String dtags = (String) map.get("tags");
			if(null != dtags || !"".equals(dtags)){
				String[] dtagsList = dtags.split("\\|");	
				map.put("tags",dtagsList);
			}
			String pdesc = (String)map.get("pdesc");
			if(null != pdesc || !"".equals(pdesc)){
				Document doc = Jsoup.parse(pdesc);
				String text = doc.text();
				map.put("pdesc", text);
			}
		}
		media.setMediatags(tags);
		media.setMediasimilar(similars);
		return media;
	}

	@Override
	public List morePackage(List mediatags) {
		Map param = new HashMap();
		if(mediatags.size()>3){
			mediatags = mediatags.subList(0, 3);
		}
		for (int i = 0; i < mediatags.size(); i++) {
			Map tmp = (Map)mediatags.get(i);
			param.put("p"+i, tmp.get("name"));
		}
		
		List reslutList = mediaMapper.morePackage(param);

		if(reslutList.size()>3){
			reslutList = reslutList.subList(0, 3);
		}
		for (int i = 0; i < reslutList.size(); i++) {
			Map map = (Map) reslutList.get(i);
			String dtags = (String) map.get("tags");
			if(null != dtags || !"".equals(dtags)){
				String[] dtagsList = dtags.split("\\|");	
				map.put("tags",dtagsList);
			}
			String pdesc = (String)map.get("pdesc");
			if(null != pdesc || !"".equals(pdesc)){
				Document doc = Jsoup.parse(pdesc);
				String text = doc.text();
				map.put("pdesc", text);
			}
		}
		
		return reslutList;
	}

}
