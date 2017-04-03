package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.Collection;
import com.rockies.services.base.IBaseInfoService;

public interface ICollectionService extends IBaseInfoService<Collection> {
	List<Map<String,Object>> selectCollectionProductListByParam(Map<String, Object> param);
	int selectByPid(Collection collection);
	boolean delete(Collection favorite);
}
