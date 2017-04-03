package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.mapper.base.BaseMapper;
import com.rockies.model.Evaluation;

public interface EvaluationMapper extends BaseMapper {

	List<Evaluation> getEvaluationListByPid(Map params);

	int getEvaluationCountByPid(String pid);
	
}
