package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.BaseMapper;

public interface ThemeMapper extends BaseMapper {
	List<Map<String, Object>> getAllListMapByIsHome();

	List<Map<String, Object>> getAllListMapByIsMore();

	List<Map<String, Object>> getAllListMapByIsMore2();

	List<Map<String, Object>> getThemeDetailListMap();

	void updateIcon(Map<String, Object> map);
}
