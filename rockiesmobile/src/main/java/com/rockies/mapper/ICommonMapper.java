package com.rockies.mapper;

import java.util.List;
import java.util.Map;

public interface ICommonMapper {

	/**
	 * 自动填充
	 * @param parentId
	 * @return
	 */
	public List<String> getAutoCompleteList(Map<String, String> param);
	
}
