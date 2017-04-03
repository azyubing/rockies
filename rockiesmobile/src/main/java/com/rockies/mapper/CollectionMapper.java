package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.IBaseInfoMapper;
import com.rockies.model.Collection;

public interface CollectionMapper extends IBaseInfoMapper<Collection> {
    
	List<Map<String,Object>> selectCollectionProductListByParam(Map<String, Object> param);
	int selectByPid(Collection collection);
	int delete(Collection favorite);
	
}