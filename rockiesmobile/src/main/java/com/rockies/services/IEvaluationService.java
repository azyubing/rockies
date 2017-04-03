package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.Evaluation;
import com.rockies.services.base.BaseService;

public interface IEvaluationService extends BaseService {
	public List<Evaluation> getEvaluationListByPid(Map params);

	public int getEvaluationCountByPid(String pid);
}
